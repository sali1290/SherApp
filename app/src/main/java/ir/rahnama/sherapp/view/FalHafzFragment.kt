package ir.rahnama.sherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.FargmentFalHafzBinding
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.utiles.Resource.*
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.view.adapter.*
import ir.rahnama.sherapp.viewmodel.BookContentViewModel
import ir.rahnama.sherapp.viewmodel.BooksViewModel
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import ir.rahnama.sherapp.viewmodel.PoetryViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fargment_fal_hafz.*
import kotlinx.android.synthetic.main.fragment_books_list.*
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class FalHafzFragment : Fragment(){

    private val poemViewModel : PoemBodyViewModel by viewModels()
    private val poemBodyAdapter = PoemBodyAdapter()
    private var binding: FargmentFalHafzBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FargmentFalHafzBinding.inflate(inflater, container, false)
        binding.poemBodyRecyclerViewHafez.adapter = poemBodyAdapter
        binding.poemBodyRecyclerViewHafez.addItemDecoration(PoemBodySpaceItem())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val name = "حافظ"
//        poetryViewModel.getPoetryNames(name)
//        poetryViewModel.poetryNames.observe(viewLifecycleOwner , Observer {
//            when (it.status) {
//                SUCCESS -> it.data?.let { categoryAdapter.refreshData(it) }
//                ERROR -> { it.message?.let { requireActivity().toast(it) } }
//                LOADING -> {}
//            }
//        })
//
//
//        val bookId = "24"
//        bookViewModel.getBooksList(bookId)
//        bookViewModel.books.observe(viewLifecycleOwner , Observer {
//            when (it.status) {
//                SUCCESS -> it.data?.let { bookAdapter.refreshData(it) }
//                ERROR -> it.message?.let { requireActivity().toast(it) }
//                LOADING ->{}
//            }
//        })
//
//
//        val contentId = bookAdapter.itemCount
//        bookContentViewModel.getBookContent(contentId.toString())
//        bookContentViewModel.bookContent.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            when (it.status) {
//                SUCCESS -> it.data?.let { bookContentAdapter.refreshData(it) }
//                ERROR ->  it.message?.let { requireActivity().toast(it) }
//                LOADING -> {}
//            }
//        })
//
//        val size = bookContentAdapter.itemCount
//        poemViewModel.getPoemById(size.toString())

        // from 2093 to 2624
        val id = Random().nextInt(573) + 2051
        poemViewModel.getPoemById(id.toString())
        observeModel()

        fal_hafez.setOnClickListener {

            fal_hafez.setInvisible()
            fal_hafez_background.setVisible()

        }
    }
    private fun observeModel(){
        poemViewModel.poemBody.observe(viewLifecycleOwner , androidx.lifecycle.Observer {
            when (it.status) {
                SUCCESS -> it.data?.let {
                    poemBodyAdapter.updatePoems(it)
                }
                ERROR -> {
                    it.message?.let { requireActivity().toast(it) }
                }
                LOADING -> {
                }
            }
        })
    }
    }











