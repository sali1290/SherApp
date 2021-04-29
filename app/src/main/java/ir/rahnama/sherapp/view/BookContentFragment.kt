package ir.rahnama.sherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentBooksListBinding
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.BookContentAdapter
import ir.rahnama.sherapp.viewmodel.BookContentViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.toast


@AndroidEntryPoint
class BookContentFragment : Fragment() {


    private val viewModel:BookContentViewModel by viewModels()
    private var binding :FragmentBooksListBinding by autoCleared()

    private var  bookContentAdapter = BookContentAdapter()

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

            val id =requireArguments().getString("id").toString()
            viewModel.getBookContent(id)
            Log.i("tag", id)

        setupRecylerView()
        observViewModel()
    }

    private fun setupRecylerView() = with(binding){
        booksListRecycler.adapter=bookContentAdapter
    }

    private fun observViewModel(){
        viewModel.bookContent.observe(viewLifecycleOwner , Observer {
            when (it.status) {
                SUCCESS -> it.data?.let { bookContentAdapter.refreshData(it) }
                ERROR ->  it.message?.let { requireActivity().toast(it) }
                LOADING -> {}
            }
        })
    }

}