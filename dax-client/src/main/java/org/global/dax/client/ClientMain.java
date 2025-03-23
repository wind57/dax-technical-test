package org.global.dax.client;


import org.global.dax.shared.CacheCommandWithArguments;
import org.global.dax.shared.CommandParser;
import org.global.dax.shared.SerializationUtil;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public final class ClientMain {

    public static void main(String[] args) {
        startClient(new Scanner(System.in));
    }

    static void startClient(Scanner scanner) {
        try (SocketChannel channelToTheServer = SocketChannel.open()) {
            channelToTheServer.connect(new InetSocketAddress("localhost", 5757));
            System.out.println("Connection established");

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    break;
                }

                boolean ok = populateChannelWithSanitizedCommand(buffer, command, channelToTheServer);
                // invalid or incorrect command
                if (!ok) {
                    continue;
                }

                buffer.clear();
                int bytesRead = channelToTheServer.read(buffer);
                if (bytesRead > 0) {
                    buffer.flip();
                    String data = new String(buffer.array(), buffer.position(), bytesRead);
                    System.out.println(data);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get the data from System.in and "place" it into the buffer
     */
    static boolean populateChannelWithSanitizedCommand(ByteBuffer buffer,
            String command, SocketChannel channelToTheServer) {

        command = CommandParser.sanitize(command);
        if (command.isEmpty()) {
            System.out.println("invalid command, try again");
            return false;
        }

        CacheCommandWithArguments cacheCommandWithArguments = CommandParser.parse(command);
        if (cacheCommandWithArguments == null) {
            System.out.println("invalid command, try again");
            return false;
        }
        byte [] serialized = SerializationUtil.serialize(cacheCommandWithArguments);

        buffer.clear().put(serialized).flip();

        // write it to the server channel
        try {
            while (buffer.hasRemaining()) {
                channelToTheServer.write(buffer);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return true;

    }
}
