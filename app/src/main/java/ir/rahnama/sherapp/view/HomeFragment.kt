package ir.rahnama.sherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentHomeBinding
import ir.rahnama.sherapp.model.SelectionPoetryModel
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.view.adapter.SelectionNewPoetryAdapter
import ir.rahnama.sherapp.view.adapter.SelectionOldPoetryAdapter
import ir.rahnama.sherapp.viewmodel.PoetryViewModel
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import kotlinx.android.synthetic.main.main_fab_menu_layout.*
import kotlinx.android.synthetic.main.main_fab_menu_layout.view.*


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val viewModel: PoetryViewModel by viewModels()
    private var binding: FragmentHomeBinding by autoCleared()
    private lateinit var clickLisener: MovableFloatingActionButton.FabCustomeListener
    private val selectionOldPoetryAdapter = SelectionOldPoetryAdapter()
    private val selectionNewPoetryAdapter = SelectionNewPoetryAdapter()
    private val poemAdapter = PoemBodyAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerViews()
        setUpLastSeenPoem()
        observeViewModel()

        binding.run {
            // 0 -> get old poetry list // 1 -> get new poetry list
            newPoetrySelect.setOnClickListener { loadPoetryListFragment("1") }
            OldPoetrySelect.setOnClickListener { loadPoetryListFragment("0") }
            btnFab.setOnClickListener {
                MainDialogFragmentPopUp().show(
                    requireActivity().supportFragmentManager,
                    "popUp"
                )
            }

        }

    }

    private fun setUpRecyclerViews() = binding.run {
        newPoetryRecycler.adapter = selectionOldPoetryAdapter
        oldPoetryRecycler.adapter = selectionNewPoetryAdapter
        lastSeenRecycler.adapter = poemAdapter
    }


    private fun setUpLastSeenPoem() {
        if (Hawk.contains("lastSeen")) {
            viewModel.getLastSeenPoem(Hawk.get("lastSeen"))
        }
    }

    private fun observeViewModel() {
        viewModel.selectionPoetry.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { poetry ->
                        val oldPoetry = poetry.filter { poetry -> poetry.poet_type == "0" }
                        val newPoetry = poetry.filter { poetry -> poetry.poet_type == "1" }
                        selectionOldPoetryAdapter.refreshData(oldPoetry)
                        selectionNewPoetryAdapter.refreshData(newPoetry)
                        binding.newPoetryError.setGone()
                        binding.oldPoetryError.setGone()
                    }
                }

                ERROR -> {
                    it.message?.let { message -> dataObserveError(message) }
                }
                LOADING -> {
                }

            }
        })

        viewModel.lastPoem.observe(viewLifecycleOwner, Observer {
            it?.let { poemAdapter.updatePoems(it) }
        })
    }


    private fun dataObserveError(message: String) = with(binding) {
        requireActivity().toast(message)
        newPoetryError.setVisible()
        oldPoetryError.setVisible()
    }

    private fun loadPoetryListFragment(type: String) {
        val action = HomeFragmentDirections.actionHomeToCategory()
        action.type = type
        view?.let { Navigation.findNavController(it).navigate(action) }
    }


}