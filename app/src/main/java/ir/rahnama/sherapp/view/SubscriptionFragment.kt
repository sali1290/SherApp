package ir.rahnama.sherapp.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.databinding.SubscriptionFragmentBinding
import ir.rahnama.sherapp.utiles.autoCleared
import ir.rahnama.sherapp.view.adapter.SubNegarViewPagerAdapter
import ir.rahnama.sherapp.utiles.checkUser
import kotlin.properties.Delegates


class SubscriptionFragment() : Fragment(R.layout.subscription_fragment) {

    private var binding: SubscriptionFragmentBinding by autoCleared()
    private lateinit var viewPager: ViewPager2
    private  var type = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SubscriptionFragmentBinding.inflate(inflater, container, false)
        viewPager = binding.subViewPager
        checkUser(requireContext())
        arguments?.let {
            val type = SubscriptionFragmentArgs.fromBundle(it).shopType
            if (type == 0 ){
                fullPagerInit()
            }else {
                negarePagerInit()
            }
        }

        return binding.root
    }

    fun fullPagerInit () {
        val fragmentList = arrayListOf(BuyNegareFragment(), BuySubFragment())
        val adapter = SubNegarViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            fragmentList
        )
        viewPager.adapter = adapter
        TabLayoutMediator(binding.subTabLayout, viewPager) { tab, position ->
            when (position)
            {
                0 -> {
                    tab.text = requireContext().getString(R.string.buyCoin)
                }
                1 -> {
                    tab.text = requireContext().getString(R.string.buySub)
                }
            }
        }.attach()
    }

    fun negarePagerInit (){
        val fragmentList : List<Fragment> = arrayListOf(BuyNegareFragment())
        val adapter = SubNegarViewPagerAdapter(
            requireActivity().supportFragmentManager ,
            lifecycle ,
            fragmentList
        )
        viewPager.adapter = adapter
        TabLayoutMediator(binding.subTabLayout , viewPager){
                tab  , position->
            tab.text = requireContext().getString(R.string.buyCoin)
        }.attach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}