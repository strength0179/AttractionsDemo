package tw.hsu.example.plane.presenter

import tw.hsu.example.plane.adapter.FlightAdapter
import tw.hsu.example.plane.data.Flight

interface FlightContainer {

//    fun setContainer(list : ArrayList<Flight>);

    fun container(): ArrayList<Flight>;

//    fun setNotifier(adapter : FlightAdapter);

    fun notifier() : FlightAdapter;

    fun release();

}