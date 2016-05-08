package com.example.ekkert.myapplication;

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
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends Activity {


    static {
        System.loadLibrary("hello");
    }

    NumberPicker noPicker = null;
    ListView listView = null;
    public int num1 = 0 ;
    public int num2 = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //number picker

    }

    public native String stringFromJNI(int numberPickerMinValue, int numberPickerMaxValue, int[] activityNum);

    public void onClickFind(View v) throws ParseException {
        int size=0;
        for (int i = 0; i < check.checkedItems.length; i++) {
            if (check.checkedItems[i]) {
                check.activityNum[size++] = i+1;
            }
        }
        int[] tempArray = new int[size];
        tempArray = Arrays.copyOf(check.activityNum, size);
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        TextView text2 = (TextView)findViewById(R.id.textView4);
        String result = stringFromJNI(check.numberPickerMinValue, check.numberPickerMaxValue, tempArray);
        text2.setText(result);
        intent.putExtra("numberPickerMinValue", check.numberPickerMinValue);
        intent.putExtra("numberPickerMaxValue", check.numberPickerMaxValue);
        Bundle bundle = new Bundle();
        bundle.putString("activityNum", result);
        intent.putExtras(bundle);
        //   intent.putExtra("activityNum", check.a);
        //intent.putExtra("checkedItems", check.checkedItems);
        startActivity(intent);
    }
    public void onClick(View v) {
        showAlert(v);
    }
    public void onClickList(View v) {
        showAlertList();
    }
    public class Check {
        public boolean[] checkedItems = new boolean[]{ false, false, false, false, false, false, false };
        public int[] activityNum = new int[7];
        public int numberPickerMinValue = 0;
        public int numberPickerMaxValue = 0;
        public int a = 0;
    }
    Check check = new Check();

    public void showAlert(final View v) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.layout_custom_dialog, null);
        adb.setView(dialogview);
        final EditText editText = (EditText)findViewById(v.getId());


        //numberpicker
        final int minValue = -40;
        final int maxValue = 40;
        final NumberPicker noPicker = (NumberPicker)dialogview.findViewById(R.id.numberPicker11);
        /*switch (v.getId()) {
            case R.id.editText2:
                noPicker.setValue(num2);;
                break;
            case R.id.editText:
                noPicker.setValue(num1);
                break;
        }*/
        noPicker.setMinValue(0);
        noPicker.setMaxValue(maxValue - minValue);
        //noPicker.setValue(noPicker.getValue() - minValue);
        noPicker.setValue( check.numberPickerMinValue - minValue);
        noPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int index) {
                return Integer.toString(index + minValue);
            }
        });
        noPicker.setWrapSelectorWheel(false);

        // заголовок
        adb.setTitle(R.string.dialogTitle);

        // кнопка положительного ответа
        adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
                switch (v.getId()) {
                    case R.id.editText2:
                        check.numberPickerMinValue = noPicker.getValue() + minValue;
                        editText.setText(String.valueOf(check.numberPickerMinValue));
                        break;

                    case R.id.editText:
                        check.numberPickerMaxValue = noPicker.getValue() + minValue;
                        editText.setText(String.valueOf(check.numberPickerMaxValue));
                        break;
                }

            }

        });
        // кнопка отрицательного ответа
        adb.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton ) {
                dialogInterface.cancel();
            }

        });
        // кнопка нейтрального ответа

        // создаем диалог
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }
    public void showAlertList() {
        final CharSequence[] activities = new String[] {
                "Пляжный отдых", "Туры по городам", "Горнолыжный спорт", "Экскурсионные туры",
                "Тур по барам", "Тур на семинар по С++", "Тур по винным подвалам"
        };



        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.dialogTitleList);
        adb.setMultiChoiceItems(activities, check.checkedItems,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which, boolean isChecked) {

                        if (isChecked) {

                            check.checkedItems[which] = true;

                        } else if (check.checkedItems[which]) {

                            check.checkedItems[which] = false;

                        }
                    }
                });
        // кнопка положительного ответа
        adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {

            }

        });
        // кнопка отрицательного ответа
        adb.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton ) {

            }

        });
        // создаем диалог
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }
}
