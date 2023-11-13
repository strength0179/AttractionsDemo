package tw.hsu.example.attractions.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.hsu.example.attractions.ui.fragment.AttractionDetailFragment
import tw.hsu.example.attractions.ui.fragment.AttractionUrlFragment
import tw.hsu.example.attractions.ui.fragment.AttractionsFragment
import tw.hsu.example.attractions.ui.fragment.MainFragment

class DemoCollectionAdapter(context : FragmentActivity) : FragmentStateAdapter(context) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment: Fragment =
            when(position){
                6 -> AttractionUrlFragment();
                5 -> AttractionDetailFragment();
                4 -> AttractionsFragment();
                3 -> MainFragment();
                2 -> AttractionsFragment();
                1 -> AttractionDetailFragment();
                0 -> AttractionUrlFragment();
                else -> {
                    MainFragment()
                }
            }

        return fragment
    }

}