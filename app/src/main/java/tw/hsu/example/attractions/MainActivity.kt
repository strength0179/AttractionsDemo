package tw.hsu.example.attractions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import tw.hsu.example.attractions.ui.compose.MainFragment

class MainActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        viewPager = ViewPager2(this);
        viewPager.adapter = DemoCollectionAdapter(this);

        setContentView(viewPager)

    }


}

class DemoCollectionAdapter(context : FragmentActivity) : FragmentStateAdapter(context) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment: MainFragment =
        when(position){
            1 -> MainFragment();
            2 -> MainFragment();
            else -> {MainFragment()}
        }

        return fragment
    }
}