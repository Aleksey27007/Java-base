package com.aston.javabase.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // серверсокет прослушивает порт 6678
        try (ServerSocket server = new ServerSocket(6678)) {
            System.out.println("Сервер запущен!");

            // accept() будет ждать пока  кто-нибудь не захочет подключиться
            try (Socket clientSocket = server.accept();
                 // поток чтения из сокета
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 // поток записи в сокет
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
            ) {
                String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                System.out.println("От клиента получено: " + word);
                out.write("Привет, это Сервер! Вы написали : " + word + "\n");
                out.flush(); // выталкиваем все из буфера

                System.out.println("Сервер закрыт!");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
