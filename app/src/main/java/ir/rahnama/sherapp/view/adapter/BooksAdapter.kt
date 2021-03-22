package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.BooksItemLeftModelBinding
import ir.rahnama.sherapp.databinding.BooksItemRightModelBinding
import ir.rahnama.sherapp.model.BookModel
import ir.rahnama.sherapp.view.BooksListFragmentDirections


class BooksAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val LAYOUT_ONE = 1
    private val LAYOUT_TWO = 2
    private val LAYOUT_THREE = 3
    private val LAYOUT_FOUR = 4

     private var books:MutableList<BookModel> = arrayListOf()


    fun refreshData(newBooks:List<BookModel>){
        if (newBooks.size > books.size){
            books.clear()
            books.addAll(newBooks)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            LAYOUT_ONE -> MyViewHolderOne(DataBindingUtil.inflate(inflater,R.layout.books_item_left_model, parent, false))
            LAYOUT_TWO -> MyViewHolderTwo(DataBindingUtil.inflate(inflater, R.layout.books_item_right_model, parent, false))
            LAYOUT_THREE -> MyViewHolderTwo(DataBindingUtil.inflate(inflater, R.layout.books_item_right_model, parent, false))
            LAYOUT_FOUR -> MyViewHolderOne(DataBindingUtil.inflate(inflater,R.layout.books_item_left_model, parent, false))
            else -> MyViewHolderOne(DataBindingUtil.inflate(inflater,R.layout.books_item_left_model, parent, false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when(holder.itemViewType){
            LAYOUT_ONE -> {
                holder as MyViewHolderOne
                holder.view.book=books[position]
                holder.view.itemLayout.setOnClickListener { goToBookContent(it,books[position].id) }
            }
            LAYOUT_TWO -> {
                holder as MyViewHolderTwo
                holder.view.book=books[position]
                holder.view.itemLayout.setOnClickListener { goToBookContent(it,books[position].id) }
            }
            LAYOUT_THREE -> {
                holder as MyViewHolderTwo
                holder.view.book=books[position]
                holder.view.itemLayout.setOnClickListener { goToBookContent(it,books[position].id) }
            }
            LAYOUT_FOUR ->{
                holder as MyViewHolderOne
                holder.view.book=books[position]
                holder.view.itemLayout.setOnClickListener { goToBookContent(it,books[position].id) }
            }
        }


    }


    override fun getItemCount() = books.size


    override fun getItemViewType(position: Int): Int {

        return when(position){
            0 -> LAYOUT_ONE
            1 -> LAYOUT_TWO
            2 -> LAYOUT_THREE
            3 -> LAYOUT_FOUR
            4 -> LAYOUT_ONE
            5 -> LAYOUT_TWO
            6 -> LAYOUT_THREE
            7 -> LAYOUT_FOUR
            8 -> LAYOUT_ONE
            else -> LAYOUT_TWO
        }


    }


    class MyViewHolderOne(var view : BooksItemLeftModelBinding) : RecyclerView.ViewHolder(view.root)

    class MyViewHolderTwo(var view : BooksItemRightModelBinding) : RecyclerView.ViewHolder(view.root)

    private fun goToBookContent(view:View,id:String){
        val action = BooksListFragmentDirections.actionBookToBookContent(id)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

}


