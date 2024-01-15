package tw.hsu.example.plane.presenter

import tw.hsu.example.plane.adapter.CurrencyAdapter
import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Currency
import tw.hsu.example.plane.data.Flight

interface CurrencyContainer {

    fun currencys(): ArrayList<Currency>;

    fun currecyNotifier() : CurrencyAdapter;


}