package tw.hsu.example.flight

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import tw.hsu.example.flight.data.Flight

class FlightListAdapter(
    context: Context,
    resource: Int,
    textViewResourceId: Int,
    objects: MutableList<Flight>
) : ArrayAdapter<Flight>(context, resource, textViewResourceId, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =  super.getView(position, convertView, parent);

        if(view is FlightItemView){
            val flightView = view as FlightItemView;
            getItem(position)?.let { flightView.bind(it) };
        }
        else if(view is FlightItemFlateView){
            val flightView = view as FlightItemFlateView;
            getItem(position)?.let { flightView.bind(it) };
        }


        return view;
    }

}