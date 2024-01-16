package tw.hsu.example.flight

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.widget.ListView
import tw.hsu.example.flight.data.FlightList

class FlightListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs) {

    fun addAdapter(container : FlightList, orientation : Int){

        var layoutResource = R.layout.layout_item_plane;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("FlightsPage LandScape")
            layoutResource = R.layout.layout_item_plane_flate;
        }

        dividerHeight = 0;


        this.adapter = FlightListAdapter(
            context,
            layoutResource,
            android.R.id.text1,
            container
        );

    }


}