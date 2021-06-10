package task1_client_server_blocking;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("введите число N: ");
            String msg = scanner.nextLine();
            out.println(msg);
            System.out.println("ответ SERVER: N-ый член ряда Фибоначчи " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
