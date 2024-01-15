package tw.hsu.example.plane.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.hsu.example.plane.fragment.CurrencyFragment
import tw.hsu.example.plane.fragment.FlightsFragment

class CollectionAdapter(act : FragmentActivity) : FragmentStateAdapter(act) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        if(position == 2){
            val fragment = CurrencyFragment()
            return fragment
        }
        else{
            val fragment = FlightsFragment()
            fragment.arguments = Bundle().apply {
                // The object is just an integer.
                putInt("type", position)
            }
            return fragment
        }

    }

}