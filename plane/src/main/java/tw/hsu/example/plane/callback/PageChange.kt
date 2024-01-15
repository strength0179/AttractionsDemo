package tw.hsu.example.plane.callback


import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import tw.hsu.example.plane.R
import tw.hsu.example.plane.view.CurrencyPanel

class PageChange(context : Context) : OnPageChangeCallback() {

    var context : Context;

    init {
        this.context = context;
    }


    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

        System.out.println("PageSelected " + position);

        var panel =  (context as Activity).findViewById<CurrencyPanel>(R.id.panel);

        if(position != 2){
            panel.visibility = View.GONE;
            if(panel.edit.visibility == View.VISIBLE){
                panel.edit.visibility = View.GONE;
                panel.currencyResult.visibility = View.GONE;
                val imm = panel.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(
                    panel.edit,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
            }

        }
        else{
            panel.visibility = View.VISIBLE;
        }

    }

}