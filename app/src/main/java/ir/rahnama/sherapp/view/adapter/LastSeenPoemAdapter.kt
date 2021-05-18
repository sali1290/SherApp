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


class LastSeenPoemAdapter : RecyclerView.Adapter<LastSeenPoemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView : TextView = itemView.last_seen_tv_mesra!!
    }

    private var poemBodyList: MutableList<PoemBodyModel> = arrayListOf()

    fun updatePoems(newPoem: MutableList<PoemBodyModel>) {
        Log.i("poemSize", "1:${poemBodyList.size}")
        poemBodyList.clear()
        Log.i("poemSize", "2:${poemBodyList.size}")
        poemBodyList.addAll(newPoem)
        Log.i("poemSize", "3:${poemBodyList.size}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.last_seen_mesra , parent ,
            false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = poemBodyList[position].body
    }

    override fun getItemCount() = poemBodyList.size

}

