package ir.rahnama.sherapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.NegarItemModelBinding
import ir.rahnama.sherapp.databinding.SubItemModelBinding
import ir.rahnama.sherapp.model.Negare
import ir.rahnama.sherapp.model.Subscribtion

class NegareAdapter :RecyclerView.Adapter<NegareAdapter.NegareMyViewHolder>() {


    var negarList : MutableList<Negare> = arrayListOf()


    fun refresh (newList : List<Negare>){
        negarList.clear()
        negarList.addAll(newList)
        Log.i("Test" , negarList.size.toString())
        notifyDataSetChanged()
    }
    inner class NegareMyViewHolder(var view : NegarItemModelBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NegareMyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NegareMyViewHolder(DataBindingUtil.inflate(inflater , R.layout.negar_item_model , parent , false))
    }

    override fun onBindViewHolder(holder: NegareMyViewHolder, position: Int) {
        holder.view.negar = negarList[position]
        Log.i("Test" , negarList[position].toString())

        holder.view.negarBuyLinearLayout.setOnClickListener(View.OnClickListener {
            //arrin pall ya bazar
        })
    }

    override fun getItemCount(): Int = negarList.size
}