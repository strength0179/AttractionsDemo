package tw.hsu.example.attractions.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.hsu.example.attractions.ui.fragment.AttractionDetailFragment
import tw.hsu.example.attractions.ui.fragment.AttractionUrlFragment
import tw.hsu.example.attractions.ui.fragment.AttractionsFragment
import tw.hsu.example.attractions.ui.fragment.MainFragment

class DemoCollectionAdapter(context : FragmentActivity) : FragmentStateAdapter(context) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment: Fragment =
            when(position){
                3 -> AttractionUrlFragment();
                2 -> AttractionDetailFragment();
                1 -> AttractionsFragment();
                else -> {
                    MainFragment()
                }
            }

        return fragment
    }

}