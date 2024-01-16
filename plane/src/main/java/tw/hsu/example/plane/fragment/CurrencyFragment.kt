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
import tw.hsu.example.plane.presenter.CurrencyContainer
import tw.hsu.example.plane.presenter.CurrencyPresenter

class CurrencyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler: RecyclerView = view.findViewById(R.id.recyclerview);


        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.layoutManager = GridLayoutManager(view.context, 2);
        } else {
            recycler.layoutManager = LinearLayoutManager(view.context);
        }

        var presenter = context as CurrencyPresenter;

        var dataHolder : CurrencyContainer = presenter!!.currencyContainer();
        recycler.adapter = dataHolder.currecyNotifier();
        recycler.adapter!!.notifyItemRangeChanged(0, recycler.adapter!!.itemCount - 1);

    }
}