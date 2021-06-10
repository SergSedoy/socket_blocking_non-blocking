package task1_client_server_blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 34555;

    public static void main(String[] args) {

        try (ServerSocket servSocket = new ServerSocket(SERVER_PORT);
             Socket socket = servSocket.accept();
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            int n = Integer.parseInt(in.readLine());
            System.out.println("число получено " + n);
            int f = fib(n);

            out.println(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int fib(int n) {
        int tmp = 0;
        if (n <= 2) {
            return 1;
        } else {

            int f1 = 1;
            int f2 = 1;
            for (int i = 2; i < n; i++) {
                tmp = f1 + f2;
                f1 = f2;
                f2 = tmp;
            }
        }
        return tmp;
    }
}

//в задаче 1 выбран способ взаимодействия Blocking, потому что в данном случае у нас однозадочность
// и нет смысла передавть на сервер ещё какие-либо данные пока идет расчет числа Фибоначчи.
