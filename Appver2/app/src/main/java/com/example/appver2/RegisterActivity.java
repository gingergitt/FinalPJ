package com.example.appver2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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
    private String useragree;
    private AlertDialog dialog;
    private boolean validate = false;
    private TextView textView1, textView2, textView3, textView4, textView5;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
//    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterTask task = new RegisterTask();
        String res;
        try {
            res = task.execute("id01", "1234", "24", "F", "010-1111-1234",
                    "id01@gmail.com", "y").get();
            Log.i("------------리턴 값", res);
        } catch (Exception e) {
            e.printStackTrace();
        }


        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText ageText = findViewById(R.id.ageText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText phone = (EditText) findViewById(R.id.Phone);
        final Button registerbutton = findViewById(R.id.registerButton);
        Button validateButton = (Button) findViewById(R.id.validateButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

// ---------------------------------------------------------------------------------------------------------
        final RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        final int genderGroupID = genderGroup.getCheckedRadioButtonId();
//        usergender = ((RadioButton) findViewById(genderGroupID)).getText().toString();//초기화 값을 지정해줌
        RadioGroup AdGroup = (RadioGroup) findViewById(R.id.AdGroup);
        int AdGroupID = AdGroup.getCheckedRadioButtonId();
//        useragree = ((RadioButton) findViewById(AdGroupID)).getText().toString();
// ----------------------------------------------------------------------------------------------------------
        //(수신동의) 버튼이 눌리면 값을 바꿔주는 부분
        AdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int j) {
                RadioButton adButton = (RadioButton) findViewById(j);
                useragree = adButton.getText().toString();
                Log.d("-----", useragree);
            }
        });


        //라디오버튼이 눌리면 값을 바꿔주는 부분
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                usergender = genderButton.getText().toString();

                Log.d("-----", usergender);
            }
        });

        //ID검증(CheckID)

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = idText.getText().toString();
                if (validate) {
                    RegisterTask registerTask = new RegisterTask();
                    registerTask.execute();

                    dialog.setMessage("OK");

                    dialog.show();

                    return;//검증 완료
                }
                //ID 값을 입력하지 않았다면
                if (userid.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID is empty")
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }


            }
        });

// ----------------------------------------------------------------------
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                        idText.setEnabled(false);
                        validate = true;
                        idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ValidateRequest validateRequest = new ValidateRequest(userid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(validateRequest);
    //}

















        //Register(회원가입) 버튼이 눌렸을 때
        //빈 공간 체크, 가입 완료 시 로그인 화면으로 가도록 함.

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = idText.getText().toString();
                String userpwd = passwordText.getText().toString();
                String userage = ageText.getText().toString();
                String userphone = phone.getText().toString();
                String useremail = emailText.getText().toString();

                //ID 중복체크를 했는지 확인함
                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID 중복체크를 눌러주세요!")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }


                //빈칸이 있을 경우 토스트메시지
                if(userid==null) {
                    Toast.makeText(getApplicationContext(), "ID를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userpwd==null) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userage==null) {
                    Toast.makeText(getApplicationContext(), "나이를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userphone==null) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(useremail==null) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(checkBox1.isChecked() == false &&
                checkBox2.isChecked() == false &&
                checkBox3.isChecked() == false &&
                checkBox4.isChecked() == false &&
                checkBox5.isChecked() == false) {
                    Toast.makeText(getApplicationContext(),"관심분야를 하나이상 체크해주세요!",Toast.LENGTH_LONG);
                }
                // 비밀번호 확인
                if(userpwd.equals(passwordText)) {
                    //pwd 정상 확인시
                    RegisterTask registerTask = new RegisterTask();
                   registerTask.execute();

                }else {
                   Log.e("--------","패스워드 불일치");
                }

                //
//                JSONObject jsonObject = new JSONObject();

            }
        });






    }//onCreate


            class RegisterTask extends AsyncTask<String, Void, String> {


                String sendMsg, receiveMsg;

                @Override
                // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.
                protected String doInBackground(String... strings) {
                    sendMsg = "id="+userid+"&pwd="+userpwd+"&age="+userage+"&gender="+usergender
                +"&phone="+userphone+"&email="+useremail+"&agree="+useragree;
                    try {
                        String str;
                        URL url = new URL("http://70.12.113.248/oracledb/androidDB.jsp"


                        );

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.

                        conn.setDoInput(true);
                        conn.connect();

                        /* 안드로이드 -> 서버 파라메터값 전달 */

                        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                        osw.write(sendMsg);
                        osw.flush();
                        osw.close();

                        /* 서버 -> 안드로이드 파라메터값 전달 */
                        //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
                        if (conn.getResponseCode() == conn.HTTP_OK) {
                            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                            BufferedReader reader = new BufferedReader(tmp);
                            StringBuffer buffer = new StringBuffer();
                            //jsp에서 보낸 값을 받는다.
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


