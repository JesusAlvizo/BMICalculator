package com.jesusalvizo.yourbmi;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText heightIn;
    private EditText weightIn;
    private Button btnCalcBMI;
    private TextView bmiOut;
    private TextView bmiStatus;
    private double weight = 0;
    private double height = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApp();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initializeApp() {
        weightIn = (EditText) findViewById(R.id.weightInput);
        heightIn = (EditText) findViewById(R.id.heightInput);
        btnCalcBMI = (Button) findViewById(R.id.btnCalcBMI);
        bmiOut = (TextView) findViewById(R.id.bmiOut);
        bmiStatus = (TextView) findViewById(R.id.bmiStatus);
        //       btnCalcBMI.setOnClickListener(new View.OnClickListener() {
        //           @Override
        //           public void onClick(View v) {
        //               calculateBMI();
        //           }
        //       });
    }

    public void calculateBMI(View v) {
        String status = "";
        //       Log.i("calculateBMI","Button was pressed");
        weight = Double.parseDouble(weightIn.getText().toString());
        height = Double.parseDouble(heightIn.getText().toString());
        double bmi = (weight / (height * height));
        String result = String.format("%.2f", bmi);
        Log.d("MyActivity", result);
        bmiOut.setText(result, TextView.BufferType.NORMAL);

        bmiStatus.setText("");
        bmiStatus.setText(bmiEval(bmi));
    }

    public String bmiEval(double bmi){
        String status="";
        if (bmi < 16.0) {
            status = "Seriously Underweight";
        } else if (bmi >= 16.0 && bmi < 18.0) {
            status = "Underweight";
        } else if (bmi >= 19.0 && bmi < 24.0) {
            status = "Normal Weight";
        } else if (bmi >= 25.0 && bmi < 29.0) {
            status = "OverWeight";
        } else if (bmi >= 30.0 && bmi < 35.0) {
            status = "Seriously Overweight";
        } else if (bmi > 36) {
            status = "Gravely Overweight";
        }
        return  status;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.jesusalvizo.yourbmi/http/host/path")
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.jesusalvizo.yourbmi/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}