package com.example.bq.assignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics extends ActionBarActivity {
    private TextView bodyText;
    TimeList myTimeList=new TimeList((this));
    BuzzerTime myBuzzerTime=new BuzzerTime((this));
    private ArrayList times;
    private ArrayList lastTen;
    private ArrayList lastHundred;
    private ArrayList buzzerdata;
    //a function to start
    public void startStat(){
        //load the data first form the file
        myTimeList.loadFromFile();
        myBuzzerTime.loadFromFile();
        fillUpLatency();
        buzzerdata=myBuzzerTime.getBuzzers();
        bodyText= (TextView) findViewById(R.id.screen_text);

        bodyText.setText("Reaction time statistics:\n"
                + "Minimum of all " + String.format("%.3f", findMin(times)) + " second\n"
                + "Minimum of last ten " + String.format("%.3f", findMin(lastTen)) + " seconds\n"
                + "Minimum of last hundred " + String.format("%.3f", findMin(lastHundred)) + " seconds\n"
                + "Maxmum of all " + String.format("%.3f", findMax(times)) + " seconds\n"
                + "Maxmum of last ten " + String.format("%.3f", findMax(lastTen)) + " seconds\n"
                + "Maxmum of last hundred " + String.format("%.3f", findMax(lastHundred)) + " seconds\n"
                + "Average of all times: " + String.format("%.3f", findAve(times)) + " seconds\n"
                + "Average of last ten: " + String.format("%.3f", findAve(lastTen)) + " seconds\n"
                + "Average of last hundred: " + String.format("%.3f", findAve(lastHundred)) + " seconds\n"
                + "Median of all " + String.format("%.3f", findMedian(times)) + " seconds\n"
                + "Median of last ten " + String.format("%.3f", findMedian(lastTen)) + " seconds\n"
                + "Median of last hundred " + String.format("%.3f", findMedian(lastHundred)) + " seconds\n"
                + "Buzzer counts: \n"
                + "2 players: Player 1 buzzers " + twoBuzzerOne(buzzerdata) + " times. Player 2 buzzers " + twoBuzzerTwo(buzzerdata) + " times\n"
                + "3 players: Player 1 buzzers " + threeBuzzerOne(buzzerdata) + " times. Player 2 buzzers " + threeBuzzerTwo(buzzerdata) + " times. Player 3 buzzers " + threeBuzzerThree(buzzerdata) + " times\n"
                + "4 players: Player 1 buzzers " + fourBuzzerOne(buzzerdata) + " times. Player 2 buzzers " + fourBuzzerTwo(buzzerdata) + " times. Player 3 buzzers " + fourBuzzerThree(buzzerdata) +
                " times. Player 4 buzzers " + fourBuzzerFour(buzzerdata) + "times.");



    }
    //this is the function for calculate different data.
    public Double findMin(ArrayList list){
        if (list.size()==0){
            return 0.0;
        }else {
            Double min = (Double) list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if ((Double) list.get(i) < min) {
                    min = (Double) list.get(i);
                }
            }
            return min;
        }
        }

    public Double findMax(ArrayList list){
        if (list.size()==0){
            return 0.0;
        }else {
            Double max = (Double) list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if ((Double) list.get(i) > max) {
                    max = (Double) list.get(i);
                }
            }
            return max;
        }
    }
    public Double findMedian(ArrayList list){
        if (list.size()==0){
            return 0.0;
        }else {
            Double median;
            Double[] DoubleArray = new Double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                DoubleArray[i] = ((Double) list.get(i));
            }
            Arrays.sort(DoubleArray);
            if (DoubleArray.length % 2 == 0) {
                median = (DoubleArray[DoubleArray.length / 2] + DoubleArray[DoubleArray.length / 2 - 1]) / 2;
            } else {
                median = DoubleArray[DoubleArray.length / 2];
            }
            return median;
        }
    }
    public Double findAve(ArrayList list){
        if (list.size()==0){
            return 0.0;
        }else {
            Double Ave;
            Double sum = 0.0;
            Double[] DoubleArray = new Double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                DoubleArray[i] = ((Double) list.get(i));
            }
            for (Double i : DoubleArray) {
                sum += i;
            }
            Ave = sum / list.size();
            return Ave;
        }
    }
    // here is the function to find how many times each player buzzer
    public int twoBuzzerOne(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            //21 represent for two player mode, player1 buzzers.
            Integer TwentyOne = 21;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
        }
    public int twoBuzzerTwo(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 22;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int threeBuzzerOne(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 31;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int threeBuzzerTwo(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 32;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int threeBuzzerThree(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 33;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int fourBuzzerOne(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 41;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int fourBuzzerTwo(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 42;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int fourBuzzerThree(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 43;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public int fourBuzzerFour(ArrayList list){
        if (list.size()==0){
            return 0;
        }else {
            int Num = 0;
            Integer TwentyOne = 44;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == (TwentyOne)) {
                    Num = Num + 1;
                }
            }
            return Num;
        }
    }
    public void clear(View view){
        myTimeList.clearTimes();
        myTimeList.saveInFile();
        myBuzzerTime.clearBuzzers();
        myBuzzerTime.saveInFile();
        startStat();
    }

    public void fillUpLatency(){
        times=myTimeList.getAllTimes();
        lastTen=myTimeList.getLastTen();
        lastHundred=myTimeList.getLastHundred();
    }
    //http://stackoverflow.com/questions/28546703/how-to-code-using-android-studio-to-send-an-email
    public void sendEmail(View view){
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, bodyText.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Statistics.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        startStat();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

