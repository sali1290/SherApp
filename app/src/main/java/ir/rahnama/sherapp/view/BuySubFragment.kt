package ir.rahnama.sherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.databinding.BuySubscriptionFragmentBinding
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.view.adapter.SubscriptionAdapter
import ir.rahnama.sherapp.viewmodel.SubscriptionViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*

@AndroidEntryPoint
class BuySubFragment : Fragment()  {

     val viewModel : SubscriptionViewModel by viewModels()
     var binding : BuySubscriptionFragmentBinding by autoCleared()
     val subAdapter = SubscriptionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BuySubscriptionFragmentBinding.inflate(inflater , container , false )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buySubRecyclerView.apply {
            adapter = subAdapter
            layoutManager = GridLayoutManager(requireActivity() , 2)
        }
        viewModel.getSubscription()
        observeViewModel()

    }


    fun observeViewModel () {

        viewModel.sub.observe(viewLifecycleOwner , {
            when(it.status){
                SUCCESS -> it.data?.let {
                    subAdapter.refresh(it)
                    binding.buySubLoadingIndicator.setInvisible()
                }
                ERROR -> it.message?.let { requireActivity().toast(it) }
                LOADING -> { binding.buySubLoadingIndicator.setVisible() }
            }
        })

    }

}