package tw.hsu.example.plane.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import tw.hsu.example.plane.R

class FlightRemarkView(context: Context?, attrs: AttributeSet?) : FrameLayout(context!!, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_view_remark, this);
    }

    fun setText(text : String){

        for(i in 0 .. childCount - 1){
            var tv : TextView = getChildAt(i) as TextView;
            tv.text = text;
        }

    }
}