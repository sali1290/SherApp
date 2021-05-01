package ir.rahnama.sherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FargmentFalHafzBinding
import ir.rahnama.sherapp.databinding.FragmentShowPoemsBinding
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.view.adapter.BookContentAdapter
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import ir.rahnama.sherapp.view.adapter.PoemBodySpaceItem
import ir.rahnama.sherapp.viewmodel.BooksViewModel
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fargment_fal_hafz.*
import java.util.Observer

class FalHafzFragment : Fragment(){

    private val viewModel: BooksViewModel by viewModels()
    private val poemViewModel : PoemBodyViewModel by viewModels()
    private val poemBodyAdapter = PoemBodyAdapter()
    private var binding: FargmentFalHafzBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FargmentFalHafzBinding.inflate(inflater, container, false)
        binding.poemBodyRecyclerViewHafez.adapter = poemBodyAdapter
        binding.poemBodyRecyclerViewHafez.addItemDecoration(PoemBodySpaceItem())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = "24"
        viewModel.getBooksList(id)

        fal_hafez.setOnClickListener{



            fal_hafez.setInvisible()
            poemBodyRecyclerViewHafez.setVisible()
        }

    }

    fun getPoem(){




    }

//    private fun obserViewModel() {
//        poemViewModel.poemBody.observe(viewLifecycleOwner, Observer {
//            when (it.status) {
//                SUCCESS -> it.data?.let {
//                    poemBodyAdapter.updatePoems(it)
//                }
//                ERROR -> {
//                    it.message?.let { requireActivity().toast(it) }
//                }
//                LOADING -> {
//                }
//            }
//        })
//    }
}