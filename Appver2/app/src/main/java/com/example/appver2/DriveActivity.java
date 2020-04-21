package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import msg.Msg;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DriveActivity extends AppCompatActivity {

    private Socket clientSocket;

    Sender sender;
    ImageView DriveStart;
    Button button5, button6;
    Msg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        DriveStart = (ImageView) findViewById(R.id.DriveStart);
            button5 = findViewById(R.id.button5);
            button6 = findViewById(R.id.button6);
            Intent i = getIntent();
            String id= i.getExtras().getString("id");
            msg = new Msg(id, "1",null);
            Log.d("---------id는:",id);

        button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(id !=null){
                        new ConnectThread("70.12.225.143", 8888).start(); //Thread시작
                    }
                }
        });

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
                    Log.d("-----------",msg.getTxt());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}





