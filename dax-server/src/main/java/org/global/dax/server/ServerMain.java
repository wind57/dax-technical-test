package org.global.dax.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public final class ServerMain {

    public static void main(String[] args) {

        // 1. ServerSocketChannel will listen for incoming TCP connections
        // 2. Bind the ServerSocketChannel to the InetSocketAddress
        // 3. Register the Selector to the SelectionKey
        //    basically think about it as a callback for that SelectionKey
        try (ServerSocketChannel channel = ServerSocketChannel.open();
            Selector selector = Selector.open()) {

            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(5757));

            // if the selector detects that the server socket channel
            // is ready to accept another connection
            channel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                // selectNow because it is not blocking
                if (selector.selectNow() == 0) {
                    Thread.onSpinWait();
                    continue;
                }

                buffer.clear();

                // channel keys that are ready for I/O
                for (SelectionKey selectionKey : selector.selectedKeys()) {

                    // ready to accept another socket channel
                    if (selectionKey.isAcceptable()) {
                        SelectionKeysDelegator.isAcceptable(selectionKey, selector);
                    }
                    // ready to read
                    else if (selectionKey.isReadable()) {
                        SelectionKeysDelegator.isReadable(selectionKey, buffer);
                    }
                }

                selector.selectedKeys().clear();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
