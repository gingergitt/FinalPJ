package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {

        private RecyclerView mVerticalView;
        private VerticalAdapter mAdapter;
        private LinearLayoutManager mLayoutManager;
        private static ArrayList<VerticalData> data;
        private int MAX_ITEM_COUNT = 5;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);

            // RecyclerView binding
            mVerticalView = (RecyclerView) findViewById(R.id.vertical_list);
            String url = "http://70.12.226.146/oracledb/androidCardLogin.jsp";



            NetworkTask networkTask = new NetworkTask(url, null);

            networkTask.execute();

            // init Data
            data = new ArrayList<>();
            ArrayList<ImageView> img = new ArrayList<>();


            int i = 0;
            while (i < MAX_ITEM_COUNT) {
                if(i==0){
                    data.add(new VerticalData(R.drawable.registercard, i+"번째 데이터"));
                }else if(i==1){
                    data.add(new VerticalData(R.drawable.hana, i+"번째 데이터"));
                }
                else if(i==2){
                    data.add(new VerticalData(R.drawable.shinhan, i+"번째 데이터"));
                }else if(i==3){
                    data.add(new VerticalData(R.drawable.woori, i+"번째 데이터"));
                }else if(i==4){
                    data.add(new VerticalData(R.drawable.ibk, i+"번째 데이터"));
                }
                i++;
            }


            // init LayoutManager
         // 기본값이 VERTICAL
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            // setLayoutManager
            mVerticalView.setLayoutManager(mLayoutManager);

            // init Adapter
            mAdapter = new VerticalAdapter();

            // set Data
            mAdapter.setData(data);

            // set Adapter
            mVerticalView.setAdapter(mAdapter);

        }
////////////////////////////////////////////////////////////////////////////////////
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;

        private ContentValues values;



        public NetworkTask(String url, ContentValues values) {

            this.url = url;

            this.values = values;

        }



        @Override

        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

            result = requestHttpURLConnection.request(url, values);

            // 해당 URL로 부터 결과물을 얻어온다.

            return result;

        }



        @Override

        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            //textView.setText(s);

        }

    }



    public class RequestHttpURLConnection {

        public String request(String _url, ContentValues _params) {

            // HttpURLConnection 참조 변수.

            HttpURLConnection urlConn = null;

            // URL 뒤에 붙여서 보낼 파라미터.

            StringBuffer sbParams = new StringBuffer();



            /**

             * 1. StringBuffer에 파라미터 연결

             * */

            // 보낼 데이터가 없으면 파라미터를 비운다.

            if (_params == null)

            // sbParams.append("");

            //  sbParams.append("miseID=" + miseID);

            //sbParams.append("&misePW=" + misePW);

            //sbParams.append("miseID=test001&misePW=test001&miseNAME=테스트001");

            //sbParams.append("memberID=test123&password=test123&name=꼬북이&email=test123@naver.com");

            // 보낼 데이터가 있으면 파라미터를 채운다.



            /**

             * 2. HttpURLConnection을 통해 web의 데이터를 가져온다.

             * */

                try {

                    URL url = new URL(_url);

                    urlConn = (HttpURLConnection) url.openConnection();



                    // [2-1]. urlConn 설정.

                    urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.

                    urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.

                    urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");



                    // [2-2]. parameter 전달 및 데이터 읽어오기.

                    String strParams = sbParams.toString(); //sbParams에 정리한 파라미터들을 스트링으로 저장. 예)id=id1&pw=123;

                    OutputStream os = urlConn.getOutputStream();

                    os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력.

                    os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.

                    os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.



                    // [2-3]. 연결 요청 확인.

                    // 실패 시 null을 리턴하고 메서드를 종료.

                    if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)

                        return null;



                    // [2-4]. 읽어온 결과물 리턴.

                    // 요청한 URL의 출력물을 BufferedReader로 받는다.

                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));



                    // 출력물의 라인과 그 합에 대한 변수.

                    String line;

                    String page = "";



                    // 라인을 받아와 합친다.

                    // 버퍼의 웹문서 소스를 줄 단위로 읽어(line), page에 저장함

                    while ((line = reader.readLine()) != null) {

                        page += line;

                    }

                    try {

                        // JSP에서 보낸 JSON 받아오자  JSONObject = siteDataMain

                        JSONObject json = new JSONObject(page);

                        JSONArray jArr = json.getJSONArray("siteDataMain");



                        // JSON이 가진 크기만큼 데이터를 받아옴

                        for (int i = 0; i < jArr.length(); i++) {

                            json = jArr.getJSONObject(i);

                            System.out.println(i + "번째 데이터 : " + json.getString("siteTitle"));

                            System.out.println(i + "번째 데이터 : " + json.getString("siteLink"));

                            System.out.println(i + "번째 데이터 : " + json.getString("siteImage"));

                            System.out.println(i + "번째 데이터 : " + json.getString("siteText"));

                            System.out.println("\n");



                          //  list.add(new CardForSite(json.getString("siteTitle"), json.getString("siteLink"), json.getString("siteImage"), json.getString("siteText")));

                        }

                        /* 여기까지 서버가 보낸 데이터를 받아 왔다. 밑에는 확인을 위한 수행 */

                        //String data = "받은 데이터 : " + getJsonData[0] + " " + getJsonData[1];

                        //setTextView(data);



                    } catch(Exception e){

                        e.printStackTrace();;

                    }

                } catch (MalformedURLException e) { // for URL.

                    e.printStackTrace();

                } catch (IOException e) { // for openConnection().

                    e.printStackTrace();

                } finally {

                    if (urlConn != null)

                        urlConn.disconnect();

                }

            return null;

        }
    }
    }





