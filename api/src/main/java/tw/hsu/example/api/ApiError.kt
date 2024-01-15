package tw.hsu.example.api

interface ApiError {

    fun error(code : Int, msg : String);

}