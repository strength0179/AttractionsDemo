package tw.hsu.example.flight.presenter

import tw.hsu.example.flight.FlightAdapter
import tw.hsu.example.flight.data.FlightList

interface FlightContainer {

//    fun setContainer(list : ArrayList<Flight>);

    fun container(): FlightList;

//    fun setNotifier(adapter : FlightAdapter);

    fun notifier() : FlightAdapter;



    fun release();

}