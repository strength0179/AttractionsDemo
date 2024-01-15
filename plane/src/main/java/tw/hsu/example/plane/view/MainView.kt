package tw.hsu.example.plane.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import tw.hsu.example.plane.R
import tw.hsu.example.plane.adapter.CollectionAdapter
import tw.hsu.example.plane.callback.PageChange

class MainView : RelativeLayout {

    var viewPager: ViewPager2

    var collectionAdapter: CollectionAdapter
    var tabLayout: TabLayout

    var tabStrings : Array<String> = arrayOf("起飛班機", "抵達班機", "匯率");

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_main_view, this);

        viewPager = findViewById(R.id.pager)

        tabLayout = this.findViewById(R.id.tab_layout)

        collectionAdapter = CollectionAdapter(context as FragmentActivity)
        viewPager.adapter = collectionAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabStrings[position];
        }.attach()

        viewPager.registerOnPageChangeCallback(PageChange(context));
    }

}