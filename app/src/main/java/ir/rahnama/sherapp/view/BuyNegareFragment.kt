package ir.rahnama.sherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.BuyNegarFragmentBinding
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.view.adapter.NegareAdapter
import ir.rahnama.sherapp.viewmodel.BuyNegareViewModel
import kotlinx.android.synthetic.main.buy_negar_fragment.view.*
import ir.rahnama.sherapp.utiles.Resource.Status.*

@AndroidEntryPoint
class BuyNegareFragment : Fragment() {


    val viewModel : BuyNegareViewModel by viewModels()
    var binding : BuyNegarFragmentBinding by autoCleared()
    val negareAdapter = NegareAdapter ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = BuyNegarFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buyNegarRecyclerView.apply {
            adapter = negareAdapter
            layoutManager = GridLayoutManager(requireActivity() , 2)
        }

        viewModel.getNegare()

        observeViewModel()

    }


    fun observeViewModel () {

        viewModel.negare.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> it.data?.let {
                    negareAdapter.refresh(it)
                    binding.buNegareLoadingIndicator.setInvisible()
                }
                ERROR -> it.message?.let { requireActivity().toast(it) }
                LOADING -> { binding.buNegareLoadingIndicator.setVisible() }
            }
        })

    }

}