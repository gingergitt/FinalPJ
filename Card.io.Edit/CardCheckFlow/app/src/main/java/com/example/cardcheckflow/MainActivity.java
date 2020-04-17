package com.example.cardcheckflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   TextView cardStatus1, cardStatus2, cardStatus3, cardStatus4, cardStatus5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardStatus1 = findViewById(R.id.cardStatus1);
        cardStatus2 = findViewById(R.id.cardStatus2);
        cardStatus3 = findViewById(R.id.cardStatus3);
        cardStatus4 = findViewById(R.id.cardStatus4);
        cardStatus5 = findViewById(R.id.cardStatus5);

        Intent i = getIntent();
        String cardAgnecy = i.getExtras().getString("cardAgency");
        String cardname = i.getExtras().getString("cardname");
        String cardNumber = i.getExtras().getString("cardNumber");
        String cardValidity = i.getExtras().getString("cardValidity");
        String cardCVV = i.getExtras().getString("cardCVV");

        cardStatus1.setText(cardname);

        cardStatus2.setText(cardNumber);
        cardStatus3.setText(cardValidity);
        cardStatus4.setText(cardCVV);
        cardStatus5.setText(cardAgnecy);


    }


    // ============  //http커넥션=================

    public class CardTask extends AsyncTask<String, Void, String> {
        private  String receiveMsg;
        private  String urlstr2;

        public void setURL(String cardno, String cardname, String id , String cardagency) {
            Log.d("---------------------","CardPayment AsyncTask 연결 ");
            urlstr2 = "http://70.12.226.146/oracledb/androidCard.jsp?cardno="+cardno+"&cardname="+cardname
                    +"&id="+id+"&cardagency="+cardagency;

            Log.d("----------------","usl연결확인되었습니다.");
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL(urlstr2);
                Log.d("---------","url="+urlstr2);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.connect();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("---------","에러Checking....");
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("---------","receiveMsg="+receiveMsg);
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.trim().equals("OK")) {
                Toast.makeText(MainActivity.this, "카드정보를 성공적으로 등록했습니다.", Toast.LENGTH_SHORT).show();


            }else if(s.trim().equals("NO")){
                Log.d("=======","s=0 :"+s);
                Toast.makeText(MainActivity.this,"이미 존재하거나 잘못된 카드정보 입니다."+"\n"+"다시 시도하세요.", Toast.LENGTH_SHORT).show();


            }

        }
        }


    }

