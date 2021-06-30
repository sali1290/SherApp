package ir.rahnama.sherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FragmentBooksListBinding
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.BookContentAdapter
import ir.rahnama.sherapp.viewmodel.BookContentViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.utiles.toast
import kotlin.properties.Delegates


@AndroidEntryPoint
class BookContentFragment : Fragment() {


    private val viewModel:BookContentViewModel by viewModels()
    private var binding :FragmentBooksListBinding by autoCleared()
    private var bookId by Delegates.notNull<Int>()

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

            bookId=requireArguments().getInt("id")
            viewModel.getBookContent(bookId)


        setupRecylerView()
        observViewModel()
    }

    private fun setupRecylerView() = with(binding){
        booksListRecycler.adapter=bookContentAdapter
    }

    private fun observViewModel(){
        viewModel.bookContent.observe(viewLifecycleOwner , {
            when (it.status) {
                SUCCESS -> it.data?.let { it1 -> bookContentAdapter.refreshData(it1) }
                ERROR ->  it.message?.let {
//                        it2 -> requireActivity().toast(it2)
                    Toast.makeText(
                        requireContext(),
                        "محتوای این کتاب فعلا در دسترس نیست",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                LOADING -> {}
            }
        })
    }

}