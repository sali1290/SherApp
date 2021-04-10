package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.SubItemModelBinding
import ir.rahnama.sherapp.model.Subscribtion


class SubscriptionAdapter : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {

    private var subsList : MutableList<Subscribtion> = arrayListOf()


     fun refresh (newList : List<Subscribtion>){
         subsList.clear()
         subsList.addAll(newList)
         notifyDataSetChanged()
     }

    class SubscriptionViewHolder(var view : SubItemModelBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return SubscriptionViewHolder(DataBindingUtil.inflate(inflater , R.layout.sub_item_model , parent , false))
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {

        holder.view.sub= subsList[position]
        holder.view.buySubLinearLayout.setOnClickListener(View.OnClickListener {
            //zarrrin pal
        })

    }

    override fun getItemCount() : Int = subsList.size

}