package tw.hsu.example.plane.service

import android.content.Context
import android.os.Message
import tw.hsu.example.api.CallApi
import tw.hsu.example.flight.data.Flight
import tw.hsu.example.flight.presenter.FlightPresenter
import tw.hsu.example.handler.HandlerProvider


class ApiService {

    lateinit var context : Context;

    constructor(context : Context){
        this.context = context;
    }

    val api : String = "https://e-traffic.taichung.gov.tw/DataAPI/api/AirPortFlyAPI";
    val apis : Array<String> = arrayOf(
        "/A/TPE",
        "/D/TPE",
//        "/A/TSA",
//        "/D/TSA",
//        "/A/KHH",
//        "/D/KHH",
//        "/A/RMQ",
//        "/D/RMQ"
    );

    val api2 : String = "https://api.freecurrencyapi.com/v1/latest?" +
            "apikey=fca_live_xLdDA9WXZYWkeH79xo5QPtWaMbVNDG2oYqxPOIq1&currencies=JPY,USD,CNY,EUR,AUD,KRW";

    var apiRun : Boolean = false;
    var resultReciever = ArrayList<ApiResult>();


    fun onCreate() {

        apiRun =true;

        resultReciever.clear();

        for(i in 0 .. apis.size - 1){
            resultReciever.add(ApiResult(context, apis[i]));
        }

        Thread{
//            Thread.sleep(3000);

            while(apiRun){

                for(i in 0 .. apis.size - 1){
                    Thread{
                        var apiResult = resultReciever[i];
                        apiResult.next();
                        CallApi(api+ apis[i]).setApiSuccess(apiResult).setApiError(apiResult).callApi();
                    }.start();
                }

                for(i in 0 .. 15){

//                    System.out.println("ApiThread sleep 20 sec");
                    Thread.sleep(20000);
                }

                if(!apiRun)
                    break;
                System.out.println("ApiThread repeat");

//                Thread.sleep(280000);

            }

        }.start();


        Thread{

            Thread.sleep(3000);

            var presenter = (context as FlightPresenter);
            var arrivalList = presenter.arrivalContainer().container();
            var depatureList = presenter.departureContainer().container();
            var arrivalNotifier = presenter.arrivalContainer().notifier();
            var departureNotifier = presenter.arrivalContainer().notifier();
            var removeFromArrivalList = ArrayList<Flight>();
            var removeFromDepartureList = ArrayList<Flight>();
            var newArrivalList = ArrayList<Flight>();
            var newDepartureList = ArrayList<Flight>();

            while(apiRun){

                for(result in resultReciever){

                    if(result.gotNewResult){
                        if(result.api.startsWith("/A")){
//                            removeFromArrivalList.addAll(result.listLast);
                            newArrivalList.addAll(result.list);
                        }
                        else{
//                            removeFromDepartureList.addAll(result.listLast);
                            newDepartureList.addAll(result.list);
                        }
                        result.next();
                    }

                }


                var fresh : ArrayList<ResultDataFresh> = ArrayList();
//                    fresh.add(ResultDataFresh(context, removeFromArrivalList, arrivalList, arrivalNotifier, ResultDataFresh.Fresh));
//                    fresh.add(ResultDataFresh(context, removeFromDepartureList, depatureList, departureNotifier, ResultDataFresh.Fresh));
                if(newArrivalList.size > 0)fresh.add(ResultDataFresh(context, newArrivalList, arrivalList, arrivalNotifier, ResultDataFresh.Add));
                if(newDepartureList.size > 0)fresh.add(ResultDataFresh(context, newDepartureList, depatureList, departureNotifier, ResultDataFresh.Add));
//                fresh.add(ResultDataFresh(context, removeFromArrivalList, arrivalList, arrivalNotifier, ResultDataFresh.Remove));
//                fresh.add(ResultDataFresh(context, removeFromDepartureList, depatureList, departureNotifier, ResultDataFresh.Remove));
//
//
                for(f in fresh){
                    var m = Message();
                    m.obj = f;
                    (context as HandlerProvider).handler().sendMessage(m);
                    Thread.sleep(200);
                }

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