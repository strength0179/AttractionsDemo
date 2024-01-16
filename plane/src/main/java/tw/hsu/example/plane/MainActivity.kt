package tw.hsu.example.plane

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import tw.hsu.example.handler.MainHandlerCallback
import tw.hsu.example.flight.presenter.FlightPresenter
import tw.hsu.example.plane.presenter.CurrencyContainer
import tw.hsu.example.flight.presenter.FlightContainer
import tw.hsu.example.handler.HandlerProvider
import tw.hsu.example.plane.service.ApiService
import tw.hsu.example.plane.service.CurrencyDataHolder
import tw.hsu.example.flight.data.FlightDataHolder
import tw.hsu.example.plane.presenter.CurrencyPresenter


class MainActivity : AppCompatActivity(), FlightPresenter, CurrencyPresenter, HandlerProvider {

    lateinit var apiService : ApiService;
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


    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiService = ApiService(this);

        setContentView(R.layout.layout_main)


    }

    override fun onResume() {
        super.onResume()
        apiService.onCreate();
    }

    override fun onPause() {
        super.onPause()
        apiService.onDestroy();
    }

    var mainHandler: Handler = Handler(Looper.getMainLooper(), MainHandlerCallback());


    override fun handler(): Handler {
        return mainHandler;
    }


}

