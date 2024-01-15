package tw.hsu.example.plane

import android.app.Application
import tw.hsu.example.plane.presenter.ContainPresenter
import tw.hsu.example.plane.presenter.CurrencyContainer
import tw.hsu.example.plane.presenter.FlightContainer
import tw.hsu.example.plane.service.CurrencyDataHolder
import tw.hsu.example.plane.service.FlightDataHolder

class FlightApp : Application(), ContainPresenter {
    var arriveDataHolder : FlightDataHolder = FlightDataHolder();
    var departureDataHolder : FlightDataHolder = FlightDataHolder();
    var currencyDataHolder : CurrencyDataHolder = CurrencyDataHolder();


    override fun arrivalContainer(): FlightContainer {
        return arriveDataHolder;
    }

    override fun departureContainer(): FlightContainer {
        return departureDataHolder;
    }

    override fun currencyContainer(): CurrencyContainer {
        return currencyDataHolder;
    }
}