package ir.rahnama.sherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentSearchBinding
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.SearchAdapter
import ir.rahnama.sherapp.viewmodel.SearchVieModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding by autoCleared()
    private val viewModel: SearchVieModel by viewModels()
    private var pos:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroupFilter.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {

                R.id.radio_button_shaer -> {
                    pos = 0
                }


                R.id.radio_button_asar -> {
                    pos = 1

                }

                R.id.radio_button_sher -> {
                    pos = 2

                }
            }
        }


            binding.filterImage.setOnClickListener {

            if( binding.cardView5.visibility == View.VISIBLE)
            binding.cardView5.visibility = View.GONE
            else
                binding.cardView5.visibility = View.VISIBLE
        }

        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {


                binding.cardView5.visibility = View.GONE
                query?.let { viewModel.getSearchResult(it,pos)}

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                binding.cardView5.visibility = View.GONE
                newText?.let { viewModel.getSearchResult(it,pos)}

                return false
            }
        })

        observerViewModel()
    }

    private fun observerViewModel() {

        viewModel.searchModel.observe(viewLifecycleOwner,{
            val searchAdapter = SearchAdapter(it)
            binding.searchRecycler.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireActivity())
            }

        })
    }
}

