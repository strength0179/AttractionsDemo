package tw.hsu.example.api

import android.os.Looper
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class CallApi {

    var client = OkHttpClient();
    var request : Request;
    var url : String;

    lateinit var success : ApiSuccess;
    lateinit var fail : ApiFail;
    lateinit var error : ApiError;

    constructor(u : String){
        url = u;
        request = Request.Builder()
            .url(url)
            .header("Accept", "application/json")
            .build()
    }

    fun setApiSuccess(success: ApiSuccess) : CallApi{
        this.success = success;
        return this;
    }

    fun setApiFail(fail: ApiFail) : CallApi{
        this.fail = fail;
        return this;
    }

    fun setApiError(error: ApiError) : CallApi{
        this.error = error;
        return this;
    }




    fun callApi(){


        System.out.println("API : " + request.url);

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                System.out.println("API Failure");
                e.printStackTrace();

            }

            override fun onResponse(call: Call, response: Response) {
                System.out.println("API success");
                var bodyString = response.body?.string();

//                System.out.println(bodyString);

                try{
                    if(success != null){
                        System.out.println("API import success result");
                        bodyString?.let { success.success(it) };
                    }
//                    var responseJs = JSONObject(bodyString);
//                    if(responseJs != null){
//
//                    }
                }
                catch (e : Exception){

                    if(error != null){
                        error.error(-1, e.message.toString());
                    }

                }

            }
        })

    }

}