package com.example.appver2.ui.login;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.appver2.MainActivity;
import com.example.appver2.R;
import com.example.appver2.RegisterActivity;

import org.json.JSONObject;

public class LoginActivity extends Activity {

    private AlertDialog dialog;
    // UI references.
    private ImageView logo;
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.

        logo = findViewById(R.id.logo);
        mEmailView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        // event handler

        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL){
                    return true;
                }
                return false;
            }

        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    //attemptLogin();

                    return true;
                }
                return false;
            }
        });

        // Button

        Button mEmailSignInButton = (Button) findViewById(R.id.login); // sign in button

        Button Register = (Button) findViewById(R.id.login2); // sign up button




        //버튼눌리면 레지스터 페이지로 가도록 한다.
        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                LoginActivity.this.startActivity(registerIntent);


            }
        });




        // event handler

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userID = mEmailView.getText().toString();

                String userPassword = mPasswordView.getText().toString();



                Response.Listener<String> responseLisner = new Response.Listener<String>(){






                    @Override

                    public void onResponse(String response) {

                        try{

                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");



                            if(success){

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                dialog = builder.setMessage("로그인에 성공했습니다")

                                        .setPositiveButton("확인", null)

                                        .create();

                                dialog.show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                LoginActivity.this.startActivity(intent);

                                finish();

                            }else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                dialog = builder.setMessage("계정을 다시 확인하세요")

                                        .setNegativeButton("다시시도", null)

                                        .create();

                                dialog.show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                LoginActivity.this.startActivity(intent);

                                finish();

                            }



                        }catch (Exception e){

                            e.printStackTrace();

                        }

                    }

                };



               // LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseLisner);

                //RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                //queue.add(loginRequest);



            }

        });



    }



    @Override

    protected void onStop() {

        super.onStop();

        if (dialog != null) {//다이얼로그가 켜져있을때 함부로 종료가 되지 않게함

            dialog.dismiss();

            dialog = null;

        }
    }





}












