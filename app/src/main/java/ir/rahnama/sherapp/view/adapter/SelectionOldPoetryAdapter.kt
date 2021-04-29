package ir.rahnama.sherapp.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.SelectionPoetryItemBinding
import ir.rahnama.sherapp.model.SelectionPoetryModel

class SelectionOldPoetryAdapter : RecyclerView.Adapter<SelectionOldPoetryAdapter.MyViewHolder>() {

    private var poetryList: MutableList<SelectionPoetryModel> = arrayListOf()

    fun refreshData(newPoetryList: List<SelectionPoetryModel>) {
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
                R.layout.selection_poetry_item,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.poetry = poetryList[position]
        val image = poetryList[position].image
        image.let {

            Glide.with(holder.itemView.context)
                .load(image)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.view.imageSelectionPoetry)
        }

        holder.view.cardview.setOnClickListener {
            val bundle = bundleOf("id" to poetryList[position].category_id,"image" to image)
            holder.itemView.findNavController().navigate(R.id.booksListFragment,bundle)
        }

        val sharedImage: SharedPreferences =
            holder.itemView.context.getSharedPreferences("imagePoem", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedImage.edit()
        editor.putString("image", image)
        editor.apply()

    }


    override fun getItemCount() = poetryList.size


    class MyViewHolder(var view: SelectionPoetryItemBinding) : RecyclerView.ViewHolder(view.root)

}