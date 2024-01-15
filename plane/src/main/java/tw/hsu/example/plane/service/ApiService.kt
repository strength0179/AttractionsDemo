package tw.hsu.example.plane.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import org.json.JSONArray
import org.json.JSONObject
import tw.hsu.example.api.ApiError
import tw.hsu.example.api.ApiSuccess
import tw.hsu.example.api.CallApi
import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Flight
import tw.hsu.example.plane.presenter.ContainPresenter
import tw.hsu.example.plane.presenter.FlightContainer


class ApiService {

    lateinit var context : Context;

    constructor(context : Context){
        this.context = context;
    }

    val api : String = "https://e-traffic.taichung.gov.tw/DataAPI/api/AirPortFlyAPI";
    val apis : Array<String> = arrayOf(
        "/A/TPE",
        "/D/TPE",
        "/A/TSA",
        "/D/TSA",
        "/A/KHH",
        "/D/KHH",
        "/A/RMQ",
        "/D/RMQ"
    );

    val api2 : String = "https://api.freecurrencyapi.com/v1/latest?" +
            "apikey=fca_live_xLdDA9WXZYWkeH79xo5QPtWaMbVNDG2oYqxPOIq1&currencies=JPY,USD,CNY,EUR,AUD,KRW";

    var apiRun : Boolean = false;


    fun onCreate() {

        apiRun =true;

        var resultReciever = ArrayList<ApiResult>();

        for(i in 0 .. apis.size - 1){
            resultReciever.add(ApiResult(context, apis[i]));
        }

        Thread{
            Thread.sleep(3000);

            while(apiRun){


                var presenter = (context as ContainPresenter);
                presenter.arrivalContainer().container().clear();
                presenter.departureContainer().container().clear();


                for(i in 0 .. apis.size - 1){
                    Thread{
                        var apiResult = resultReciever[i];
                        CallApi(api+ apis[i]).setApiSuccess(apiResult).setApiError(apiResult).callApi();
                    }.start();
                }

                Thread.sleep(30000);

            }

        }.start();

        Thread{
            var apiResult = CurrencyResult(context);
            CallApi(api2).setApiSuccess(apiResult).setApiError(apiResult).callApi();

        }.start();

    }

    fun onDestroy() {
        apiRun = false;
    }

}