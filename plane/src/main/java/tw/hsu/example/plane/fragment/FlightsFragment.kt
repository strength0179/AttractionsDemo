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
import tw.hsu.example.plane.R
import tw.hsu.example.plane.presenter.ContainPresenter
import tw.hsu.example.plane.presenter.FlightContainer

class FlightsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("type") }?.apply {
            val recycler: RecyclerView = view.findViewById(R.id.recyclerview);

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recycler.layoutManager = GridLayoutManager(view.context, 2);
            } else {
                recycler.layoutManager = LinearLayoutManager(view.context);
            }

            var presenter = context as ContainPresenter;

            if(requireArguments().getInt("type") == 1){

                var dataHolder : FlightContainer = presenter!!.arrivalContainer();
                recycler.adapter = dataHolder.notifier();
            }
            else{
                var dataHolder : FlightContainer = presenter!!.departureContainer();
                recycler.adapter = dataHolder.notifier();
            }


        }
    }

}