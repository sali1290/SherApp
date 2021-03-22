package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.BookContentItemModelBinding
import ir.rahnama.sherapp.model.BookContentModel
import ir.rahnama.sherapp.view.BookContentFragmentDirections
import ir.rahnama.sherapp.view.PoetryCategoryFragmentDirections


class BookContentAdapter: RecyclerView.Adapter<BookContentAdapter.MyViewHolder>() {


    private  var bookContentList:MutableList<BookContentModel> = arrayListOf()

    fun refreshData(newBookContent:List<BookContentModel>){
        if (newBookContent.size > bookContentList.size){
            bookContentList.clear()
            bookContentList.addAll(newBookContent)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(DataBindingUtil.inflate(inflater, R.layout.book_content_item_model,parent,false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.bookContent = bookContentList[position]

        holder.view.bookContentLayout.setOnClickListener {
            val action = BookContentFragmentDirections.actionToPoemBody(bookContentList[position].id)
            it?.let { Navigation.findNavController(it).navigate(action) }
        }

    }


    override fun getItemCount() = bookContentList.size



    class MyViewHolder(var view : BookContentItemModelBinding) : RecyclerView.ViewHolder(view.root)


}