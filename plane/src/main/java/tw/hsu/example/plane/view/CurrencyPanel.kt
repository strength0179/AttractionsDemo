package tw.hsu.example.plane.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import tw.hsu.example.plane.R

class CurrencyPanel(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var currency0 : TextView;
    var currency1 : TextView;
    var edit : EditText;
    var currencyResult : TextView;

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_currency_panel, this);
        currency0 = findViewById<TextView>(android.R.id.text1);
        currency1 = findViewById<TextView>(android.R.id.text2);
        currencyResult = findViewById<TextView>(android.R.id.textAssist);
        edit = findViewById(android.R.id.edit);


    }
}