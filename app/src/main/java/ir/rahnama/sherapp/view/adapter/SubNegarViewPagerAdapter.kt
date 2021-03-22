package ir.rahnama.sherapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class SubNegarViewPagerAdapter(fragmentManager: FragmentManager , lifecyle : Lifecycle , private var fragmentList : List<Fragment>)
    :FragmentStateAdapter(fragmentManager , lifecyle){

    override fun getItemCount(): Int  = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }


}