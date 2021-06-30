package ir.rahnama.sherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.model.PoemBodyModel
import ir.rahnama.sherapp.utiles.Resource
import ir.rahnama.sherapp.utiles.toast
import ir.rahnama.sherapp.view.FavoriteFragment
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel

class FavoritesAdapter(val ids: MutableList<Int> , val poemBody : MutableList<String>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_poem, parent, false)
        return FavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        for (i in 0 until 3000){
            holder.recycler.adapter = ShowFavoritePoemAdapter(poemBody)
        }
    }

    override fun getItemCount() = ids.size

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycler: RecyclerView = itemView.findViewById(R.id.favoritesPoemRecycler)
    }

}