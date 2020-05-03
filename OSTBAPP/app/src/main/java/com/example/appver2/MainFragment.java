package com.example.appver2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import msg.Msg;
//starbucks :  192.168.0.11

public class MainFragment extends Fragment {
    //
    // fragment_main.xml 파일과 인플레이션으로 연결해주는것을 메모리 객체화를 시켜주어야한다
    private Socket clientSocket;


    Sender sender;
    Sender2 sender2;

    Msg msg;
    Msg msg2;

    ImageView DriveStart;
    ImageButton button5;
    boolean clicks=true;
    FrameLayout container;
//    String id = "admin";

//
//    @Override
//    public void onAttachFragment(@NonNull Fragment childFragment) {
//        getActivity();
//        super.onAttachFragment(childFragment);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 인플레이션이 가능하다, container 이쪽으로 붙여달라, fragment_main을
        final ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);
        ImageButton button5 = (ImageButton) rootview.findViewById(R.id.button5);
        container = (FrameLayout) rootview.findViewById(R.id.container);
        Intent i = getActivity().getIntent();
        String id= i.getExtras().getString("id");


        msg = new Msg(id, "1", null);
        msg2 = new Msg(id, "0", null);
        Log.d("---------id는:", id);

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                MainActivity mainActivity = (MainActivity) getActivity();
//                mainActivity.onFragmentChanged(0);

                Log.d("--------", "setOnClickListener");
                if(id !=null) {
                    Log.d("---------id는:", id);
                    if(clicks == true) {

                            Toast.makeText(getActivity(),"Engine On",Toast.LENGTH_SHORT).show();
                            Log.d("--------","setOnClickListener");
//                        new ConnectThread("70.12.225.143", 8888).start(); //Thread시작
//                        new ConnectThread("192.168.0.27", 8888).start(); //Thread시작
                        new ConnectThread("192.168.43.88", 8888).start(); //Thread시작
                        button5.setImageResource(R.drawable.p_off);


                        clicks=false;
                        } else {
                            Toast.makeText(getActivity(),"Engine Off",Toast.LENGTH_SHORT).show();
                            //엔진off (0을 여기서 보내면 시동이 꺼진다.)
//                            new DisconnectThread("70.12.225.143", 8888).start(); //Thread시작
                        button5.setImageResource(R.drawable.p_on);
                        new DisconnectThread("192.168.43.88", 8888).start(); //Thread시작

                    }

                }


            }
        });

//        Button button5 = getActivity().findViewById(R.id.button5);
        return rootview;            // 플레그먼트 화면으로 보여주게 된다.
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
//            finally {
//                try {
////                    clientSocket.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//                Intent intent = new Intent(MainActivity.this, CheckOutActivity.class);
//                startActivity(intent);
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
                    Toast.makeText(getActivity(),"Engine을 off합니다.",Toast.LENGTH_SHORT).show();
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
