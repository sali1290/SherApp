package ir.rahnama.sherapp.view.adapter

import android.util.Log
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

class SelectionNewPoetryAdapter : RecyclerView.Adapter<SelectionNewPoetryAdapter.MyViewHolder>() {

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
        Log.i("image" , image)
        image.let {

            try {
                Glide.with(holder.itemView.context)
                    .load(image)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.view.imageSelectionPoetry)
            }
            catch (e:Exception){}
            }
        holder.view.cardview.setOnClickListener {
            val bundle = bundleOf("id" to poetryList[position].category_id)
            holder.itemView.findNavController().navigate(R.id.booksListFragment,bundle)
        }

    }

    override fun getItemCount() = poetryList.size


    class MyViewHolder(var view: SelectionPoetryItemBinding) : RecyclerView.ViewHolder(view.root)

}