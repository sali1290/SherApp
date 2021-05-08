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
import ir.rahnama.sherapp.utiles.ResultHandler
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.SearchAdapter
import ir.rahnama.sherapp.viewmodel.SearchVieModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding by autoCleared()
    private val viewModel: SearchVieModel by viewModels()
    private val searchAdapter:SearchAdapter = SearchAdapter()
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
        searchAdapter.clearData()
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

            searchAdapter.clearData()
            searchAdapter.pos = pos

        }

        binding.searchRecycler.apply {

            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireActivity())

        }

            binding.filterImage.setOnClickListener {

                if (binding.resultView.visibility == View.VISIBLE && binding.radioGroupFilter.visibility == View.VISIBLE) {
                    binding.resultView.visibility = View.GONE
                    binding.radioGroupFilter.visibility = View.GONE
                }

                else if (binding.resultView.visibility == View.VISIBLE && binding.radioGroupFilter.visibility == View.GONE) {
                    binding.searchText.visibility = View.GONE
                    binding.resultText.visibility = View.GONE
                    binding.radioGroupFilter.visibility = View.VISIBLE
                } else {
                    binding.resultView.visibility = View.VISIBLE
                    binding.searchText.visibility = View.GONE
                    binding.resultText.visibility = View.GONE
                    binding.radioGroupFilter.visibility = View.VISIBLE

                }
            }


        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchAdapter.clearData()
                binding.resultView.visibility = View.VISIBLE
                binding.radioGroupFilter.visibility = View.GONE
                binding.searchText.visibility = View.VISIBLE
                binding.resultText.visibility = View.VISIBLE
                binding.resultText.text = query
                if(query!="")
                query?.let { viewModel.getSearchResult(it,pos)}
                else
                    binding.resultView.visibility = View.GONE


                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                searchAdapter.clearData()
                binding.resultView.visibility = View.VISIBLE
                binding.radioGroupFilter.visibility = View.GONE
                binding.searchText.visibility = View.VISIBLE
                binding.resultText.visibility = View.VISIBLE
                binding.resultText.text = newText

                if(newText!="")
                newText?.let { viewModel.getSearchResult(it,pos)}
                else
                    binding.resultView.visibility = View.GONE



                return false
            }

        })

        observerViewModel()
    }

    private fun observerViewModel() {

        viewModel.searchModel.observe(viewLifecycleOwner,{

            when(it){

                is ResultHandler.Loading -> {
                    binding.resultView.visibility = View.VISIBLE
                    binding.searchText.visibility = View.VISIBLE
                    binding.resultText.visibility = View.VISIBLE

                }
                is ResultHandler.Error -> {
                    binding.resultView.visibility = View.VISIBLE
                    binding.searchText.visibility = View.GONE
                    binding.resultText.visibility = View.VISIBLE
                    binding.resultText.text = it.message

                }

                is ResultHandler.Success -> {

                    searchAdapter.addData(it.data)

                }

            }

        })
    }
}

