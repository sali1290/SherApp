    package ir.rahnama.sherapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.LastSeenMesraBinding
import ir.rahnama.sherapp.model.PoemBodyModel
import kotlinx.android.synthetic.main.last_seen_mesra.view.*
import kotlinx.android.synthetic.main.show_poem_mesra1_txt.view.*


    class LastSeenPoemAdapter : RecyclerView.Adapter<LastSeenPoemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView : TextView = itemView.show_poem_txt1
    }

    private var poemBodyList: MutableList<PoemBodyModel> = arrayListOf()

    fun updatePoems(newPoem: MutableList<PoemBodyModel>) {
        poemBodyList.clear()
        poemBodyList.addAll(newPoem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.show_poem_mesra1_txt , parent ,
            false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = poemBodyList[position].body
    }


    override fun getItemCount() = poemBodyList.size

}

