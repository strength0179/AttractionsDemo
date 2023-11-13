package tw.hsu.example.attractions

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import tw.hsu.example.attractions.data.Attractions
import tw.hsu.example.attractions.data.GetAttractions
import tw.hsu.example.attractions.data.GetUsedHand
import tw.hsu.example.attractions.data.GetViewPager
import tw.hsu.example.attractions.ui.adapter.DemoCollectionAdapter
import tw.hsu.example.attractions.ui.fragment.AttractionDetailFragment
import tw.hsu.example.attractions.ui.fragment.AttractionUrlFragment
import tw.hsu.example.attractions.ui.fragment.AttractionsFragment
import tw.hsu.example.attractions.ui.fragment.MainFragment

class MainActivity : FragmentActivity(), GetAttractions, GetViewPager, GetUsedHand {

    private lateinit var viewPager: ViewPager2;

    var ats = Attractions();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        viewPager = ViewPager2(this);
        viewPager.setUserInputEnabled(false);
        viewPager.adapter = DemoCollectionAdapter(this);
        viewPager.currentItem = 3;

        setContentView(viewPager)

        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                if(viewPager.currentItem != 3){
                    if(isLeftHandUsed()){
                        viewPager.setCurrentItem(viewPager.currentItem +1 , true);
                    }
                    else{
                        viewPager.setCurrentItem(viewPager.currentItem -1 , true);
                    }

                }
                else{
                    finish();
                }
            }
        }

        onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )

    }


    override fun getAttractions(): Attractions {
        return ats;
    }

    override fun getPager(): ViewPager2 {
        return viewPager;
    }

    var lfu = false;
    override fun setLeftHandUsed(isLeftHand: Boolean) {
        this.lfu = isLeftHand;
    }

    override fun isLeftHandUsed(): Boolean {
        return  lfu
    }

}
