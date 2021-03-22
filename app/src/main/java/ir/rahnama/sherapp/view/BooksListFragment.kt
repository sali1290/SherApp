package ir.rahnama.sherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentBooksListBinding
import ir.rahnama.sherapp.utiles.Resource
import ir.rahnama.sherapp.view.adapter.BooksAdapter
import ir.rahnama.sherapp.viewmodel.BooksViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.toast


@AndroidEntryPoint
class BooksListFragment : Fragment() {


    private val viewModel:BooksViewModel by viewModels()
    private var binding : FragmentBooksListBinding by autoCleared()

    private val bookAdapter = BooksAdapter()
    var poetryId = ""
   // private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBooksListBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            poetryId= BooksListFragmentArgs.fromBundle(it).petryId.toString()
           viewModel.getBooksList(poetryId)
        }


        binding.booksListRecycler.apply {
            layoutManager=GridLayoutManager(requireActivity(),2)
            adapter=bookAdapter
        }


        observeViewModel()

    }

    private fun observeViewModel(){
        viewModel.books.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> it.data?.let { bookAdapter.refreshData(it) }
                ERROR -> it.message?.let { requireActivity().toast(it) }
                LOADING ->{}

            }
        })
    }


}