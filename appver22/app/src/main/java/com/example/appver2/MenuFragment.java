package com.example.appver2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.appver2.ui.login.LoginActivity.idbt;
import static com.example.appver2.ui.login.LoginActivity.pwdbt;

public class MenuFragment extends Fragment {
    //
    // fragment_main.xml 파일과 인플레이션으로 연결해주는것을 메모리 객체화를 시켜주어야한다
    MainActivity activity;
    private RecyclerView mVerticalView;
    private VerticalAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int MAX_ITEM_COUNT = 5;
    ArrayList<VerticalData> data = new ArrayList<>();


    ProgressBar loading;
    ProgressDialog progressDialog;


//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        activity = (MainActivity)getActivity();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 인플레이션이 가능하다, container 이쪽으로 붙여달라, fragment_main을
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_menu,container,false);
        // rootview가 플래그먼트 화면으로 보이게 된다. 부분화면을 보여주고자하는 틀로 생각하면 된다.

//        Button button2 = (Button) rootview.findViewById(R.id.button2);

        // RecyclerView binding
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            String cardname = extra.getString("cardname");
            String cardagency = extra.getString("cardagency");


            Toast.makeText(getActivity(),cardname+cardagency,Toast.LENGTH_SHORT).show();

        }



        mVerticalView = (RecyclerView) rootview.findViewById(R.id.vertical_list);
        progressDialog = new ProgressDialog(getActivity());

        Intent intent = getActivity().getIntent(); /*데이터 수신*/
        String id = idbt.getText().toString(); /*String형*/
        String pwd = pwdbt.getText().toString(); /*int형*/
//        String id = "admin";
//        String pwd = "admin";

       CardMatchTask cardmatchtask = new CardMatchTask();
        cardmatchtask.setURL(id,pwd);
        cardmatchtask.setURL3(id);
        cardmatchtask.execute();

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.onFragmentChange(0);
//            }
//        });


        return rootview;
    }

    // Card 등록 여부 확인 Task 실행 ----------------------------------------------------------------------

    public class CardMatchTask extends AsyncTask<String, Void, String> {
        private String receiveMsg;
        private String urlstr, urlstr3;


        public  void setURL(String id, String pwd) {
            Log.d("---------------------","LoginTask http 연결");
//            urlstr = "http://70.12.226.146/oracledb/androidCardLogin.jsp?id="+id+"&pwd"+pwd;

            urlstr = "http://192.168.0.11/oracledb/androidLogin.jsp?id="+id+"&pwd"+pwd;

            Log.d("----------------","usl연결 oK?");
        }

        public  void setURL2(String id, String pwd) {
            Log.d("---------------------","LoginTask http 연결");
//                urlstr = "http://70.12.226.146/oracledb/androidCard.jsp?id="+id+"&pwd"+pwd;

            urlstr = "http://192.168.0.11/oracledb/androidLogin.jsp?id="+id+"&pwd"+pwd;
            Log.d("----------------","usl연결 oK?");
        }

        public  void setURL3(String id) {

            Log.d("---------------------","CardLoginTask http 연결");
//
//            urlstr3 = "http://70.12.226.146/oracledb/androidCardPlus.jsp?id="+id;

            urlstr = "http://192.168.0.11/oracledb/androidCardLogin.jsp?id="+id;
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

                Toast.makeText(getActivity(),"등록된 카드가 있습니다. 카드 관리 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();

                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {



                    @Override
                    public void run() {
//                        Intent i = new Intent(MapActivity.this, MainActivity.class);
                        Intent i = new Intent(getActivity(),MainActivity.class);
                        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~intent값 받아오기



                        i = getActivity().getIntent();
                        String cardno= i.getExtras().getString("cardno");

//                        //main으로 옮겨진 상태?
                        ArrayList<VerticalData> data = new ArrayList<>();
                        try {
                            URL url = new URL(urlstr3);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        int a=0;

                        while (a < MAX_ITEM_COUNT) {
                            if(a==0){
                                data.add(new VerticalData(R.drawable.registercard, cardno+"님의카드"));
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
                        mLayoutManager = new LinearLayoutManager(getActivity());
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
                Toast.makeText(getActivity(),"등록된 카드가 없습니다. 카드등록페이지로 이동합니다.", Toast.LENGTH_SHORT).show();


                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Intent i = new Intent(getActivity(), CheckOutActivity.class);


                        ArrayList<VerticalData> data = new ArrayList<>();

                        startActivity(i);
//                        finish();


                    }
                },2000);
            }
            progressDialog.dismiss();





        }

    }
}


