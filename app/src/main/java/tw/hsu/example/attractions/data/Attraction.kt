package tw.hsu.example.attractions.data

import org.json.JSONObject

class Attraction : JsonReader{
    var id = 0;
    var name = " ";

    var open_status = -1;
    var introduction = "";
    var open_time = "";
    var zipcode = "";
    var distric = "";
    var address = "";
    var tel = "";
    var fax = "";
    var email = "";
    var months = "";
    var nlat = "";
    var elong = "";
    var official_site= "";
    var facebook = "";
    var ticket = "";
    var remind = "";
    var staytime = "";
    var modified = "";
    var url = "";
    var category = null;
    var target = null;
    var images = ArrayList<String>();
    override fun read(json: JSONObject) {

//        System.out.println(json.toString());

        id = json.getInt("id");
        name = json.getString("name");
        open_status = json.getInt("open_status");
        introduction = json.getString("introduction");
        open_time = json.getString("open_time");
        zipcode = json.getString("zipcode");
        distric = json.getString("distric");
        address = json.getString("address");
        tel = json.getString("tel");
        fax = json.getString("fax");
        email = json.getString("email");
        months = json.getString("months");
        nlat = json.getString("nlat");
        elong = json.getString("elong");
        official_site = json.getString("official_site");
        facebook = json.getString("facebook");
        ticket = json.getString("ticket");
        remind = json.getString("remind");
        staytime = json.getString("staytime");
        modified = json.getString("modified");
        url = json.getString("url");

        val images2 = json.getJSONArray("images")

        for(ii in 0 .. images2.length() - 1){
            images!!.add(images2.getJSONObject(ii).getString("src"))
        }

    }


    fun copy(f : Attraction){
        id = f.id;
        name = f.name;
        images = f.images;
        address = f.address;
        modified = f.modified;
        distric = f.distric;
        introduction = f.introduction;
    }

}
