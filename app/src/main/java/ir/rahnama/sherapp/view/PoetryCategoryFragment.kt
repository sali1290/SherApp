package ir.rahnama.sherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentPoetryCategoryBinding
import ir.rahnama.sherapp.view.adapter.PoetryCategoryAdapter
import ir.rahnama.sherapp.viewmodel.PoetryViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.toast


@AndroidEntryPoint
class PoetryCategoryFragment : Fragment() {


   private val viewModel: PoetryViewModel by viewModels()
    private var binding : FragmentPoetryCategoryBinding by autoCleared()


    var type = "" // -> type of Poetry
    private val categoryAdapter = PoetryCategoryAdapter()

   // private val binding get() =_binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPoetryCategoryBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            type =PoetryCategoryFragmentArgs.fromBundle(it).type
            viewModel.getPoetryNames(type)
        }



        binding.poetryListRecyclerView.apply {

            layoutManager = GridLayoutManager(requireActivity(),3)
            adapter=categoryAdapter

        }

        observeViewModel()

    }

    private fun observeViewModel(){

        viewModel.poetryNames.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> it.data?.let { categoryAdapter.refreshData(it) }
                ERROR -> { it.message?.let { requireActivity().toast(it) } }
                LOADING -> {}
            }
        })

    }




}