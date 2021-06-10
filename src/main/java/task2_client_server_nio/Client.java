package task2_client_server_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        InetSocketAddress socketAdress = new InetSocketAddress("127.0.0.1", Server.SERVER_PORT);
        try {
            final SocketChannel socketChanel = SocketChannel.open();
            socketChanel.connect(socketAdress);
            try (Scanner sca = new Scanner(System.in)) {
                final ByteBuffer inpBuffer = ByteBuffer.allocate(2 << 10);
                String msg;

                while (true) {
                    System.out.println("Введите сообщение для сервера...");
                    msg = sca.nextLine();

                    if (msg.equals("end")) break;

                    socketChanel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));

                    Thread.sleep(2000);

                    int bytesCount = socketChanel.read(inpBuffer);
                    System.out.println("сообщение без пробелов: " + new String(inpBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8));
                    inpBuffer.clear();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                socketChanel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}