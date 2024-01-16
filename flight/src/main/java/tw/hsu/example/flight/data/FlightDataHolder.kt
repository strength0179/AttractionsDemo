package tw.hsu.example.flight.data

import tw.hsu.example.flight.FlightAdapter
import tw.hsu.example.flight.data.FlightList
import tw.hsu.example.flight.presenter.FlightContainer

class FlightDataHolder : FlightContainer {

    var list: FlightList? = null;

    var adapter: FlightAdapter? = null;

//    override fun setContainer(list: ArrayList<Flight>) {
////        this.list = list;
//    }

    override fun container(): FlightList {
        if(list == null){
            list = FlightList()
        };
        return list!!;
    }

//    override fun setNotifier(adapter: FlightAdapter) {
//        this.adapter = adapter;
//    }

    override fun notifier(): FlightAdapter {
        if(adapter == null){
            adapter = FlightAdapter(container())
        } ;
        return adapter!!;
    }

    override fun release() {
        System.out.println("Container and Notifier Release");
        list = null;
        adapter = null;
    }
}