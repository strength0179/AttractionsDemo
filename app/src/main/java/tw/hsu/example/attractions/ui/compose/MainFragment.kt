package tw.hsu.example.attractions.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import tw.hsu.example.attractions.ui.theme.AttractionsTheme
import java.io.IOException


class MainFragment  : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ComposeView(requireContext());
        view.apply {
            setContent {
                AttractionsTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.onBackground
                    ) {
                        Greeting("Android")
                    }
                }
            }
        }

        val client = OkHttpClient();
        val request = Request.Builder()
            .url("https://www.travel.taipei/open-api/zh-tw/Attractions/All")
            .header("Accept", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                System.out.println("API Failure");
                e.printStackTrace();

            }

            override fun onResponse(call: Call, response: Response) {
                System.out.println("API success");
//                System.out.println(response.body?.string());

                var responseJs = JSONObject(response.body?.string());
                if(responseJs != null){
                    var data = responseJs.getJSONArray("data");
                    for(dd in 0..data.length() - 1){

                        System.out.println(data.getJSONObject(dd).toString());

                    }
                }
            }
        })

        return view;
    }


}

private fun Call.enqueue() {
    TODO("Not yet implemented")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column {
        Text(
            text = "Hello, $name!",
            modifier = modifier
        )

        Text(
            text = "Hello again, $name!",
            modifier = modifier
        )
    }

}