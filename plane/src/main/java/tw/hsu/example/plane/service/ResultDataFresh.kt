package tw.hsu.example.plane.service

import android.content.Context
import tw.hsu.example.flight.FlightAdapter
import tw.hsu.example.flight.data.Flight

class ResultDataFresh(

    var context: Context,
    var list: ArrayList<Flight>,
    var source: ArrayList<Flight>,
    var adapter: FlightAdapter,
    var code: Int,
) : Runnable {


    companion object{
        val Add : Int = 0;
        val Remove : Int = 1;
        val Fresh : Int = 2;
    }

    override fun run() {


        when(code){
            Add ->{
                var index = source.size;

                source.addAll(list);
//                for(f in 0 .. list.size-1){
//                    adapter.notifyItemChanged(index + f);
//                }
//                adapter.notifyItemRangeInserted(index, list.size - 1);
                adapter.notifyItemRangeChanged(index, list.size);
                adapter.notifyDataSetChanged();
                System.out.println("Refresh List ... Add " + list.size);
            }
            Remove ->{
                for(f in list){
                    var index = source.indexOf(f);
                    source.remove(f);
                    adapter.notifyItemRemoved(index);
                    adapter.notifyItemRangeRemoved(index, index+1);
                }
                System.out.println("Refresh List ... Remove");
            }
            Fresh ->{
                for(f in list){
                    var index = source.indexOf(f);
                    adapter.notifyItemChanged(index);
                }
                System.out.println("Refresh List ... Fresh");
            }


        }

        list.clear();

    }


}