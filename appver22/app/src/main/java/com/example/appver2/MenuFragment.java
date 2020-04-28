package com.example.appver2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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
//    ArrayList<String> cardinfo = new ArrayList<>();

    ProgressBar loading;
    ProgressDialog progressDialog;
TextView RecentProduct, RecentPrice;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 인플레이션이 가능하다, container 이쪽으로 붙여달라, fragment_main을
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_menu,container,false);

        RecentProduct = rootview.findViewById(R.id.RecentProduct);
        RecentPrice = rootview.findViewById(R.id.RecentPrice);


//


        mVerticalView = (RecyclerView) rootview.findViewById(R.id.vertical_list);


        progressDialog = new ProgressDialog(getActivity());

        Intent i = getActivity().getIntent(); /*데이터 수신*/
        String id = idbt.getText().toString(); /*String형*/
        String pwd = pwdbt.getText().toString(); /*int형*/


//        int paymentno = Integer.parseInt(RecentPay.getText().toString());
        int paymentno = 0;
//        String productname = RecentProduct.getText().toString();
//        String productprice = RecentPrice.getText().toString();
//        String cardagency;





//        Log.d("--------","cardname="+cardname+"cardagency="+cardagency);



//        String id = "admin";
//        String pwd = "admin";
        Log.d("--------","id="+id+"pwd="+pwd);

       CardMatchTask cardmatchtask = new CardMatchTask();
       PayTask payTask = new PayTask();
        cardmatchtask.setURL(id,pwd);

        cardmatchtask.setURL3(id);
        cardmatchtask.execute();
        payTask.setURL4(id);

        payTask.execute();




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
        private String receiveA;
        private String urlstr,urlstr2, urlstr3, urlstr4;


        public  void setURL(String id, String pwd) {
            Log.d("---------------------","LoginTask http 연결");
//            urlstr = "http://70.12.226.146/oracledb/androidCardLogin.jsp?id="+id+"&pwd"+pwd;

//            urlstr = "http://192.168.0.6/oracledb/androidLogin.jsp?id="+id+"&pwd="+pwd;

            urlstr = "http://192.168.43.198:8088/finalpj/androidLogin.jsp?id="+id+"&pwd="+pwd;


            Log.d("----------------","usl연결 oK?");
        }

        public  void setURL2(String id,String cardname, String cardagency) {
            Log.d("---------------------","card확인Task http 연결");
//                urlstr = "http://70.12.226.146/oracledb/androidCard.jsp?id="+id+"&pwd"+pwd;

//            urlstr = "http://192.168.1.162/oracledb/androidLogin.jsp?id="+id+"&pwd="+pwd;



            urlstr2 = "http://192.168.43.198:8088/finalpj/androidLogin.jsp?id="+id+"&cardname="+cardname+"&cardagency="+cardagency;

            Log.d("----------------","urlstr2="+urlstr2);
        }

        public  void setURL3(String id) {

            Log.d("---------------------","CardLoginTask http 연결");
//
//            urlstr3 = "http://70.12.226.146/oracledb/androidCardPlus.jsp?id="+id;
//            urlstr3 = "http://192.168.0.6/oracledb/androidCardPlus.jsp?id="+id;


            urlstr3 = "http://192.168.43.198:8088/finalpj/androidCardPlus.jsp?id="+id;
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
            HttpURLConnection conn=null;
            Log.d("---------","do in background 확인");
            try {
                String str;
                URL url = new URL(urlstr);
                Log.d("---------","url="+urlstr);
                conn = (HttpURLConnection) url.openConnection();
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
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
            conn.disconnect();
            Log.d("---------","receiveMsg="+receiveMsg);
            return receiveMsg;
        }

        // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        @Override //끝날때
        protected void onPostExecute(String s) {

            s = receiveMsg.trim();
            Log.d("=======", "s=" + s);

            if (s.trim().equals("1")) {
                Log.d("=======", "s=1 :" + s);

                Toast.makeText(getActivity(), "등록된 카드가 있습니다. 카드 관리 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();


                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getActivity(), MainActivity.class);
//                        int paymentno = RecentPay.getText().toString();




//                        //main으로 옮겨진 상태?
                        ArrayList<VerticalData> data = new ArrayList<>();
                        try {
                            URL url = new URL(urlstr4);




                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        int a = 0;

                        while (a < MAX_ITEM_COUNT) {
                            if (a == 1) {
                                data.add(new VerticalData(R.drawable.card22, a+ "번째 카드"));
                            } else if (a == 2) {
                                data.add(new VerticalData(R.drawable.registercard, a + "번째 데이터"));
                            } else if (a == 3) {
                                data.add(new VerticalData(R.drawable.registercard, a + "번째 데이터"));
                            } else if (a == 4) {
                                data.add(new VerticalData(R.drawable.registercard, a + "번째 데이터"));
                            } else if (a == 5) {
                                data.add(new VerticalData(R.drawable.registercard, a + "번째 데이터"));
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
                }, 0);


//                2초후 메인페이지 이동



            }else if(s.equals("0")){
                Log.d("=======","s=0 :"+s.trim());
                Toast.makeText(getActivity(),"등록된 카드가 없습니다. 카드등록페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                Log.d("------------","확인~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                Intent i = new Intent(getActivity(), CheckOutActivity.class);

                startActivity(i);

//                Handler hand = new Handler();
//                hand.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        Intent i = new Intent(getActivity(), CheckOutActivity.class);
//
//                        startActivity(i);
////                        finish();
//                    }
//                },1000);
            }
            progressDialog.dismiss();






        }

    }

    //---------------------------ui 변경


//    private void processMethod() {
//
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //작업 내용
//
//                RecentPay.setText(ans1);
//            }
//        });
//    }


    public class PayTask extends AsyncTask<String, Void, String> {


        private String receiveA;
        private String  urlstr4;
        public void setURL4(String id) {
            Log.d("---------------------","Pay history task 연결");
//            urlstr4 = "http://70.12.226.146/oracledb/androidPayHistory.jsp?id="+id;
            urlstr4 = "http://192.168.43.198:8088/finalpj/androidPayHistory.jsp?id="+id;
//            urlstr4 = "http://192.168.0.6/oracledb/androidPayHistory.jsp?id="+id;
            Log.d("----------------","usl연결--- urlstr4");

        }



        @Override
        protected String doInBackground(String... strings) {



            //=========================================================================================
            try {
                String str;
                URL url = new URL(urlstr4);
                Log.d("---------", "url=" + urlstr4);
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
                        Log.d("----------","str="+str);
                        receiveA = buffer.toString().trim();
//

//                        String ans1 = receiveA.substring(0,receiveA.indexOf("/"));
//                        String ans2= receiveA.substring(receiveA.lastIndexOf("/")+1);
//                        Log.d("-----------","ans1="+ans1);
//                        Log.d("-----------","ans2="+ans2);

//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                runOnUiThread(new Runnable(){
//                                    @Override
//                                    public void run() {
//                                        // 해당 작업을 처리함
//                                        RecentPay.setText(ans1);
//                                    }
//                                });
//                            }
//                        }).start();


//            RecentPay.setText(receiveA);
//                        processMethod();

                    }





//                    RecentPay.setText("");
                    Log.d("---------", "ok");
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("---------", "receiveA=" + receiveA.trim());
            if(receiveA.trim().equals("000")) {
                Toast.makeText(getActivity(),"결제 내역이 없습니다. 메인으로 이동합니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);


            }else {

            }

            return receiveA;


        }

// 카드는 있는데 결제 내역 없을 때 처리하기 !
//=============================================================================================================================
        @Override
        protected void onPostExecute(String s) {

            Log.d("------","s="+s);
            String ans1 = s.substring(0,receiveA.indexOf("/"));
            String ans2= s.substring(receiveA.lastIndexOf("/")+1);
            Log.d("-----------","ans1="+ans1);
            Log.d("-----------","ans2="+ans2);
            if(s.trim() !=null) {

                RecentProduct.setText("상품이름: "+ans1);
                RecentPrice.setText("상품가격: "+ans2);
            }
            else if(s.trim() == null){
                Toast.makeText(getActivity(),"결제 내역이 없습니다. 메인으로 이동합니다.", Toast.LENGTH_SHORT).show();
                //핸들러 먹는지 확인할 것.

                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Intent i = new Intent(getActivity(), MainActivity.class);

                        startActivity(i);
//                        finish();
                    }
                },1000);

            }


        }
    }


}


