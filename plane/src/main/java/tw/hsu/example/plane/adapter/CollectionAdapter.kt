package tw.hsu.example.plane.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CollectionAdapter(act : FragmentActivity, val fragments : ArrayList<Fragment>) : FragmentStateAdapter(act) {


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment = fragments.get(position);
        fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putInt("type", position)
        }
        return fragment

    }

}