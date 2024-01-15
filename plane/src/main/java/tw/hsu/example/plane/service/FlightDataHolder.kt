package tw.hsu.example.plane.service

import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Flight
import tw.hsu.example.plane.presenter.FlightContainer

class FlightDataHolder : FlightContainer {

    var list: ArrayList<Flight>? = null;

    var adapter: FlightAdapter? = null;

//    override fun setContainer(list: ArrayList<Flight>) {
////        this.list = list;
//    }

    override fun container(): ArrayList<Flight> {
        if(list == null)list = ArrayList();
        return list!!;
    }

//    override fun setNotifier(adapter: FlightAdapter) {
//        this.adapter = adapter;
//    }

    override fun notifier(): FlightAdapter {
        if(adapter == null)adapter = FlightAdapter(container()) ;
        return adapter!!;
    }

    override fun release() {
        System.out.println("Container and Notifier Release");
        list = null;
        adapter = null;
    }
}