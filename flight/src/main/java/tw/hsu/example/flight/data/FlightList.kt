package tw.hsu.example.flight.data

import tw.hsu.example.flight.callback.ListCallback


class FlightList : ArrayList<Flight>() {

    var listCallback : ListCallback? = null;

    fun setCallback(callback: ListCallback){
        listCallback = callback;
    }

    override fun addAll(elements: Collection<Flight>): Boolean {
        var b = super.addAll(elements);

        if(listCallback != null)listCallback?.callback();

        return b;
    }

}