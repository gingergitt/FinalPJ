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
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appver2.R;
import com.example.appver2.RegisterActivity;

import org.json.JSONObject;
//필요기능 1: 이메일? 아이디? 검증 -> 아이디가 없거나 일치하지 않으면 토스트 메시지 보내고 다시 입력하게 함
// 2: 비밀번호 일치하는지 확인 -> 일치하지 않으면 다시 입력하도록 함
// 3: 회원가입 버튼 만들어서 회원가입 페이지로 (인텐트) 넘김
// 4 : 로그인 성공 시 메인으로 넘어가도록 함
// 순서 : 메인로고 (onPreExecute) -> 로그인 액티비티 -> 성공시 메인 or 레지스터 화면 -> 레지스터 화면에서 가입 성공 시 메인 -> 메인 구상하기 .
public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    // UI references.
    private ImageView logo;
    private EditText idbt, pwdbt, ckbt;  //ckbt -> pwd체크
    String sidbt, spwdbt, sckbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idbt = findViewById(R.id.idbt);
        pwdbt = findViewById(R.id.pwdbt);
        logo = findViewById(R.id.logo);


    }

    public void signbt(View v) {
        //버튼 누르면 동작
        sidbt = idbt.getText().toString();
        spwdbt = pwdbt.getText().toString();
        sckbt = ckbt.getText().toString();

        //패스워드 체크하기
        if (spwdbt.equals(sckbt)) {
            //정상일경우
//            RegisterActivity.RegisterTask rt = new RegisterActivity.RegisterTask();
//
//            rt.execute();

        } else {
            //불일치할 경우
            pwdbt.setText("");
            ckbt.setText("");
            Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다. 다시 입력하세요.", Toast.LENGTH_LONG).show();
        }

    }


    public void regibt(View v) {
        if (v.getId() == R.id.regibt) {
            Intent intent =
                    new Intent(getApplicationContext(),
                            RegisterActivity.class);

            startActivity(intent);
        }

    }
}












