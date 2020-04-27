package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appver2.ui.login.LoginActivity;

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

import static com.example.appver2.ui.login.LoginActivity.idbt;
import static com.example.appver2.ui.login.LoginActivity.pwdbt;


public class MapActivity extends AppCompatActivity {


        private RecyclerView mVerticalView;
        private VerticalAdapter mAdapter;
        private LinearLayoutManager mLayoutManager;

        private int MAX_ITEM_COUNT = 5;
        ArrayList<VerticalData> data = new ArrayList<>();


        ProgressBar loading;
        ProgressDialog progressDialog;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);

            // RecyclerView binding
            mVerticalView = (RecyclerView) findViewById(R.id.vertical_list);
            progressDialog = new ProgressDialog(this);

            // init Data
//
//            ArrayList<ImageView> img = new ArrayList<>();
//
//
//            int i = 0;
//            while (i < MAX_ITEM_COUNT) {
//                if(i==0){
//                    data.add(new VerticalData(R.drawable.registercard, i+"번째 데이터"));
//                }else if(i==1){
//                    data.add(new VerticalData(R.drawable.hana, i+"번째 데이터"));
//                }
//                else if(i==2){
//                    data.add(new VerticalData(R.drawable.shinhan, i+"번째 데이터"));
//                }else if(i==3){
//                    data.add(new VerticalData(R.drawable.woori, i+"번째 데이터"));
//                }else if(i==4){
//                    data.add(new VerticalData(R.drawable.ibk, i+"번째 데이터"));
//                }
//                i++;
//            }

            // init LayoutManager


            Intent intent = getIntent(); /*데이터 수신*/
            String id = idbt.getText().toString(); /*String형*/
            String pwd = pwdbt.getText().toString(); /*int형*/
            CardMatchTask cardmatchtask = new CardMatchTask();
            cardmatchtask.setURL(id,pwd);
            cardmatchtask.setURL3(id);
            cardmatchtask.execute();

        }

