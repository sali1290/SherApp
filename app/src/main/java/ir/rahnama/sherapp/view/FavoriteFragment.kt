package ir.rahnama.sherapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentFavoritesBinding
import ir.rahnama.sherapp.utiles.Resource
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.toast
import ir.rahnama.sherapp.view.adapter.FavoritesAdapter
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import java.lang.IndexOutOfBoundsException
import java.util.ArrayList

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val poemViewModel: PoemBodyViewModel by viewModels()
    private var binding: FragmentFavoritesBinding by autoCleared()

    val poemIds: MutableList<Int> = arrayListOf()
    var poemBody: MutableList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        for (i in 0 until 3000) {
            if (context?.getSharedPreferences(i.toString(), Context.MODE_PRIVATE)
                    ?.getInt(i.toString() , 0) == 1
            ) {
                observe()
                updatedId(i)
            }
        }
        showPoems(poemIds, poemBody)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    fun updatedId(id: Int) {
        poemIds.add(id)
    }

    fun removeId(id: Int) {
        poemIds.remove(id)
    }

//    fun getItemCount(i: Int) {
//        Toast.makeText(context, i.toString(), Toast.LENGTH_LONG).show()
//    }

    fun showPoems(ids: MutableList<Int>, poemBody: MutableList<String>) {
        binding.favoritesShowPoemRecycler.adapter = FavoritesAdapter(ids, poemBody)
    }

    fun observe() {
        try {
            for (i in 0 until poemIds.size) {
                poemViewModel.getPoemById(i)
                poemViewModel.poemBody.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> it.data?.let {
                            try {
                                poemBody[i] = it[i].body
                            }catch (e : Exception){
                                Toast.makeText(requireActivity() , "لیستی برای نمایش وجود ندارد" , Toast.LENGTH_LONG).show()
                            }
                        }
                        Resource.Status.ERROR -> {
                            it.message?.let {
                                requireActivity().toast(it)
                            }
                        }
                        Resource.Status.LOADING -> {
                        }
                    }
                })
            }
        } catch (e: Exception) {
            Toast.makeText(requireActivity() , "لیستی برای نمایش وجود ندارد" , Toast.LENGTH_LONG).show()
        }
    }

}