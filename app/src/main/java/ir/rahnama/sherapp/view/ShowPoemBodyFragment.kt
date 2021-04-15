package ir.rahnama.sherapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentShowPoemsBinding
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.SubDialogFragmentPopUp
import ir.rahnama.sherapp.utiles.toast
import ir.rahnama.sherapp.view.adapter.PoemBodySpaceItem
import kotlinx.android.synthetic.main.fragment_show_poems.view.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class ShowPoemBodyFragment : Fragment(){

    private val viewModel: PoemBodyViewModel by viewModels()
    private var binding: FragmentShowPoemsBinding by autoCleared()
    private var shared: SharedPreferences? = null
    private var mId by Delegates.notNull<Int>()
    private var fId by Delegates.notNull<Int>()
    private var lId by Delegates.notNull<Int>()
    private var sharedBackground: SharedPreferences? = null
    private var sharedImage: SharedPreferences? = null

    private val poemAdapter = PoemBodyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowPoemsBinding.inflate(inflater, container, false)
        binding.poemBodyRecyclerView.adapter = poemAdapter
        binding.poemBodyRecyclerView.addItemDecoration(PoemBodySpaceItem())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getString("id")?.toIntOrNull()!!
        fId = requireArguments().getString("fId")?.toIntOrNull()!!
        lId = requireArguments().getString("lId")?.toIntOrNull()!!
        viewModel.getPoemById(id.toString())
        Hawk.put("lastSeen", id.toString())
        Log.i("pomeid", id.toString())
        mId = id

        obserViewModel()

        var fav: Int? = null

        binding.markFavShowPoem.setOnClickListener {

            if (fav == 1) {
                fav = 0
                binding.markFavShowPoem.setImageResource(R.drawable.ic_star)

            } else {
                fav = 1
                binding.markFavShowPoem.setImageResource(R.drawable.ic_star_bold)
                shared = context?.getSharedPreferences("shared_fav", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = shared!!.edit()
                editor.putString("id", mId.toString())
                Toast.makeText(context, "به لیست علاقه مندی ها اضافه شد", Toast.LENGTH_SHORT).show()
                editor.apply()
            }


        }

        binding.textOption.setOnClickListener {
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popUp"
            )
        }

        if (mId == lId) {

            binding.nextPage.visibility = View.INVISIBLE

        } else

        binding.nextPage.setOnClickListener {

            nextPage()

        }

        if (mId==fId) {

            binding.previousPage.visibility = View.INVISIBLE
        }
         else {
        binding.previousPage.setOnClickListener{

            previousPage()

        }
    }

        //Set Background Text
        sharedBackground =
            context?.getSharedPreferences("shared_background_color", Context.MODE_PRIVATE)
        val bbcg: Boolean = sharedBackground!!.getBoolean("bbcg", false)
        if (bbcg) {
            val color: Int = sharedBackground!!.getInt("background_color", 0)
            view.background_show_poem_body.setBackgroundColor(color)
        }

        //Set Image
        sharedImage = context?.getSharedPreferences("imagePoem", Context.MODE_PRIVATE)
        val image: String? = sharedImage!!.getString("image", "")
        context?.let {

            Glide.with(this)
                .load(image)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(view.image_poem_adapter)
        }

    }

      private fun obserViewModel() {
        viewModel.poemBody.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> it.data?.let {
                    poemAdapter.updatePoems(it)
                }
                ERROR -> {
                    it.message?.let { requireActivity().toast(it) }
                }
                LOADING -> {
                }
            }
        })
    }

      private fun nextPage(){
         binding.previousPage.visibility = View.VISIBLE
         mId += 1
         viewModel.getPoemById(mId.toString())
         Hawk.put("lastSeen", mId.toString())
         Log.i("pomeid", mId.toString())
         obserViewModel()
    }
    private fun previousPage(){
        binding.nextPage.visibility = View.VISIBLE
        mId -= 1
        viewModel.getPoemById(mId.toString())
        Hawk.put("lastSeen", mId.toString())
        Log.i("pomeid", mId.toString())
        obserViewModel()
    }
}