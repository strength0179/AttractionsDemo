package tw.hsu.example.plane.service

import tw.hsu.example.plane.adapter.CurrencyAdapter
import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Currency
import tw.hsu.example.plane.presenter.CurrencyContainer

class CurrencyDataHolder : CurrencyContainer {

    var list: ArrayList<Currency>? = ArrayList();

    var adapter: CurrencyAdapter? = null;

    override fun currencys(): ArrayList<Currency> {
        return list!!;
    }

    override fun currecyNotifier(): CurrencyAdapter {
        if(adapter == null){
            adapter = CurrencyAdapter(currencys());
        }
        return adapter as CurrencyAdapter;
    }

}