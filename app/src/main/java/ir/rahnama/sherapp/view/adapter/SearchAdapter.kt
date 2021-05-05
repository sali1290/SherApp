package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.model.SearchModel
import kotlinx.android.synthetic.main.fragment_search_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>()  {

   private var searchModel:MutableList<SearchModel> = arrayListOf()
   var pos:Int=0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.searchResult)

    }

    fun addData(searchData:MutableList<SearchModel>){

        searchModel.addAll(searchData)
        notifyDataSetChanged()

    }

    fun clearData(){

        searchModel.clear()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

        holder.textView.text = searchModel[position].body
        holder.itemView.searchItemViwe.setOnClickListener {

            when (pos) {

                0 -> {
                    val bundle = bundleOf("id" to searchModel[position].category_id )
                    holder.itemView.findNavController().navigate(R.id.booksListFragment,bundle)
                }
                1 -> {
                    val bundle = bundleOf("id" to searchModel[position].id )
                    holder.itemView.findNavController().navigate(R.id.showPoemBodyFragment,bundle)
                }

                2 -> {
                    val bundle = bundleOf("id" to searchModel[position].category_id )
                    holder.itemView.findNavController().navigate(R.id.showPoemBodyFragment,bundle)
                }
            }
        }


    }

    override fun getItemCount() = searchModel.size


}