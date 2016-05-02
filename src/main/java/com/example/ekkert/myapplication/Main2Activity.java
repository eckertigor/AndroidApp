package com.example.ekkert.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Main2Activity extends Activity {

    /*boolean[] checked = intent.getBooleanArrayExtra("checkedItems");
    String checkedItems = Arrays.toString(checked);
    char[] items = checkedItems.toCharArray();*/
    static {
        System.loadLibrary("hello");
    }
    public class Check {
        public int numberPickerMinValue = 0;
        public int numberPickerMaxValue = 0;
        public int[] activityNum = new int[7];
        // public int a;
    }
    Check check = new Check();
    public native String stringFromJNI(int numberPickerMinValue, int numberPickerMaxValue, int[] activityNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle b=getIntent().getExtras();
        check.numberPickerMinValue = intent.getIntExtra("numberPickerMinValue", 0);
        check.numberPickerMaxValue = intent.getIntExtra("numberPickerMaxValue", 0);
        check.activityNum = b.getIntArray("activityNum");
        // check.a = intent.getIntExtra("a", 0);

    }
    public void onClickConnect(View v){
        int sizea = check.activityNum.length;
        String[] values = {stringFromJNI(check.numberPickerMinValue, check.numberPickerMaxValue, check.activityNum)};
        final ListView listView = (ListView) findViewById(R.id.listView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, values);
        listView.setAdapter(adapter);
        //textView.setText(stringFromJNI());
    }

}