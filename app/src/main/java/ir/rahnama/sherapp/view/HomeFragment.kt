package ir.rahnama.sherapp.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.FragmentHomeBinding
import ir.rahnama.sherapp.utiles.*
import ir.rahnama.sherapp.utiles.Resource.Status.*
import ir.rahnama.sherapp.view.adapter.LastSeenPoemAdapter
import ir.rahnama.sherapp.view.adapter.PoemBodyAdapter
import ir.rahnama.sherapp.view.adapter.SelectionNewPoetryAdapter
import ir.rahnama.sherapp.view.adapter.SelectionOldPoetryAdapter
import ir.rahnama.sherapp.viewmodel.PoemBodyViewModel
import ir.rahnama.sherapp.viewmodel.PoetryViewModel
import ir.rahnama.sherapp.viewmodel.PosterViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener
import java.util.Observer

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val poemViewModel: PoemBodyViewModel by viewModels()
    private val viewModel: PoetryViewModel by viewModels()
    private val posterViewModel: PosterViewModel by viewModels()
    private var binding: FragmentHomeBinding by autoCleared()
    private val selectionOldPoetryAdapter = SelectionOldPoetryAdapter()
    private val selectionNewPoetryAdapter = SelectionNewPoetryAdapter()
    private val list = mutableListOf<CarouselItem>()
    private val lastPoemAdapter = LastSeenPoemAdapter()

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
        setUpLastSeenPoem()
        observePosterViewModel()
        setUpRecyclerViews()
        observeViewModel()

        binding.run {
            // 0 -> get old poetry list // 1 -> get new poetry list
            newPoetrySelect.setOnClickListener { loadPoetryListFragment(0, "شاعرهای کهن") }
            OldPoetrySelect.setOnClickListener { loadPoetryListFragment(1, "شاعر های معاصر") }
            constraintLayout6.setOnClickListener { loadFalHafzFragment() }



            search_bar.setOnClickListener { loadSearchFragment() }
            btnFab.setOnClickListener {
                MainDialogFragmentPopUp().show(
                    requireActivity().supportFragmentManager,
                    "popUp"
                )
            }

        }


    }

    private fun loadFalHafzFragment() {

        view?.findNavController()?.navigate(R.id.falHafzFragment)
    }

    private fun observePosterViewModel() {

        posterViewModel.posterModel.observe(viewLifecycleOwner, {

            when (it.status) {
                SUCCESS -> {
                    it.data?.let { it1 ->
                        list.clear()
                        for (i in 0 until it1.size) {
                            list.add(
                                CarouselItem(
                                    imageUrl = it1[i].image
                                )
                            )
                        }
                        binding.carousel.addData(list)
                        carousel.onItemClickListener = object : OnItemClickListener {
                            override fun onClick(position: Int, carouselItem: CarouselItem) {

                                if (it1[position].clickable == 1) {
                                    val bundle = bundleOf("id" to it1[position].category_id)
                                    view?.findNavController()
                                        ?.navigate(R.id.booksListFragment, bundle)
                                }

                            }

                            override fun onLongClick(position: Int, dataObject: CarouselItem) {
                                // ...
                            }

                        }
                    }
                }
                ERROR -> {
                    it.message?.let { message -> dataObserveError(message) }
                }
                LOADING -> {
                }
            }
        })
    }

    private fun setUpRecyclerViews() = binding.run {
        newPoetryRecycler.adapter = selectionOldPoetryAdapter
        oldPoetryRecycler.adapter = selectionNewPoetryAdapter
        lastSeenRecycler.adapter = lastPoemAdapter
       // lastSeenRecycler.adapter = poemAdapter
    }


    private fun setUpLastSeenPoem() {
//        if (Hawk.contains("lastSeen")) {
//            viewModel.getLastSeenPoem(Hawk.get("lastSeen"))
//        }
        val sharedLastSeen =
            requireActivity().getSharedPreferences("lastSeenPoem", Context.MODE_PRIVATE)
        if (sharedLastSeen.getInt("lastSeenPoemId", 0) != 0) {
            poemViewModel.getPoemById(sharedLastSeen.getInt("lastSeenPoemId", 0))
            observeViewModelPoem()
        }

    }

    private fun observeViewModel() {
        viewModel.selectionPoetry.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { poetry ->
                        val oldPoetry = poetry.filter { poetry -> poetry.poet_type == 0 }
                        val newPoetry = poetry.filter { poetry -> poetry.poet_type == 1 }
                        selectionOldPoetryAdapter.refreshData(oldPoetry)
                        selectionNewPoetryAdapter.refreshData(newPoetry)

                    }
                }

                ERROR -> {
                    it.message?.let { message -> dataObserveError(message) }
                }
                LOADING -> {
                }

            }
        })

        viewModel.lastPoem.observe(viewLifecycleOwner, {
            it?.let { lastPoemAdapter.updatePoems(it) }
        })
    }

    private fun dataObserveError(message: String) = with(binding) {
        requireActivity().toast(message)
    }

    private fun loadPoetryListFragment(type: Int, name: String) {
        val bundle = bundleOf("type" to type, "name" to name)
        view?.findNavController()?.navigate(R.id.poetryCategoryFragment, bundle)

    }

    private fun loadSearchFragment() {

        view?.findNavController()?.navigate(R.id.searchFragment)

    }

    private fun observeViewModelPoem() {
        poemViewModel.poemBody.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                SUCCESS -> it.data?.let {
                    lastPoemAdapter.updatePoems(it)
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