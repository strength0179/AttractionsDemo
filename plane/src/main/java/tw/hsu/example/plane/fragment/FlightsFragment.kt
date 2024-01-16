package tw.hsu.example.plane.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.hsu.example.flight.presenter.FlightPresenter
import tw.hsu.example.flight.presenter.FlightContainer
import tw.hsu.example.plane.R

class FlightsFragment : Fragment(){

    var recycler: RecyclerView? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_fragment_recyclerview, container, false)
    }

    override fun onResume() {
        super.onResume()

        arguments?.takeIf { it.containsKey("type") }?.apply {

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recycler?.layoutManager = GridLayoutManager(context, 2);
            } else {
                recycler?.layoutManager = LinearLayoutManager(context);
            }

            var presenter = context as FlightPresenter;

            if(requireArguments().getInt("type") == 1){

                var dataHolder : FlightContainer = presenter!!.arrivalContainer();
                recycler?.adapter = dataHolder.notifier();
            }
            else{
                var dataHolder : FlightContainer = presenter!!.departureContainer();
                recycler?.adapter = dataHolder.notifier();
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.recyclerview);

    }

}