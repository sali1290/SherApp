package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.model.SearchModel

class SearchAdapter(private val searchModel:MutableList<SearchModel>): RecyclerView.Adapter<SearchAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.searchResult)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

        holder.textView.text = searchModel[position].body

    }

    override fun getItemCount() = searchModel.size


}