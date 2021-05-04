package ir.rahnama.sherapp.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentFavBinding
import ir.rahnama.sherapp.utiles.Resource
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.toast
import ir.rahnama.sherapp.view.adapter.AdapterFav
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel

@AndroidEntryPoint
class FragmentFav : Fragment() {
    var shared: SharedPreferences? = null
    var binding: FragmentFavBinding by autoCleared()
    private val viewModel: PoemBodyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = context?.getSharedPreferences("shared_fav", MODE_PRIVATE)
        val id = shared!!.getInt("id", 0)



        arguments?.let {
            viewModel.getPoemById(id)
        }

        obserViewModel()

    }

    private fun obserViewModel() {
        viewModel.poemBody.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> it.data?.let {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
                Resource.Status.ERROR -> {
                    it.message?.let { requireActivity().toast(it) }
                }
                Resource.Status.LOADING -> {
                }
            }
        })
    }

}