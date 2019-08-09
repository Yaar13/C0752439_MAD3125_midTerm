package com.example.c0752439_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserHome extends AppCompatActivity implements DataAdapter.onClickListener {
    RecyclerView recyclerView;
    JSONObject obj;
    List<DataModel> dataList = new ArrayList<DataModel>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        processJSON();
        editor = sharedPreferences.edit();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            obj = new JSONObject(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Should read json values and store it in the list.


        //end of reading

        //dataList.add(new DataModel("Title","Launch Year","https://dummyimage.com/600x400/000/fff"));
        DataAdapter adapter = new DataAdapter(UserHome.this, dataList);


        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }


    //Reading Json file from the assets folder
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    //yyyyyy
    public void processJSON()
    {
        String jsonString = this.loadJSONFromAsset();
        if (jsonString != null)
        {
            try {
                JSONArray mJSONArray = new JSONArray(jsonString);
                dataList = new ArrayList<>();
                for(int i = 0; i < mJSONArray.length();i++){
                    DataModel dataModel = fetchData(mJSONArray.getJSONObject(i));
                    dataList.add(dataModel);
                    Log.v("--JSON--",dataList.toString());
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
    public DataModel fetchData(JSONObject userJsonObject) throws JSONException {
        String flight_number = userJsonObject.getString("mission_name");
        //String image_URL = userJsonObject.getString("mission_patch_small");
        String launch_year = userJsonObject.getString("launch_year");

        JSONObject links = new JSONObject(userJsonObject.getJSONObject("links").toString());
        //String mission_patch = links.getString(“mission_patch”);
        String mission_patch_small = links.getString("mission_patch_small");
        //String article_link = links.getString(“article_link”);
        //String wikipedia = links.getString(“wikipedia”);
        //String video_link = links.getString(“video_link”);
        String image_URL = mission_patch_small;


        return new DataModel(flight_number,launch_year,image_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            editor.putString("status","0");
            editor.commit();
            startActivity(new Intent(UserHome.this,UserLogin.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClickListener(int position) {
        Toast.makeText(this, "You Clicked on Position "+position, Toast.LENGTH_SHORT).show();
    }
}



