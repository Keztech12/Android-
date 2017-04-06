package com.kesahv.idiyhomeautomation;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;





public class MainActivity extends AppCompatActivity    {

    public ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String msgon="on";
         final String msgoff="off";
        final String msgon1="on1";
         final String msgoff1="off1";
     pb=(ProgressBar) findViewById(R.id.progressBar1);

        Button  ON1=(Button) findViewById(R.id.button);
        Button  ON2=(Button) findViewById(R.id.button6);
        Button  OFF1=(Button) findViewById(R.id.button2);
        Button  OFF2=(Button) findViewById(R.id.button4);
        final ImageView switch1=(ImageView) findViewById(R.id.imageView);
        final ImageView switch2=(ImageView) findViewById(R.id.imageView1);




        ON1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch1.setImageResource(R.drawable.on);

                pb.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute(msgon);



            }
        });

        ON2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch2.setImageResource(R.drawable.on);
                pb.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute(msgon1);
            }
        });


        OFF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch1.setImageResource(R.drawable.off);
                pb.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute(msgoff);

            }
        });

        OFF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch2.setImageResource(R.drawable.off);
                pb.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute(msgoff1);

            }
        });

    }


    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }



        protected void onPostExecute(Double result){
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
        }



        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);

        }



        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://kezrok.000webhostapp.com/button.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("$_POST['']", valueIWantToSend));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }








}














