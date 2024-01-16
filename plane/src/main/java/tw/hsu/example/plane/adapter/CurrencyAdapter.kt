package tw.hsu.example.plane.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import tw.hsu.example.plane.R
import tw.hsu.example.plane.data.Currency
import tw.hsu.example.plane.view.CurrencyPanel

class CurrencyAdapter(private val dataSet: ArrayList<Currency>) : RecyclerView.Adapter<CurrencyViewHolder>(){

    var currency0 : Currency? = null;
    var currency1 : Currency? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_currency, parent, false)

        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

        holder.name.text = dataSet[position].name;
        holder.rate.text = dataSet[position].rate;
        holder.layout.setOnClickListener {

            var panel = (holder.layout.context as Activity).findViewById<CurrencyPanel>(R.id.panel);

            if(currency0 != null && currency1 != null){
                currency0 = null;
                currency1 = null;
                panel.currency0.text = "";
                panel.currency1.text = "";

                panel.edit.editableText.clear();
                panel.edit.visibility = View.GONE;
                panel.currencyResult.visibility = View.GONE;

                val imm = holder.layout.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(
                    panel.edit,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);

            }

            if(currency0 == null){
                currency0 = dataSet[position];
                panel.currency0.text = currency0!!.name;

            }
            else if(currency1 == null){
                currency1 = dataSet[position];
                if(currency1 == currency0){
                    currency1 = null;
                }
                else{

                    panel.currency1.text = currency1!!.name;

                    panel.edit.visibility = View.VISIBLE;
                    panel.currencyResult.visibility = View.VISIBLE;
                    panel.currencyResult.text = "";

                    if(panel.edit.requestFocus()){
                        val imm = holder.layout.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(
                            panel.edit,
                            InputMethodManager.SHOW_IMPLICIT);


                        panel.edit.addTextChangedListener {
                            var text = it.toString();

                            if(text == ""){
                                panel.currencyResult.text = "0";
                            }
                            else{
                                var number : Float = text.toFloat();
                                var c0 : Float = currency0!!.rate.toFloat();
                                var c1 : Float = currency1!!.rate.toFloat();
                                panel.currencyResult.text = (number * c1/ c0).toString();

                            }

                        }
                    }

                }

            }


        }

    }

}

class CurrencyViewHolder (view: View) : RecyclerView.ViewHolder(view){

    var layout : View;
    var name : TextView;
    var rate : TextView;
//    var edit : EditText;

    init {
        layout = view;
        name = view.findViewById(android.R.id.text1);
        rate = view.findViewById(android.R.id.text2);
//        edit = view.findViewById(android.R.id.edit);
    }

}