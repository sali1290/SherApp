package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.PoetsItemMenuBinding
import ir.rahnama.sherapp.model.PoetModel


class PoetryCategoryAdapter : RecyclerView.Adapter<PoetryCategoryAdapter.MyViewHolder>() {


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
        image.let {
                Glide.with(holder.itemView.context)
                    .load(image)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.view.imageSelectionPoetryItem)
            }
        holder.view.cardview.setOnClickListener {
            val bundle = bundleOf("id" to poetryList[position].category_id)
            holder.itemView.findNavController().navigate(R.id.booksListFragment,bundle)
        }


    }


    override fun getItemCount() = poetryList.size


    class MyViewHolder(var view: PoetsItemMenuBinding) : RecyclerView.ViewHolder(view.root)


}