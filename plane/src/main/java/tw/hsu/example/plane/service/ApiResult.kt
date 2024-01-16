package tw.hsu.example.plane.service

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import tw.hsu.example.api.ApiError
import tw.hsu.example.api.ApiSuccess
import tw.hsu.example.flight.data.Flight
import tw.hsu.example.flight.presenter.FlightPresenter

class ApiResult : ApiSuccess, ApiError {

    var context : Context;
    var api : String;
    var presenter : FlightPresenter;


    var list : ArrayList<Flight> = ArrayList();
//    var listNew : ArrayList<Flight> = ArrayList();
    var listLast : ArrayList<Flight> = ArrayList();
    var gotNewResult: Boolean = false;



    constructor(c : Context, a : String){
        context = c;
        api = a;
        presenter = c as FlightPresenter;
    }


    override fun error(code: Int, msg: String) {
    }

    override fun success(result: String) {
        var responseJs : JSONArray? = null;

        responseJs = JSONArray(result);

        if(responseJs != null){

            System.out.println("API[" + api + "] result ");
            parseJsonArray(responseJs);
            gotNewResult = true;
        }
        else{
            return;
        }
    }

    fun parseJsonArray(array : JSONArray){

        System.out.println(api + " jsonArray .. " + array.length());

        for(i in 0..array.length() - 1){

            var js : JSONObject = array.getJSONObject(i);

//            System.out.println(api + " [" + i + "] parse json " + js.toString());

            var temp = Flight();
            temp.AirlineID = js.getString("AirlineID");
            temp.FlightNumber = js.getString("FlightNumber");
//            listNew.add(temp);
            list.add(temp);


//            for(j in 0 .. listLast.size - 1){
//                var f = listLast.get(j);
//                if(f.FlightNumber.equals(temp.FlightNumber)){
//                    temp = f;
//                    listNew.remove(temp);
//                }
//            }
//
//            listLast.remove(temp);

            if(!js.isNull("ActualTime")) temp.ActualTime = js.getString("ActualTime");
            if(!js.isNull("ScheduleTime"))temp.ScheduleTime = js.getString("ScheduleTime");
            if(!js.isNull("Terminal"))temp.Terminal = js.getString("Terminal");
            if(!js.isNull("Gate"))temp.Gate = js.getString("Gate");
            if(!js.isNull("Remark"))temp.Remark = js.getString("Remark");
            if(!js.isNull("DepartureAirportID"))temp.DepartureAirportID = js.getString("DepartureAirportID");
            if(!js.isNull("DepartureAirport"))temp.DepartureAirport = js.getString("DepartureAirport");
            if(!js.isNull("ArrivalAirportID"))temp.ArrivalAirportID = js.getString("ArrivalAirportID");
            if(!js.isNull("ArrivalAirport"))temp.ArrivalAirport = js.getString("ArrivalAirport");


        }

    }

    fun next(){
        listLast.clear();
        listLast.addAll(list);
        list.clear();
//        listNew.clear();
        gotNewResult = false;
    }

}