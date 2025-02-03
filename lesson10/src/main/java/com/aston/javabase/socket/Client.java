package com.aston.javabase.socket;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (   // этой строкой мы запрашиваем у сервера доступ на соединение
                // адрес - локальный хост, порт - 6678, такой же как у сервера
                Socket clientSocket = new Socket("localhost", 6678);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                // читать сообщения с сервера
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        ) {

            System.out.println("Вы что-то хотели сказать? Введите это здесь:");
            String word = reader.readLine(); // ждём пока клиент что-нибудь напишет в консоль

            out.write(word + "\n"); // отправляем сообщение на сервер
            out.flush();

            String serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
            System.out.println("Клиент был закрыт...");

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
