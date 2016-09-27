package com.mangodevelopers.events;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by AmanTugnawat on 24-09-16.
 */
public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{

    TextView eventNum;
    ListView listView;
    ProgressBar mProgressBar;
    SwipeRefreshLayout swipeLayout;
    CustomListAdapter adapter;

    int totalEvents;

    private String API_URL="https://mangodevelopers.com/tutorials/samples/events/demo-api.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Content Elements
        //textView= (TextView) findViewById(R.id.textView);
        listView= (ListView) findViewById(R.id.listView);
        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);
        eventNum=(TextView)findViewById(R.id.eventNum);

        //Swipe Layout
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //Get Data from API and display
        new GetEvents().execute();


    }


    /*
    * Open details activity onItemClick
    * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Not working as there are multiple children which are focusable

        //Intent eventDetails=new Intent(getApplicationContext(),EventDetailsActivity.class);
        //Event event= eventsList.get(position);
        //startActivity(eventDetails);
    }

    @Override
    public void onRefresh() {
        if(swipeLayout!=null) {

            new GetEvents().execute();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeLayout.setRefreshing(false);
                }
            }, 5000);
        }

    }



    /**
     * Async task to get all events from API
     * */
    private class GetEvents extends AsyncTask<String, Void, Boolean> {

        ArrayList<Event> eventsList= new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            HttpServiceHandler handler = new HttpServiceHandler();

            String json=handler.makeServiceCall(API_URL);
            Log.i("Response", "> " + json);

            if (json != null) {
                try {

                    JSONObject jsonObj = new JSONObject(json);

                    if (jsonObj != null) {
                        JSONArray items = jsonObj.getJSONArray("events");
                        totalEvents=items.length();
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject catObj = (JSONObject) items.get(i);
                            Event cat = new Event(catObj.getInt("id"),
                                    catObj.getString("name"),
                                    catObj.getString("image"),
                                    catObj.getString("category"),
                                    catObj.getString("description"),
                                    catObj.getString("experience")
                            );
                            eventsList.add(cat);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;

            } else {

                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            try{
                mProgressBar.setVisibility(View.GONE);
                if(result.booleanValue()){
                    populateData(eventsList);
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Oops...!!\nConnection to server couldn't be established.\nSwipe down to refresh.",
                            Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                Log.e("Post Execute Error",e.toString());
            }
        }

    }

    /**
     * Adding ListView data
     * */
    private void populateData(ArrayList<Event> list) {

        // attaching data adapter to spinner
        adapter=null;

        adapter = new CustomListAdapter(getApplicationContext(), list);

        //Polulating TextViews
        eventNum.setText("Total event(s):"+totalEvents);

        //Populating ListView
        listView.setAdapter(null);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        Log.i("poulate", "done");

    }


}
