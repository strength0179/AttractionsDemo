package tw.hsu.example.plane.service

import android.content.Context
import org.json.JSONObject
import tw.hsu.example.api.ApiError
import tw.hsu.example.api.ApiSuccess
import tw.hsu.example.plane.data.Currency
import tw.hsu.example.plane.presenter.CurrencyContainer
import tw.hsu.example.plane.presenter.CurrencyPresenter

class CurrencyResult(context : Context) : ApiSuccess, ApiError {

    var currencyContainer : CurrencyContainer;
    init {
        currencyContainer = (context as CurrencyPresenter).currencyContainer();
    }

    override fun error(code: Int, msg: String) {
    }

    override fun success(result: String) {
        var responseJs : JSONObject = JSONObject(result);

        if(responseJs != null && responseJs.has("data")){

            var data = responseJs.getJSONObject("data");
            var list = currencyContainer.currencys();
            var keys = data.keys();
            keys.forEachRemaining {
                list.add(Currency(it, data.getString(it)));
                System.out.println("Currency[" + it + "] add");
            }

            currencyContainer.currecyNotifier().notifyItemRangeChanged(0, list.size - 1);

        }
        else{
            return;
        }
    }

}