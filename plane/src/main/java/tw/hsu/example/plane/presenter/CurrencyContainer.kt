package tw.hsu.example.plane.presenter

import tw.hsu.example.plane.adapter.CurrencyAdapter
import tw.hsu.example.plane.data.Currency

interface CurrencyContainer {

    fun currencys(): ArrayList<Currency>;

    fun currecyNotifier() : CurrencyAdapter;


}