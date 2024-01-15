package tw.hsu.example.plane.service

import android.content.Context
import android.os.Message
import org.json.JSONArray
import org.json.JSONObject
import tw.hsu.example.api.ApiError
import tw.hsu.example.api.ApiSuccess
import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Flight
import tw.hsu.example.plane.presenter.ContainPresenter
import tw.hsu.example.plane.presenter.HandlerProvider

class ApiResult : ApiSuccess, ApiError {

    var context : Context;
    var api : String;
    var presenter : ContainPresenter;

    var listHolder : HashMap<String, Flight> = HashMap();

    var list : ArrayList<Flight>;
    var adapter : FlightAdapter;


    constructor(c : Context, a : String){
        context = c;
        api = a;
        presenter = c as ContainPresenter;

        if(api.startsWith("/A")){
            list = presenter.arrivalContainer().container();
            adapter = presenter.arrivalContainer().notifier();
        }
        else{
            list = presenter.departureContainer().container();
            adapter = presenter.departureContainer().notifier();
        }
    }


    override fun error(code: Int, msg: String) {
    }

    override fun success(result: String) {
        var responseJs : JSONArray? = null;

        responseJs = JSONArray(result);

        if(responseJs != null){

            System.out.println("API[" + api + "] result ");
            parseJsonArray(list, responseJs, adapter);
        }
        else{
            return;
        }
    }

    fun parseJsonArray(sourcelist : ArrayList<Flight>, array : JSONArray, adapter: FlightAdapter){

        System.out.println(api + " jsonArray .. " + array.length());

        var listTmp : ArrayList<Flight> = ArrayList();
        var index = -1;

        for(i in 0..array.length() - 1){

            var js : JSONObject = array.getJSONObject(i);

//            System.out.println(api + " [" + i + "] parse json " + js.toString());

            var temp = Flight();
            temp.AirlineID = js.getString("AirlineID");
            temp.FlightNumber = js.getString("FlightNumber");
            listTmp.add(temp);


            index = -1;
            for(j in 0 .. sourcelist.size - 1){
                var f = sourcelist.get(j);
                if(f.FlightNumber.equals(temp.FlightNumber)){
                    listTmp.remove(temp);
                    temp = f;
                    index = j;
                }
            }

            if(!js.isNull("ActualTime")) temp.ActualTime = js.getString("ActualTime");
            if(!js.isNull("ScheduleTime"))temp.ScheduleTime = js.getString("ScheduleTime");
            if(!js.isNull("Terminal"))temp.Terminal = js.getString("Terminal");
            if(!js.isNull("Gate"))temp.Gate = js.getString("Gate");
            if(!js.isNull("Remark"))temp.Remark = js.getString("Remark");
            if(!js.isNull("DepartureAirportID"))temp.DepartureAirportID = js.getString("DepartureAirportID");
            if(!js.isNull("DepartureAirport"))temp.DepartureAirport = js.getString("DepartureAirport");
            if(!js.isNull("ArrivalAirportID"))temp.ArrivalAirportID = js.getString("ArrivalAirportID");
            if(!js.isNull("ArrivalAirport"))temp.ArrivalAirport = js.getString("ArrivalAirport");


            if(index != -1){
                adapter.notifyItemChanged(index);
            }
            else{
//                System.out.println(api + " [" + i + "] parse json -3 ...")
//                sourcelist.add(temp);
//                System.out.println(api + " [" + i + "] parse json -4 ...")
//
//                adapter.notifyItemInserted(sourcelist.size - 1);
////                adapter.notifyDataSetChanged();
//                System.out.println(api + " [" + i + "] parse json -5 ...")
            }

        }

        index = sourcelist.size;
        sourcelist.addAll(listTmp);

//        sourcelist.sortBy {
//            it.ScheduleTime + it.ActualTime;
//        }

        var m = Message();
        m.obj = Runnable {
            adapter.notifyItemRangeChanged(0, sourcelist.size- 1);
        }

        (context as HandlerProvider).handler().sendMessage(m);

        System.out.println(api + " end parse " + sourcelist.size + "/" + index + "/" + listTmp.size);


    }
}