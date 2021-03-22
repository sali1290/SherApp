package ir.rahnama.sherapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.PoetsItemMenuBinding
import ir.rahnama.sherapp.model.PoetModel
import ir.rahnama.sherapp.view.BooksListFragment
import ir.rahnama.sherapp.view.HomeFragmentDirections
import ir.rahnama.sherapp.view.PoetryCategoryFragmentDirections


class PoetryCategoryAdapter() : RecyclerView.Adapter<PoetryCategoryAdapter.MyViewHolder>() {


    private var poetryList: MutableList<PoetModel> = arrayListOf()

    fun refreshData(newPoetryList: List<PoetModel>) {
        if (newPoetryList.size > poetryList.size) {
            poetryList.clear()
            poetryList.addAll(newPoetryList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.poets_item_menu,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.poetry = poetryList[position]
        val image = poetryList[position].image
        image?.let {
            if (image != "") {
                Picasso.get().load(image).into(holder.view.imageSelectionPoetryItem)
            }
        }
        holder.view.cardview.setOnClickListener {
            val action =
                PoetryCategoryFragmentDirections.actionPoetryToBooks(poetryList[position].category_id)
            it?.let { Navigation.findNavController(it).navigate(action) }
        }




    }


    override fun getItemCount() = poetryList.size


    class MyViewHolder(var view: PoetsItemMenuBinding) : RecyclerView.ViewHolder(view.root)


}