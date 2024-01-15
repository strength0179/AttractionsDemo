package tw.hsu.example.plane.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.hsu.example.plane.R
import tw.hsu.example.plane.data.Flight

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
        var flight : Flight= dataSet[safePosition];
        holder.schedule.text = flight.ScheduleTime;
        holder.actually.text = flight.ActualTime;
        holder.number.text = flight.FlightNumber;
        holder.gate.text = flight.Terminal + "/" + flight.Gate;
        holder.departure_id.text = flight.DepartureAirportID;
        holder.departure_name.text = flight.DepartureAirport;
        holder.arrival_id.text = flight.ArrivalAirportID;
        holder.arrival_name.text = flight.ArrivalAirport;
        holder.remark.text = flight.Remark;
//        System.out.println(flight.FlightNumber + " " + flight.Remark);
        if(flight.Remark.contains("取消")){
            holder.remark.setTextColor(Color.parseColor("#ff0000"));
        }
        else if(flight.Remark.contains("出發") || flight.Remark.contains("已飛")){
            holder.remark.setTextColor(Color.parseColor("#00ff00"));
        }
        else if(flight.Remark.contains("更改")){
            holder.remark.setTextColor(Color.parseColor("#eac100"));
        }
        else {
            holder.remark.setTextColor(Color.parseColor("#000000"));
        }
    }

}

class FlightHolder (view: View) : RecyclerView.ViewHolder(view){

    val schedule: TextView
    val actually: TextView
    val number: TextView
    val gate: TextView
    val departure_id: TextView
    val departure_name: TextView
    val arrival_id: TextView
    val arrival_name: TextView
    val remark: TextView
//    var remarkview: FlightRemarkView

    init {
        // Define click listener for the ViewHolder's View
        schedule = view.findViewById(android.R.id.text1)
        actually = view.findViewById(android.R.id.text2)
        number = view.findViewById(R.id.number);
        gate = view.findViewById(R.id.gate);
        departure_id = view.findViewById(R.id.departure_id);
        departure_name = view.findViewById(R.id.departure_name);
        arrival_id = view.findViewById(R.id.arrival_id);
        arrival_name = view.findViewById(R.id.arrival_name);
//        remarkview = view.findViewById(R.id.remark)
        remark = view.findViewById(R.id.remark)
    }

}