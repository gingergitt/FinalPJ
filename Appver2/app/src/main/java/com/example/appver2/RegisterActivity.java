package com.example.appver2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter adapter;


    private String userid;
    private String userpwd;
    private String usergender;
    private String userage;
    private String userphone;
    private String useremail;
    private AlertDialog dialog;
    private boolean validate = false;
    private TextView textView1, textView2, textView3, textView4, textView5;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private Button registerButton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterTask task = new RegisterTask();
        task.execute("abc", "1234");


        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText phone = (EditText) findViewById(R.id.Phone);
        final Button registerbutton = findViewById(R.id.registerButton);
        Button validateButton = (Button) findViewById(R.id.validateButton);

        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        int genderGroupID = genderGroup.getCheckedRadioButtonId();
        usergender = ((RadioButton) findViewById(genderGroupID)).getText().toString();//초기화 값을 지정해줌

        //라디오버튼이 눌리면 값을 바꿔주는 부분
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                usergender = genderButton.getText().toString();
            }
        });

        //ID검증

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                if (validate) {
                    return;//검증 완료
                }
                //ID 값을 입력하지 않았다면
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID is empty")
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }


            }
        });

    }


            class RegisterTask extends AsyncTask<String, Void, String> {


                String sendMsg, receiveMsg;

                @Override
                // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.
                protected String doInBackground(String... strings) {
                    try {
                        String str;
                        URL url = new URL("http://70.12.113.248/oracledb/androidDB.jsp"


                        );//보낼 jsp 주소를 ""안에 작성합니다.
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.
                        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                        sendMsg = "id=id&pwd=pwd&age=age&gender=gender&phone=phone&email=email&agree=agree";


                        //보낼 정보인데요. GET방식으로 작성합니다. ex) "id=rain483&pwd=1234";
                        //회원가입처럼 보낼 데이터가 여러 개일 경우 &로 구분하여 작성합니다.
                        osw.write(sendMsg);//OutputStreamWriter에 담아 전송합니다.
                        osw.flush();
                        //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
                        if (conn.getResponseCode() == conn.HTTP_OK) {
                            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                            BufferedReader reader = new BufferedReader(tmp);
                            StringBuffer buffer = new StringBuffer();
                            //jsp에서 보낸 값을 받겠죠?
                            while ((str = reader.readLine()) != null) {
                                buffer.append(str);
                            }
                            receiveMsg = buffer.toString();

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
                    return receiveMsg;
                }
            }



}