// Card 등록 여부 확인 Task 실행 ----------------------------------------------------------------------

    public class CardMatchTask extends AsyncTask<String, Void, String> {
        private String receiveMsg;
        private String urlstr, urlstr3;


        public  void setURL(String id, String pwd) {
            Log.d("---------------------","CardLoginTask http 연결");
//            urlstr = "http://70.12.226.146/oracledb/androidCardLogin.jsp?id="+id+"&pwd"+pwd;

            urlstr = "http://192.168.1.162/oracledb/androidLogin.jsp?id="+id+"&pwd"+pwd;

            Log.d("----------------","usl연결 oK?");
        }

            public  void setURL2(String id, String pwd) {
                Log.d("---------------------","LoginTask http 연결");
//                urlstr = "http://70.12.226.146/oracledb/androidCard.jsp?id="+id+"&pwd"+pwd;

                urlstr = "http:/192.168.1.162/oracledb/androidLogin.jsp?id="+id+"&pwd"+pwd;
                Log.d("----------------","usl연결 oK?");
            }

        public  void setURL3(String id) {
            Log.d("---------------------","LoginTask http 연결");
//            urlstr3 = "http://70.12.226.146/oracledb/androidCardPlus.jsp?id="+id;

            urlstr = "http://192.168.1.162/oracledb/androidLogin.jsp?id="+id;
            Log.d("----------------","usl연결 oK?");
        }




        //thread와 동일 형식
        @Override //시작하기전
        protected void onPreExecute() {
            Log.d("----","onPreExecute");
            progressDialog.setTitle("HTTP Connection...");
            progressDialog.setTitle("Please Wait...!");

            progressDialog.setCancelable(false);
            progressDialog.show();
            Log.d("---------","dialog show");
//            progressDialog.dismiss();
        }



        @Override
        protected String doInBackground(String... strings) {

            Log.d("---------","do in background 확인");
            try {
                String str;
                URL url = new URL(urlstr);
                Log.d("---------","url="+urlstr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.

                conn.setDoInput(true);
                conn.connect();


                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    //jsp에서 보낸 값을 받는다.
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("---------","ok");
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                    // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("---------","receiveMsg="+receiveMsg);
//            Toast.makeText(getApplicationContext() ,"회원가입완료?",Toast.LENGTH_LONG).show();
            return receiveMsg;
        }

        // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        @Override //끝날때
        protected void onPostExecute(String s) {

            Log.d("=======","s="+s.trim());

            if(s.trim().equals("1")) {
                Log.d("=======","s=1 :"+s.trim());

                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {

                    @Override
                    public void run() {
//                        Intent i = new Intent(MapActivity.this, MainActivity.class);
//                        //main으로 옮겨진 상태?
                        ArrayList<VerticalData> data = new ArrayList<>();
                        try {
                            URL url = new URL(urlstr3);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        int a = 0;

                        while (a < MAX_ITEM_COUNT) {
                            if(a==0){
                                data.add(new VerticalData(R.drawable.registercard, a+"번째 데이터"));
                            }else if(a==1){
                                data.add(new VerticalData(R.drawable.hana, a+"번째 데이터"));
                            }
                            else if(a==2){
                                data.add(new VerticalData(R.drawable.shinhan, a+"번째 데이터"));
                            }else if(a==3){
                                data.add(new VerticalData(R.drawable.woori, a+"번째 데이터"));
                            }else if(a==4){
                                data.add(new VerticalData(R.drawable.ibk, a+"번째 데이터"));
                            }
                            a++;
                        }
                        // 기본값이 VERTICAL
                        mLayoutManager = new LinearLayoutManager(MapActivity.this);
                        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                        // setLayoutManager
                        mVerticalView.setLayoutManager(mLayoutManager);

                        // init Adapter
                        mAdapter = new VerticalAdapter();

                        // set Data
                        mAdapter.setData(data);

                        // set Adapter
                        mVerticalView.setAdapter(mAdapter);
//
//                        startActivity(i);
//                        finish();


                    }
                },2000);
//                2초후 메인페이지 이동



            }else if(s.trim().equals("0")){
                Log.d("=======","s=0 :"+s.trim());
                Toast.makeText(MapActivity.this,"등록된 카드가 없습니다. 카드등록페이지로 이동합니다.", Toast.LENGTH_SHORT).show();


                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Intent i = new Intent(MapActivity.this, CheckOutActivity.class);


                        ArrayList<VerticalData> data = new ArrayList<>();

                        startActivity(i);
                        finish();


                    }
                },2000);
            }
            progressDialog.dismiss();





        }

    }
}
////////////////////////////////////////////////////////////////////////////////////






//    private List<lst> itemsList = new ArrayList<lst>();
//    private RecyclerViewAdapter adapter2;
//
//    private RecyclerView listview;
//    private MyAdapter adapter;
//    RecyclerView recyclerview;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);

//        recyclerview = findViewById(R.id.recycler);
//
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerview.setLayoutManager(linearLayoutManager);
//
//        recyclerview.setAdapter(adapter);
//        Set<String> noti_set = PreferenceManager.getDefaultSharedPreferences(this).getStringSet("noti_set", new HashSet<String>());
//        for (String noti : noti_set) {
//            String[] notification = noti.split("---");
//            String title = notification[0];
//            String msg = notification[1];
//            lst ls = new lst();
//            ls.setMsg(msg);
//            ls.setTitle(title);
//            itemsList.add(ls);
//            adapter.notifyDataSetChanged();

//----------------------
// 파이어베이스 에서 데이터를 가져 옴
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//
//                    User user = snapshot.getValue(User.class);
//                    user.User = snapshot.getKey();
//                    Log.i("id", user.User);
//                    Log.i("Userid", user.getUserid());
//                    Log.i("UserAge", user.getUserage());
//
//                    userList.add(user);
//                }
//
//                //어뎁터한테 데이터 넣어줬다고 알려줌 (안하면 화면에 안나온다)
////                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
//            }
//        });