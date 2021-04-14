package ir.rahnama.sherapp.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.ShowPoemMesra1TxtBinding
import ir.rahnama.sherapp.databinding.ShowPoemMesra2TxtBinding
import ir.rahnama.sherapp.model.PoemBodyModel



class PoemBodyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var LAYOUT_ONE = 0
    private var LAYOUT_TWO = 1
    var shared: SharedPreferences? = null
    var sharedFont: SharedPreferences? = null
    var sharedColor: SharedPreferences? = null
   // var sharedBackground: SharedPreferences? = null
    var typeface: Typeface? = null

    private var poemBodyList: MutableList<PoemBodyModel> = arrayListOf()

    fun updatePoems(newPoem: MutableList<PoemBodyModel>) {
            Log.i("poemSize", "1:${poemBodyList.size}")
            poemBodyList.clear()
            Log.i("poemSize", "2:${poemBodyList.size}")
            poemBodyList.addAll(newPoem)
            Log.i("poemSize", "3:${poemBodyList.size}")
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        shared = parent.context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        sharedFont = parent.context.getSharedPreferences("shared_font", Context.MODE_PRIVATE)
        sharedColor = parent.context.getSharedPreferences("shared_color", Context.MODE_PRIVATE)
       // sharedBackground = parent.context.getSharedPreferences("shared_background_color", Context.MODE_PRIVATE)

        return if (viewType == LAYOUT_ONE) {
            MyViewHolderOne(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.show_poem_mesra1_txt,
                    parent,
                    false
                )
            )
        } else {
            MyViewHolderTwo(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.show_poem_mesra2_txt,
                    parent,
                    false
                )
            )
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            LAYOUT_ONE -> {
                holder as MyViewHolderOne
                holder.view.poem = poemBodyList[position]

                val size: Int = shared!!.getInt("text_size", 0)
                val bb: Boolean = shared!!.getBoolean("bb", false)
                val bbf: Boolean = sharedFont!!.getBoolean("bbf", false)
                val bbc: Boolean = sharedColor!!.getBoolean("bbc", false)
               // val bbcg: Boolean = sharedBackground!!.getBoolean("bbcg", false)

                if (bb) {
                    size.let { holder.text1.textSize = size.toFloat() }
                }

                if (bbf) {
                    val font: Int = sharedFont!!.getInt("font_text", 0)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = holder.itemView.resources.getFont(font)
                        holder.text1.typeface = typeface
                    } else {
                        typeface = ResourcesCompat.getFont(holder.itemView.context, font)
                        holder.text1.typeface = typeface
                    }
                }

                if (bbc){
                    val color: Int = sharedColor!!.getInt("text_color", 0)
                    holder.text1.setTextColor(color)
                }

               /* if (bbcg){
                    val color: Int = sharedBackground!!.getInt("background_color", 0)
                    holder.back1.setBackgroundColor(color)
                }*/

            }
            else -> {
                holder as MyViewHolderTwo
                holder.view.poem = poemBodyList[position]

                val size: Int = shared!!.getInt("text_size", 0)
                val bb: Boolean = shared!!.getBoolean("bb", false)
                val bbf: Boolean = sharedFont!!.getBoolean("bbf", false)
                val bbc: Boolean = sharedColor!!.getBoolean("bbc", false)
               // val bbcg: Boolean = sharedBackground!!.getBoolean("bbcg", false)

                if (bb) {
                    size.let { holder.text2.textSize = size.toFloat() }
                }

                if (bbf) {
                    val font: Int = sharedFont!!.getInt("font_text", 0)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = holder.itemView.resources.getFont(font)
                        holder.text2.typeface = typeface

                    } else {
                        typeface = ResourcesCompat.getFont(holder.itemView.context, font)
                        holder.text2.typeface = typeface
                    }
                }

                if (bbc){
                    val color: Int = sharedColor!!.getInt("text_color", 0)
                    holder.text2.setTextColor(color)
                }

             /*   if (bbcg){
                    val color: Int = sharedBackground!!.getInt("background_color", 0)
                    holder.back2.setBackgroundColor(color)
                }*/

            }
        }
    }


    override fun getItemCount() = poemBodyList.size

    override fun getItemViewType(position: Int) = if (position % 2 == 0) LAYOUT_ONE else LAYOUT_TWO

    class MyViewHolderOne(var view: ShowPoemMesra1TxtBinding) : RecyclerView.ViewHolder(view.root) {
        val text1 = view.showPoemTxt1
    }

    class MyViewHolderTwo(var view: ShowPoemMesra2TxtBinding) : RecyclerView.ViewHolder(view.root) {
        val text2 = view.showPoemTxt2
    }


}