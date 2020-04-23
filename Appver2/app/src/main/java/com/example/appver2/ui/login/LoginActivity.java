package com.example.appver2.ui.login;


import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appver2.CheckOutActivity;
import com.example.appver2.DriveActivity;
import com.example.appver2.MainActivity;
import com.example.appver2.MapActivity;
import com.example.appver2.R;
import com.example.appver2.RegisterActivity;
import com.example.appver2.SaveSharedPreference;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//필요기능
//1: id, pwd미입력시 id를 입력하세요! 토스트 띄우고 task중지
//2: 자동로그인
//3: ui
//4: 로그인 시 00님 환영합니다.
// 순서 : 메인로고 (onPreExecute) -> 로그인 액티비티 -> 성공시 메인 or 레지스터 화면 -> 레지스터 화면에서 가입 성공 시 메인 -> 메인 구상하기 .


public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    // UI references.
    private ImageView logo;
    public static EditText idbt;
    public static EditText pwdbt;
    String sidbt, spwdbt;
    Button loginbt, regibt;
    ProgressBar loading;
    ProgressDialog progressDialog;
    Intent intent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         idbt = findViewById(R.id.idbt);
        pwdbt = findViewById(R.id.pwdbt);
        logo = findViewById(R.id.logo);
        loginbt = findViewById(R.id.loginbt);
        regibt = findViewById(R.id.regibt);
//        loading =  findViewById(R.id.loading);
        progressDialog = new ProgressDialog(this);



        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("---","login");
                String id = idbt.getText().toString();
                String pwd = pwdbt.getText().toString();

                if(idbt == null) {
                    Toast.makeText(getApplicationContext(),"id를 입력해주세요.",Toast.LENGTH_SHORT).show();
                    finish();                   //task 실행 안되도록 하기 .
                }else if (pwdbt == null) {
                    Toast.makeText(getApplicationContext(),"pwd를 입력해주세요.",Toast.LENGTH_SHORT).show();

                } else if (idbt == null && pwdbt == null ) {
                    Toast.makeText(getApplicationContext(),"id와 pwd를 입력해주세요.",Toast.LENGTH_SHORT).show();

                }
                LoginTask loginTask = new LoginTask();
                loginTask.setURL(id,pwd);

                loginTask.execute();
            }

        });



//        regibt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =
//                        new Intent(getApplicationContext(),
//                                MainActivity.class);
//
//                startActivity(intent);
//            }
//        });


    }

    public class LoginTask extends AsyncTask<String, Void, String> {
        private String receiveMsg;
        private String urlstr;


        public  void setURL(String id, String pwd) {
            Log.d("---------------------","LoginTask http 연결");
            urlstr = "http://70.12.226.146/oracledb/androidLogin.jsp?id="+id+"&pwd="+pwd;

//            urlstr = "http://192.168.0.20/orcledb/androidLogin.jsp?id="+id+"&pwd"+pwd;

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

//                /* 안드로이드 -> 서버 파라메터값 전달 */
//                OutputStream outs = conn.getOutputStream();
//                outs.write(urlstr.getBytes("UTF-8"));
//                outs.flush();
//                outs.close();


                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    //jsp에서 보낸 값을 받는다.
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("---------","에러~~");
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

           Log.d("=======","s="+s);

            if(s.trim().equals("1")) {
                Log.d("=======","s=1 :"+s);
                if(SaveSharedPreference.getUserName(LoginActivity.this).length() == 0)
                    SaveSharedPreference.setUserName(LoginActivity.this, idbt.getText().toString());
                Toast.makeText(LoginActivity.this, idbt.getText().toString()+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this,"메인페이지로 이동합니다!", Toast.LENGTH_LONG).show();

                //2초후 메인페이지 이동
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(LoginActivity.this, DriveActivity.class);
                        String userid = idbt.getText().toString();
                        String userpwd = pwdbt.getText().toString();
                        i.putExtra("id",userid ); /*송신*/
                        i.putExtra("pwd",userpwd);



                        startActivity(i);
                        finish();


                    }
                },2000);


            }else if(s.trim().equals("0")){
                Log.d("=======","s=0 :"+s);
                Toast.makeText(LoginActivity.this,"ID나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();


            }
            progressDialog.dismiss();





        }

    }
    //회원가입 창으로 이동함
    public void regibt(View v) {
        if(v.getId() == R.id.regibt) {
            Intent intent =
                    new Intent(getApplicationContext(),
                            RegisterActivity.class);

            startActivity(intent);
        }
    }

    }
















