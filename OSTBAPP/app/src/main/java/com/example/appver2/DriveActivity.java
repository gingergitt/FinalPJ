package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import msg.Msg;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DriveActivity extends AppCompatActivity {

    private Socket clientSocket;

    Sender sender;
    Sender2 sender2;
    ImageView DriveStart;
    Button button5, button6;
    Msg msg;
    Msg msg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        DriveStart = (ImageView) findViewById(R.id.DriveStart);



            button5 = this.findViewById(R.id.button5);
            button6 = this.findViewById(R.id.button6);
            Intent i = getIntent();
            String id= i.getExtras().getString("id");
            msg = new Msg(id, "1",null);
            msg2 = new Msg(id, "0", null);
            Log.d("---------id는:",id);



        button5.setOnClickListener(new View.OnClickListener() {
            boolean clicks = true;
        //엔진 스타트
                @Override
                public void onClick(View v) {
                    Log.d("--------","setOnClickListener");
                    if(id !=null ){
                        if(clicks == true) {

                            Toast.makeText(getApplicationContext(),"1번째클릭",Toast.LENGTH_SHORT).show();
                            Log.d("--------","setOnClickListener");
                            new ConnectThread("70.12.225.143", 8888).start(); //Thread시작

                            clicks=false;
                        } else {
                            Toast.makeText(getApplicationContext(),"2번째클릭",Toast.LENGTH_SHORT).show();
                            //엔진off (0을 여기서 보내면 시동이 꺼진다.)
                            new DisconnectThread("70.12.225.143", 8888).start(); //Thread시작
                        }
                    }
                }
        });




        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(DriveActivity.this,MainActivity.class);
                    startActivity(intent);
                }

        });

//
//        DriveStart.setOnClickListener(new ImageView.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                if(id !=null){
//                    new ConnectThread("70.12.225.143", 8888).start(); //Thread시작
//                }
//            }
//
//        });
//        Log.d("=======","imageview눌림?");

    }




    class ConnectThread extends Thread {

        String ip;
        int port;

        public ConnectThread() {
        }

        public ConnectThread(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {

            try {
                clientSocket = new Socket(ip, port);
                Log.d("-----------", "Connect ok");
//                Toast.makeText(getApplicationContext(),ip+"에 연결되었습니다.",Toast.LENGTH_SHORT).show();



            } catch (Exception e) {
                while (true) {
                    Log.d("-----------", "Connect fail");
                    try {
                        Thread.sleep(1000);
                        clientSocket = new Socket(ip, port);
                        break;
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

            try {
                sender = new Sender(clientSocket);
                sender.setMsg(msg);
                new Thread(sender).start();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            Intent intent = new Intent(DriveActivity.this, CheckOutActivity.class);
            startActivity(intent);
//            finish();
        } //end run

    }

    //-------------------------------------------------------------------------------------------
    class DisconnectThread extends Thread {

        String ip;
        int port;

        public DisconnectThread() {
        }

        public DisconnectThread(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {

            try {
                clientSocket = new Socket(ip, port);
                Log.d("-----------", "시동off스레드: Connect ok");
//                Toast.makeText(getApplicationContext(),ip+"에 연결되었습니다.",Toast.LENGTH_SHORT).show();



            } catch (Exception e) {
                while (true) {
                    Log.d("-----------", "시동off스레드: Connect fail");
                    try {
                        Thread.sleep(1000);
                        clientSocket = new Socket(ip, port);
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            try {
                sender2 = new Sender2(clientSocket);
                sender2.setMsg2(msg2);
                new Thread(sender2).start();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            Log.d("-----------","engine off 되었습니다.");
            Handler mhandler = new Handler(Looper.getMainLooper());
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Engine을 off합니다.",Toast.LENGTH_SHORT).show();
                }
            },1000);


        } //end run

    }

    class Sender implements Runnable {

        OutputStream os;
        ObjectOutputStream oos;
        Msg msg;


        public Sender(Socket socket) throws IOException {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        }

        public void setMsg(Msg msg) {
            Log.d("-----------","setMsg");

            this.msg = msg;

        }


        @Override
        public void run() {
            if (oos != null) {
                try {
                    oos.writeObject(msg);

                    Log.d("-----------","msg="+msg.getTxt());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class Sender2 implements Runnable{
        OutputStream os;
        ObjectOutputStream oos;
        Msg msg2;

        public Sender2(Socket socket) throws IOException {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        }

        public void setMsg2(Msg msg2) {
            Log.d("-----------","setMsg2");
            this.msg2 = msg2;
        }

        @Override
        public void run() {
            if (oos != null) {
                try {
                    oos.writeObject(msg2);

                    Log.d("-----------","msg2="+msg2.getTxt());


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }




}





