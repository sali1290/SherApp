package ir.rahnama.sherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentBooksListBinding
import ir.rahnama.sherapp.view.adapter.BooksAdapter
import ir.rahnama.sherapp.viewmodel.BooksViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.utiles.toast
import kotlin.properties.Delegates


@AndroidEntryPoint
class BooksListFragment : Fragment() {


    private val viewModel:BooksViewModel by viewModels()
    private var binding : FragmentBooksListBinding by autoCleared()

    private val bookAdapter = BooksAdapter()
    private var poetryId by Delegates.notNull<Int>()


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

        poetryId= requireArguments().getInt("id")
        viewModel.getBooksList(poetryId)



        binding.booksListRecycler.apply {
            layoutManager=GridLayoutManager(requireActivity(),2)
            adapter=bookAdapter
        }


        observeViewModel()

    }

    private fun observeViewModel(){
        viewModel.books.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> it.data?.let { it1 -> bookAdapter.refreshData(it1) }
                ERROR -> it.message?.let { it2 -> requireActivity().toast(it2) }
                LOADING ->{}

            }
        })
    }


}