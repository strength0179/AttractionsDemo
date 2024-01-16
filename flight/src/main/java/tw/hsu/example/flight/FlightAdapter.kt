package tw.hsu.example.flight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.hsu.example.flight.data.Flight

class FlightAdapter(private val dataSet: ArrayList<Flight>) : RecyclerView.Adapter<FlightHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_plane, parent, false)

        return FlightHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val safePosition = holder.adapterPosition
        val flight : Flight = dataSet[safePosition];
        holder.flightItemView.bind(flight);
    }

}

class FlightHolder (view: View) : RecyclerView.ViewHolder(view){

    val flightItemView : FlightItemView;

    init {
        // Define click listener for the ViewHolder's View
        flightItemView = view as FlightItemView;
    }

}