package com.example.appver2.ui.login;


import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appver2.MainActivity;
import com.example.appver2.R;
import com.example.appver2.RegisterActivity;
import com.example.appver2.SaveSharedPreference;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//필요기능 1: 이메일? 아이디? 검증 -> 아이디가 없거나 일치하지 않으면 토스트 메시지 보내고 다시 입력하게 함
// 2: 비밀번호 일치하는지 확인 -> 일치하지 않으면 다시 입력하도록 함
// 3: 회원가입 버튼 만들어서 회원가입 페이지로 (인텐트) 넘김
// 4 : 로그인 성공 시 메인으로 넘어가도록 함
// 순서 : 메인로고 (onPreExecute) -> 로그인 액티비티 -> 성공시 메인 or 레지스터 화면 -> 레지스터 화면에서 가입 성공 시 메인 -> 메인 구상하기 .
public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    // UI references.
    private ImageView logo;
    private EditText idbt, pwdbt;
    String sidbt, spwdbt;
    Button loginbt, regibt;
    ProgressBar loading;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         idbt = findViewById(R.id.idbt);
        pwdbt = findViewById(R.id.pwdbt);
        logo = findViewById(R.id.logo);
        loginbt = findViewById(R.id.loginbt);
        regibt = findViewById(R.id.regibt);
        loading =  findViewById(R.id.loading);
        progressDialog = new ProgressDialog(this);

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("---","login");
                String sidbt = idbt.getText().toString();
                String spwdbt = pwdbt.getText().toString();
                LoginTask loginTask = new LoginTask(sidbt,spwdbt);
                loginTask.execute();
            }
        });



    }

    class LoginTask extends AsyncTask<String, Void, String> {
        String receiveMsg;
        String urlstr;

        public LoginTask(String id, String pwd){
            Log.d("---------------------","LoginTask http 연결");
            urlstr = "http://70.12.113.248/orcledb/androidLogin.jsp?";
            urlstr += "id="+id+"&pwd="+pwd;
        }



        //thread와 동일 형식
        @Override //시작하기전
        protected void onPreExecute() {
            Log.d("----","onPreExecute");
            progressDialog.setTitle("HTTP Connection...");
            progressDialog.setTitle("Please Wait...");

            progressDialog.setCancelable(false);
            progressDialog.show();

        }



        @Override
        protected String doInBackground(String... strings) {


            try {
                String str;
                URL url = new URL(urlstr);

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
            //jsp로부터 받은 리턴 값입니다.
            Log.d("---------",receiveMsg);
//            Toast.makeText(getApplicationContext() ,"회원가입완료?",Toast.LENGTH_LONG).show();
            return receiveMsg;
        }

        // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        @Override //끝날때
        protected void onPostExecute(String s) {
            progressDialog.dismiss();

            if(s.trim().equals("1")) {
                if(SaveSharedPreference.getUserName(LoginActivity.this).length() == 0)
                    SaveSharedPreference.setUserName(LoginActivity.this, idbt.getText().toString());

                Intent intent =
                        new Intent(getApplicationContext(),
                                MainActivity.class);

                startActivity(intent);


            }else {
                Toast.makeText(LoginActivity.this,"틀렸습니다.", Toast.LENGTH_SHORT);
            }


        }

    }



    }
















