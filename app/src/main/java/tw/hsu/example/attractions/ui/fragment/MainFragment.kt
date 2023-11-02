package tw.hsu.example.attractions.ui.fragment

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import tw.hsu.example.attractions.R
import tw.hsu.example.attractions.data.Attraction
import tw.hsu.example.attractions.data.GetAttractions
import tw.hsu.example.attractions.data.GetUsedHand
import tw.hsu.example.attractions.data.GetViewPager
import tw.hsu.example.attractions.ui.theme.AttractionsTheme
import java.io.IOException


/**
 *
 * https://developer.android.com/jetpack/compose/components/fab?hl=zh-tw
 */
class MainFragment  : Fragment() {


    var lang_index = arrayOf(
        "zh-tw",
        "zh-cn",
        "en",
        "ja",
        "ko",
        "es",
        "id",
        "th",
        "vi"
    );

    var lang_selected = 0;

    var isLeftHandUsed = false;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        isLeftHandUsed = (context as GetUsedHand).isLeftHandUsed()
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

        callApi();

        return view;
    }

    fun callApi(){
        val client = OkHttpClient();
        val request = Request.Builder()
            .url("https://www.travel.taipei/open-api/" + lang_index[lang_selected] + "/Attractions/All")
            .header("Accept", "application/json")
            .build()

        System.out.println("API : " + request.url);

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                System.out.println("API Failure");
                e.printStackTrace();

            }

            override fun onResponse(call: Call, response: Response) {
                System.out.println("API success");
                var bodyString = response.body?.string();

                System.out.println(bodyString);

                try{
                    var responseJs = JSONObject(bodyString);
                    if(responseJs != null){

                        var list = (context as GetAttractions).getAttractions().attractions;
                        list.clear();
                        var data = responseJs.getJSONArray("data");
                        for(dd in 0..data.length() - 1){
                            var attraction = Attraction();
                            attraction.read(data.getJSONObject(dd));
                            list.add(attraction);
                        }

//                        (context as GetAttractions).getAttractions().selectedAttraction = Attraction();
//
//                        if(list.size > 0)
//                                (context as GetAttractions).getAttractions().selectedAttraction.copy(list.get(0));



                    }
                }
                catch (e : Exception){
                    Looper.prepare();
                    val lang_string = resources.getStringArray(R.array.lang_string)
                    Toast.makeText(context, "No Data for [" + lang_string[lang_selected] + "]", Toast.LENGTH_LONG).show();
                }

            }
        })

        var list = (context as GetAttractions).getAttractions().attractions;
        list.clear();
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        val lang_string = resources.getStringArray(R.array.lang_string)
        var isLeft by remember { mutableStateOf(isLeftHandUsed) }

        Box(modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.White)
            .padding(all = 10.dp)){


            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Demo",
                    modifier = modifier
                )


            }


            if(isLeft){

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End) {
                    FloatingActionButton(
                        onClick = {
                            isLeft = false;
                            isLeftHandUsed = false;
                            (context as GetUsedHand).setLeftHandUsed(isLeft) }) {
                        Icon(Icons.Filled.ArrowForward, "Floating action button.")
                    }
                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.Start) {
                    Spinner(
                        lang_string,
                        preselected = lang_string[lang_selected],
                        onSelectionChanged = { },
                        modifier = Modifier
                            .width(220.dp)
                            .padding(vertical = 10.dp)
                    )

                    ExtendedFloatingActionButton(
                        modifier = Modifier.width(220.dp),
                        onClick = { onClick() },

                        ) {
                        Icon(Icons.Filled.List, "Floating action button.")
                        Text(text = "Start")
                    }
                }
            }
            else{

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.Start) {
                    FloatingActionButton(onClick = {
                        isLeft = true;
                        isLeftHandUsed = true;
                        (context as GetUsedHand).setLeftHandUsed(isLeft)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Floating action button.")
                    }
                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End) {
                    Spinner(
                        lang_string,
                        preselected = lang_string[lang_selected],
                        onSelectionChanged = { },
                        modifier = Modifier
                            .width(220.dp)
                            .padding(vertical = 10.dp)
                    )

                    ExtendedFloatingActionButton(
                        modifier = Modifier.width(220.dp),
                        onClick = { onClick() },

                        ) {
                        Icon(Icons.Filled.List, "Floating action button.")
                        Text(text = "Start")
                    }
                }
            }



        }



    }

    private fun onClick() {

        if((context as GetAttractions).getAttractions().attractions.size > 0)
                (context as GetViewPager).getPager().setCurrentItem(1, true)
        else{
            Toast.makeText(context, "LOADONG... PLEASE WAIT.", Toast.LENGTH_LONG).show();
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Spinner(
        list: Array<String>,
        preselected: String,
        onSelectionChanged: (myData: String) -> Unit,
        modifier: Modifier = Modifier
    ) {

        var selected by remember { mutableStateOf(preselected) }
        var expanded by remember { mutableStateOf(false) } // initial value

        OutlinedCard(
            modifier = modifier.clickable {
                expanded = !expanded
            }
        ) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {

                Text(
                    text = selected,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = modifier  // delete this modifier and use .wrapContentWidth() if you would like to wrap the dropdown menu around the content
                ) {
                    list.forEach { listEntry ->

                        DropdownMenuItem(
                            onClick = {
                                selected = listEntry
                                expanded = false
                                onSelectionChanged(selected)

                                lang_selected = list.indexOf(selected);

                                callApi();

                            },
                            text = {
                                Text(
                                    text = listEntry,
                                    modifier = Modifier
                                        //.wrapContentWidth()  //optional instad of fillMaxWidth
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                )
                            },
                        )
                    }
                }

            }
        }
    }


}



