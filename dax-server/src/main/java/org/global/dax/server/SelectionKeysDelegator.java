package org.global.dax.server;

import org.global.dax.shared.CacheCommandWithArguments;
import org.global.dax.shared.SerializationUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

final class SelectionKeysDelegator {

    private static final CacheCommandInvoker CACHE_COMMAND_INVOKER = new CacheCommandInvoker();

    static void isAcceptable(SelectionKey selectionKey, Selector selector) {

        try {
            if (selectionKey.channel() instanceof ServerSocketChannel serverSocketChannel) {
                SocketChannel client = serverSocketChannel.accept();
                String hostAddress = client.socket().getInetAddress().getHostAddress();
                System.out.println("hostAddress: " + hostAddress);

                client.configureBlocking(false);

                // register that we are ready for reading
                client.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void isReadable(SelectionKey selectionKey, ByteBuffer buffer) {
        try {
            if (selectionKey.channel() instanceof SocketChannel client) {
                int bytesRead = client.read(buffer);
                if (bytesRead == -1) {
                    System.out.println("DISCONNECTED: " + client.socket().getInetAddress().getHostAddress());
                    client.close();
                }

                buffer.flip();
                byte [] serialized = buffer.array();
                CacheCommandWithArguments cacheCommandWithArguments = SerializationUtil.deserialize(serialized);
                System.out.println("Command that came from client: " + cacheCommandWithArguments);

                String output = CACHE_COMMAND_INVOKER.output(cacheCommandWithArguments);
                buffer.clear();
                buffer.put(output.getBytes());
                buffer.flip();

                // write the data back to the client
                while (buffer.hasRemaining()) {
                    client.write(buffer);
                }

                buffer.clear();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
