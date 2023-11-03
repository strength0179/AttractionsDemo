package tw.hsu.example.attractions.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import tw.hsu.example.attractions.R
import tw.hsu.example.attractions.data.Attraction
import tw.hsu.example.attractions.data.GetAttractions
import tw.hsu.example.attractions.data.GetUsedHand
import tw.hsu.example.attractions.data.GetViewPager
import tw.hsu.example.attractions.ui.theme.AttractionsTheme


class AttractionDetailFragment  : Fragment() {

    lateinit var view : ComposeView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = ComposeView(requireContext());


        return view;
    }

    override fun onResume() {
        super.onResume()
        System.out.println("Detail BasicDataBinding onResume");

        view.apply {
            setContent {
                AttractionsTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.onBackground
                    ) {
                        BasicDataBinding()
                    }
                }
            }
        }
    }

    @Composable
    fun BasicDataBinding() {

        var attraction by remember { mutableStateOf((context as GetAttractions).getAttractions().selectedAttraction) }


        Box(modifier = Modifier
            .fillMaxSize(1f)
            .padding(all = 10.dp)){

            if(attraction.size > 0){
                System.out.println("Detail BasicDataBinding "  + attraction.get(0).name);
                AttractionDetail(name = "", attraction = attraction.get(0) );
            }
            else{
                System.out.println("Detail BasicDataBinding empty");
            }


            if((context as GetUsedHand).isLeftHandUsed()){

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.Start) {
                    FloatingActionButton(
                        onClick = {
                            (context as GetViewPager).getPager().setCurrentItem(1, true);
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
                        (context as GetViewPager).getPager().setCurrentItem(1, true);
                    }) {
                        Icon(Icons.Filled.Menu, "Floating action button.")
                    }
                }
            }


        }


    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun AttractionDetail(name: String, modifier: Modifier = Modifier,  attraction: Attraction) {

        var imagePresent by remember { mutableStateOf(0) }
        
        LazyColumn(
            state = rememberLazyListState(0),
            contentPadding = PaddingValues(horizontal = 5.dp),
        ) {

            if(attraction?.images!!.size > 0){
                item {
                    GlideImage(
                        model = attraction.images?.get(imagePresent),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(1f),
                        contentScale = ContentScale.Crop)
                }
                
                item{
                    LazyRow(content = {
                        items(attraction.images.size){
                            GlideImage(
                                model = attraction.images?.get(it),
                                contentDescription = "",
                                modifier = Modifier.width(60.dp).padding(4.dp).clickable {
                                    imagePresent = it;
                                },
                                contentScale = ContentScale.Fit)
                        }
                    })
                }
            }


            item{
                Text(text = attraction.name,  modifier = Modifier.padding(vertical = 8.dp), fontWeight = FontWeight.Bold,)
            }
            
            item{
                Text(text = attraction.introduction,  modifier = Modifier.padding(vertical = 8.dp))
            }


            item{
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = resources.getString(R.string.address))
                    Text(text = attraction.address)
                }

            }

            item{
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = resources.getString(R.string.modified))
                    Text(text = attraction.modified)
                }

            }

            item{
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = attraction.url, color = Color.Blue, modifier = Modifier.clickable {
                        (context as GetViewPager).getPager().setCurrentItem(3);
                    })
                }

            }
            
        }

    }

}

