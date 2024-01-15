package tw.hsu.example.api

import org.junit.Assert.*

import org.junit.Test

class CallApiTest {


    @Test
    fun callApi() {
        System.out.println("set callApi");

        var api : CallApi = CallApi("http://e-traffic.taichung.gov.tw/DataAPI/api/AirPortFlyAPI/A/RMQ");
        var apiSuccess : ApiSuccess = object : ApiSuccess {
            override fun success(result: String) {
                System.out.println("callApi success return : " + result);
            }
        }

        api.setApiSuccess(apiSuccess);

        api.callApi();

    }
}