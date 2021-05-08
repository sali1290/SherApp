package ir.rahnama.sherapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.utiles.ColorTextDialogFragmentPopUp
import ir.rahnama.sherapp.utiles.Resource
import ir.rahnama.sherapp.utiles.SubDialogFragmentPopUp
import ir.rahnama.sherapp.utiles.toast
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import ir.rahnama.sherapp.view.adapter.PoemBodySpaceItem
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import kotlin.properties.Delegates


@AndroidEntryPoint
class ShowPoemBodyActivity : AppCompatActivity(), Animation.AnimationListener {

    private val viewModel: PoemBodyViewModel by viewModels()
    private var shared: SharedPreferences? = null
    private var mId by Delegates.notNull<Int>()
    private var sharedBackground: SharedPreferences? = null
    private var sharedImage: SharedPreferences? = null
    private val poemAdapter = PoemBodyAdapter()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_show_poems)

        val markFavShowPoem = findViewById<ImageView>(R.id.mark_fav_show_poem)
        val textOption = findViewById<ImageView>(R.id.textOption)
        val poemBodyRecyclerView = findViewById<RecyclerView>(R.id.poemBodyRecyclerView)
        val backgroundShowPoemBody = findViewById<ConstraintLayout>(R.id.background_show_poem_body)

        poemBodyRecyclerView.adapter = poemAdapter
        poemBodyRecyclerView.addItemDecoration(PoemBodySpaceItem())

        val id = intent.getIntExtra("id", 0)
        viewModel.getPoemById(id)
        Hawk.put("lastSeen", id)
        mId = id

        obserViewModel()

        var fav: Int? = null

        markFavShowPoem.setOnClickListener {

            if (fav == 1) {
                fav = 0
                markFavShowPoem.setImageResource(R.drawable.ic_star)

            } else {
                fav = 1
                markFavShowPoem.setImageResource(R.drawable.ic_star_bold)
                shared = getSharedPreferences("shared_fav", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = shared!!.edit()
                editor.putString("id", mId.toString())
                Toast.makeText(this, "به لیست علاقه مندی ها اضافه شد", Toast.LENGTH_SHORT).show()
                editor.apply()
            }


        }

        textOption.setOnClickListener {
            SubDialogFragmentPopUp().show(
                supportFragmentManager,
                "popUp"
            )

        }

        /*   if (mId == lId) {

               binding.nextPage.visibility = View.INVISIBLE

           } else

           binding.nextPage.setOnClickListener {

               nextPage()

           }*/

        //for slide between pages

/*
        if (mId==fId) {

            binding.previousPage.visibility = View.INVISIBLE
        }
         else {
        binding.previousPage.setOnClickListener{

            previousPage()

        }
        }

 */

        //Set Background Text
        sharedBackground =
            getSharedPreferences("shared_background_color", Context.MODE_PRIVATE)
        val bbcg: Boolean = sharedBackground!!.getBoolean("bbcg", false)
        if (bbcg) {
            val color: Int = sharedBackground!!.getInt("background_color", 0)
            backgroundShowPoemBody.setBackgroundColor(color)
        }

        //Set Image
        /* sharedImage = context?.getSharedPreferences("imagePoem", Context.MODE_PRIVATE)
         val image: String? = sharedImage!!.getString("image", "")
         context?.let {

             Glide.with(this)
                 .load(image)
                 .centerCrop()
                 .error(R.drawable.ic_launcher_foreground)
                 .into(view.image_poem_adapter)
         }*/

        poemBodyRecyclerView.setOnTouchListener(object :
            OnSwipeTouchListener(this@ShowPoemBodyActivity) {
            override fun onSwipeRight() {
                val anim1 = AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.move_out_anim
                ) as Animation
                val anim2 = AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.move_in_anim_left
                ) as Animation
                backgroundShowPoemBody.startAnimation(anim1)
                backgroundShowPoemBody.startAnimation(anim2)
                previousPage()
            }

            override fun onSwipeLeft() {
                val anim1 = AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.move_out_anim
                ) as Animation
                val anim2 = AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.move_in_anim_right
                ) as Animation
                backgroundShowPoemBody.startAnimation(anim1)
                backgroundShowPoemBody.startAnimation(anim2)
                nextPage()
            }
        })


    }


    private fun obserViewModel() {
        viewModel.poemBody.observe({ lifecycle }, {
            when (it.status) {
                Resource.Status.SUCCESS -> it.data?.let {
                    poemAdapter.updatePoems(it)
                }
                Resource.Status.ERROR -> {
                    it.message?.let { Activity().toast(it) }
                }
                Resource.Status.LOADING -> {
                }
            }
        })
    }

    private fun nextPage() {
        mId += 1
        viewModel.getPoemById(mId)
        Hawk.put("lastSeen", mId)
        obserViewModel()
    }

    private fun previousPage() {
        mId -= 1
        viewModel.getPoemById(mId)
        Hawk.put("lastSeen", mId)
        obserViewModel()
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

}