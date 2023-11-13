package tw.hsu.example.attractions.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import tw.hsu.example.attractions.data.Attraction
import tw.hsu.example.attractions.data.GetAttractions
import tw.hsu.example.attractions.data.GetUsedHand
import tw.hsu.example.attractions.data.GetViewPager
import tw.hsu.example.attractions.ui.theme.AttractionsTheme


class AttractionsFragment  : Fragment() {

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
                        BasicDataBinding()
//                        AttractionsList("Android", arrayList = (context as GetAttractions).getAttractions().attractions)
                    }
                }
            }
        }



        return view;
    }

    @Composable
    fun BasicDataBinding() {

        var datas by remember { mutableStateOf((context as GetAttractions).getAttractions().attractions) }

        System.out.println("Detail BasicDataBinding List : " + datas.size);

        Box(modifier = Modifier
            .fillMaxSize(1f)
            .padding(all = 10.dp)){
            AttractionsList("Android", arrayList = datas)

            if((context as GetUsedHand).isLeftHandUsed()){

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.Start) {
                    FloatingActionButton(
                        onClick = {
                            (context as GetViewPager).getPager().setCurrentItem(3, true)
                        }) {
                        Icon(Icons.Filled.Menu, "Floating action button.")
                    }
                }
            }
            else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End) {
                    FloatingActionButton(onClick = {
                        (context as GetViewPager).getPager().setCurrentItem(3, true)
                    }) {
                        Icon(Icons.Filled.Menu, "Floating action button.")
                    }
                }
            }


        }


    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun AttractionsList(name: String, modifier: Modifier = Modifier,  arrayList : ArrayList<Attraction>) {

        LazyColumn(
            state = rememberLazyListState(1),
            contentPadding = PaddingValues(horizontal = 5.dp),
        ) {

            item {
                Box(modifier = Modifier.height(400.dp)) {
                    Text(
                        text = "No more data",
                        modifier = modifier.align(Alignment.Center)
                    )
                }

            }

            items(arrayList.size){
                Row(
                    Modifier
                        .height(120.dp)
                        .padding(horizontal = 1.dp, vertical = 6.dp)) {

                    Column (Modifier.weight(0.3f)){
                        if(arrayList.get(it).images?.size!!.compareTo(0) > 0)
                            GlideImage(
                                model = arrayList.get(it).images?.get(0),
                                contentDescription = "demo",
                                modifier = Modifier.fillMaxSize(1f),
                                contentScale = ContentScale.Crop)
                        else
                            Box(modifier = Modifier
                                .fillMaxSize(1f)
                                .background(color = Color.Gray)){
                                Text(text = "No\r\nImage", modifier = Modifier.align(Alignment.Center))
                            }

                    }



                    Column (
                        Modifier
                            .weight(0.6f)
                            .padding(horizontal = 8.dp, vertical = 2.dp)) {
                        Text(
                            text = "${arrayList.get(it).name}!",
                            modifier = modifier,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = "${arrayList.get(it).introduction}!",
                            modifier = modifier,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                    }

                    Column(Modifier
                        .fillMaxSize(1f)
                        .weight(0.1f).clickable {

                            (context as GetAttractions).getAttractions().selectedAttraction.clear();
                            (context as GetAttractions).getAttractions().selectedAttraction.add(arrayList.get(it));

                            if((context as GetUsedHand).isLeftHandUsed()){
                                (context as GetViewPager).getPager().setCurrentItem(1, true)
                            }
                            else{
                                (context as GetViewPager).getPager().setCurrentItem(5, true)
                            }

                            System.out.println("Select [" + arrayList.get(it).name + "]");
                            System.out.println("Detail BasicDataBinding get " + arrayList.get(it).name);
                            System.out.println("Detail BasicDataBinding get " + (context as GetAttractions).getAttractions().selectedAttraction.get(0).name);
                        },
                        verticalArrangement = Arrangement.Center
                    ){

                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            "Floating action button.")
                    }
                }


            }



        }

    }

}

