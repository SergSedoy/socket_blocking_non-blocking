package task2_client_server_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static final int SERVER_PORT = 34555;

    public static void main(String[] args) {

        try {
            final ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("localhost", SERVER_PORT));

            while (true) {
                try (SocketChannel socketChannel = serverChannel.accept()) {
                    final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                    while (socketChannel.isConnected()) {
                        int bytesCount = socketChannel.read(inputBuffer);
                        if (bytesCount == -1) break;

                        final String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                        inputBuffer.clear();
                        System.out.println("Получено сообщение от клиента: " + msg);

                        String[] arr = msg.trim().split(" ");

                        String message = "";
                        for (String world : arr)
                            message += world.trim();

                        socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
