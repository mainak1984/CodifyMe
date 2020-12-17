package edu.codifyme.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BasicClientServer {
    // ICMP testing
    // InetAddress.getByName(ip).isReachable(timeout);

    public static void main (String[] args) {
        Runnable server = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket listenSock = new ServerSocket(8899);
                    Socket acceptSock = listenSock.accept();

                    DataInputStream dis = new DataInputStream(acceptSock.getInputStream());
                    String line = dis.readUTF();

                    DataOutputStream dos = new DataOutputStream(acceptSock.getOutputStream());
                    dos.writeUTF("Hello " + line);

                    dis.close();
                    dos.close();
                    acceptSock.close();
                    listenSock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Socket clientSock = new Socket("localhost", 8899);
            Scanner sc = new Scanner(System.in);

            DataOutputStream dos = new DataOutputStream(clientSock.getOutputStream());
            System.out.println("Write something");
            dos.writeUTF(sc.next());

            DataInputStream dis = new DataInputStream(clientSock.getInputStream());
            String reply = dis.readUTF();
            System.out.println(reply);

            clientSock.close();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Client not connecting");
        }
    }
}
