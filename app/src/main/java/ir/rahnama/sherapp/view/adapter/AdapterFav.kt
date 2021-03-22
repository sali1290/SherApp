package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.model.PoemBodyModel
import kotlinx.android.synthetic.main.row_item_fav.view.*

class AdapterFav(var poem: MutableList<PoemBodyModel>) :
    RecyclerView.Adapter<AdapterFav.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_fav, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text_adapter_fav.text = poem[position].toString()
    }

    override fun getItemCount(): Int {
        return poem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}