package msg;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    Socket socket;
    Sender sender;

    public Client() {
    }

    public Client(String address, int port, Msg msg) throws IOException {

        try {
            socket = new Socket(address, port);

        } catch (Exception e) {
            while (true) {
                System.out.println("Retry..");
                try {
                    Thread.sleep(1000);
                    socket = new Socket(address, port);
                    break;
                } catch (Exception e1) {
                }
            }
        }

        System.out.println("Connected Server:" + address);

        sender = new Sender(socket);

//         Msg msg = new Msg(id, "1", null);

        sender.setMsg(msg);
        new Thread(sender).start();

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
            this.msg = msg;
        }

        @Override
        public void run() {
            if (oos != null) {
                try {
                    System.out.println(msg.getTxt());
                    oos.writeObject(msg);
                } catch (IOException e) {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        return;
                    }

                }
            }
        }

    }

    public static void main(String[] args) {
//      Client client = null;
//      try {
//         client = new Client("70.12.226.14", 8888);
////         client.startClient2();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
    }


}