package tw.hsu.example.flight

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tw.hsu.example.flight.callback.ListCallback
import tw.hsu.example.flight.presenter.FlightContainer
import tw.hsu.example.flight.presenter.FlightPresenter
import tw.hsu.example.handler.HandlerProvider

class FlightsPageFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_fragment_flightlistview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val flightListView = view.findViewById<FlightListView>(R.id.listview);

        var presenter = context as FlightPresenter;
        var dataHolder : FlightContainer = presenter!!.arrivalContainer();

        val orientation = resources.configuration.orientation;
        flightListView.addAdapter(dataHolder.container(), orientation);

        dataHolder.container().setCallback(object : ListCallback {
            override fun callback() {
                var m = Message();
                m.obj = Runnable {
                    (flightListView.adapter as FlightListAdapter).notifyDataSetInvalidated();
                }
                (context as HandlerProvider).handler().sendMessage(m);
            }
        });

    }

}