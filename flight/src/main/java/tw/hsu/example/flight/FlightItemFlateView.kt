package tw.hsu.example.flight

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import tw.hsu.example.flight.data.Flight

class FlightItemFlateView(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {


    val schedule: TextView
    val actually: TextView
    val number: TextView
    val gate: TextView
    val departure_id: TextView
    val departure_name: TextView
    val arrival_id: TextView
    val arrival_name: TextView
    val remark: TextView

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.layout_plane_view_flate, this)

        schedule = findViewById(android.R.id.text1)
        actually = findViewById(android.R.id.text2)
        number = findViewById(R.id.number);
        gate = findViewById(R.id.gate);
        departure_id = findViewById(R.id.departure_id);
        departure_name = findViewById(R.id.departure_name);
        arrival_id = findViewById(R.id.arrival_id);
        arrival_name = findViewById(R.id.arrival_name);
        remark = findViewById(R.id.remark)

    }

    fun bind(flight: Flight) {
        schedule.text = flight.ScheduleTime;
        actually.text = flight.ActualTime;
        number.text = flight.FlightNumber;
        gate.text = flight.Terminal + "/" + flight.Gate;
        departure_id.text = flight.DepartureAirportID;
        departure_name.text = flight.DepartureAirport;
        arrival_id.text = flight.ArrivalAirportID;
        arrival_name.text = flight.ArrivalAirport;
        remark.text = flight.Remark;
//        System.out.println(flight.FlightNumber + " " + flight.Remark);
        if(flight.Remark.contains("取消")){
            remark.setTextColor(Color.parseColor("#ff0000"));
        }
        else if(flight.Remark.contains("出發") || flight.Remark.contains("已飛")){
            remark.setTextColor(Color.parseColor("#00ff00"));
        }
        else if(flight.Remark.contains("更改")){
            remark.setTextColor(Color.parseColor("#eac100"));
        }
        else {
            remark.setTextColor(Color.parseColor("#000000"));
        }
    }

}