package com.geotask.myapplication.Controllers;

import android.util.Log;

import com.geotask.myapplication.DataClasses.Bid;
import com.geotask.myapplication.DataClasses.GTData;
import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/*
API DOCUMENTATION
https://github.com/searchbox-io/Jest/tree/master/jest
 */


public class ElasticsearchController {
    private static JestDroidClient client;
    private final static String INDEX_NAME = "cmput301w18t23";

    public String createNewDocument(GTData data){
        Gson gson = new Gson();
        String json = gson.toJson(data);
        Index request = new Index.Builder(json).index(INDEX_NAME).type(data.getType()).build();

        System.out.println(request.toString());
        try {
            JestResult result = client.execute(request);
            return result.getJsonObject().get("_id").toString().replace("\"", "");
           // return result.get;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GTData getDocument(String ID) {
        Get request = new Get.Builder(INDEX_NAME, ID).build();

        try {
            JestResult result = client.execute(request);
            Bid data = result.getSourceAsObject(Bid.class);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteDocument(String ID) {
        try {
            Log.i("build", new Delete.Builder(ID).index(INDEX_NAME).type("bid").build().toString());
            return client.execute(new Delete.Builder(ID).index(INDEX_NAME).type("bid").build()).getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /*public void search(String type, ArrayList<ArrayList<String>> terms){
        Map<String, String> jsonMap = new HashMap<String,String>();
        for(ArrayList<String> term: terms) {
            jsonMap.put(term.get(0), term.get(1));
        }
        Gson gson = new Gson();
        String query = gson.toJson(jsonMap);

        Log.i("query", query);
        Search search = new Search.Builder(query).addIndex(INDEX_NAME).addType(type).build();
        try {
            Log.i("hi-search", client.execute(search).getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public Integer search(String type, String query) {
        Search search = new Search.Builder(query).addIndex(INDEX_NAME).addType(type).build();
        Log.wtf("search", String.valueOf(search));
        try {

            SearchResult result = client.execute(search);
            return result.getTotal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void verifySettings() {
        if(client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);

            client = (JestDroidClient) factory.getObject();
        }
    }
}
