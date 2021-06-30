package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import java.util.zip.Inflater

class ShowFavoritePoemAdapter(val poemBody : MutableList<String>) :
    RecyclerView.Adapter<ShowFavoritePoemAdapter.ShowFavoritePoemViewHolder>() {

    inner class ShowFavoritePoemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val body: TextView = itemView.findViewById(R.id.last_seen_tv_mesra)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowFavoritePoemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_poem, parent, false)
        return ShowFavoritePoemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowFavoritePoemViewHolder, position: Int) {
        holder.body.text = poemBody[position]
    }

    override fun getItemCount() = poemBody.size

}