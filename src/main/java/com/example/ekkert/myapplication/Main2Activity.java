package com.example.ekkert.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


import java.util.HashMap;

public class Main2Activity extends Activity {

    /*boolean[] checked = intent.getBooleanArrayExtra("checkedItems");
    String checkedItems = Arrays.toString(checked);
    char[] items = checkedItems.toCharArray();*/
    static {
        System.loadLibrary("hello");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ekkert.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ekkert.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public class Check {
        public int numberPickerMinValue = 0;
        public int numberPickerMaxValue = 0;
        public String activityNum;
        // public int a;
    }

    Check check = new Check();
    //   public native String stringFromJNI(int numberPickerMinValue, int numberPickerMaxValue, int[] activityNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        check.numberPickerMinValue = intent.getIntExtra("numberPickerMinValue", 0);
        check.numberPickerMaxValue = intent.getIntExtra("numberPickerMaxValue", 0);
        check.activityNum = b.getString("activityNum");
        ListView listView = (ListView) findViewById(R.id.listVieww);
        // TextView text = (TextView) findViewById(R.id.textView3);
        //String test = "[ { \"id\": 2, \"name\": \"Балашиха\", \"weather\": 5 }, { \"id\": 5, \"name\": \"Зеленоград\", \"weather\": 0 } ]";
        JSONParser parser = new JSONParser();
        try {
            // Object obj = parser.parse(test);
            Object obj = parser.parse(check.activityNum);
            JSONArray jArray = (JSONArray) obj;
            ArrayList<HashMap<String, String>> items = new ArrayList<>();
            for (int i = 0; i < jArray.size(); i++) {
                JSONObject json_data = (JSONObject) jArray.get(i);
                String name = (String) json_data.get("name");
                Integer weather = Integer.parseInt(json_data.get("weather").toString());
                //  String weather = (String)json_data.get("weather");
                // String name1 = name + weather.toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("weather", weather.toString());
                // items.add();
                items.add(map);
            }
            SimpleAdapter adapter = new SimpleAdapter(this, items,
                    R.layout.view_item, new String[]{"name", "weather"},
                    new int[]{R.id.textViewName, R.id.textViewWeather});

            listView.setAdapter(adapter);


        } catch (ParseException e) {
            e.printStackTrace();
        }

//JSONObject jsonObject = new JSONObject(check.activityNum);

        // check.a = intent.getIntExtra("a", 0);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*public void onClickListView(View v) {

        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        startActivity(intent);
    }*/

}