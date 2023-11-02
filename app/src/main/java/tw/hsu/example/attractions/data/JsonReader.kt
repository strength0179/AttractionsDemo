package tw.hsu.example.attractions.data

import org.json.JSONObject

interface JsonReader {

    fun read(json : JSONObject)

}