package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    private ArrayList<ObjectOutputStream> connectedPersons = null;

    public static void main(String[] args) {
        new Server().go();
    }

    private void go() {
        connectedPersons = new ArrayList<ObjectOutputStream>();

        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                connectedPersons.add(out);

                Thread thread = new Thread(new ClientHandler(socket));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void tellEveryOne(Object o1, Object o2) {
        Iterator it = connectedPersons.iterator();
        while (it.hasNext()) {
            try {
                ObjectOutputStream out = (ObjectOutputStream) it.next();
                out.writeObject(o1);
                out.writeObject(o2);
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {

        private ObjectInputStream in = null;

        Socket clientSocket = null;

        public  ClientHandler(Socket socket) {
            try {
                clientSocket = socket;
                in = new ObjectInputStream(socket.getInputStream());
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        @Override
        public void run() {
            Object o2 = null;
            Object o1 = null;

            try {
                while (true) {
                    while ((o1 = in.readObject()) != null) {
                        o2 = in.readObject();
                        tellEveryOne(o1, o2);
                    }
                }
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

}
