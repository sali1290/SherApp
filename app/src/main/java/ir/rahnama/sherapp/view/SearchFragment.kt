package ir.rahnama.sherapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import ir.rahnama.sherapp.databinding.FragmentSearchBinding
import ir.rahnama.sherapp.utiles.autoCleared
import kotlin.properties.Delegates

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding by autoCleared()
    private lateinit var shared: SharedPreferences
    private var pos by Delegates.notNull<Int>()

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


        binding.run {
            filterIcon.setOnClickListener {
                SearchDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    "popup"
                )
            }
           /* search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                }

                override fun onQueryTextChange(newText: String?): Boolean {

                }

            })*/
        }
        shared=view.context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        pos = shared.getInt("position",0)
    }


}