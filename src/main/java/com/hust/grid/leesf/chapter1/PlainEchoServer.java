package com.hust.grid.leesf.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LEESF on 2017/4/24.
 */

public class PlainEchoServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            while (true) {
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " +
                        clientSocket);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            BufferedReader reader =
                                    new BufferedReader(
                                        new InputStreamReader(clientSocket.getInputStream())
                                    );
                            PrintWriter writer =
                                    new PrintWriter(clientSocket.getOutputStream(), true);
                            while(true) {
                                writer.println(reader.readLine());
                                writer.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
