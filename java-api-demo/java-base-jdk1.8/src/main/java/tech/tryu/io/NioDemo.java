package tech.tryu.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * java的Nio框架实现
 *
 * @author YU
 * @date 2022-07-12 22:29
 */
public class NioDemo {
    private static final Logger LOGGER = Logger.getLogger("NioDemo");

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(83));

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(100) == 0) {
                continue;
            }
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

            while (selectionKeys.hasNext()) {
                SelectionKey readyKey = selectionKeys.next();
                selectionKeys.remove();
                SelectableChannel selectableChannel = readyKey.channel();
                if (readyKey.isValid() && readyKey.isAcceptable()) {
                    LOGGER.info("==Channel 通道已经准备好了");
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectableChannel;
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    registerSocketChannel(socketChannel, selector);
                } else if (readyKey.isValid() && readyKey.isReadable()) {
                    LOGGER.info("== socket channel 数据准备完成，可以读取 ==");
                }
            }
        }
    }

    private static void registerSocketChannel(SocketChannel socketChannel, Selector selector) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
    }


    private static void registerSocketChannel(SelectionKey readyKey) throws IOException {
        SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
        InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
        int resourcePort = sourceSocketAddress.getPort();
        ByteBuffer contextBytes = (ByteBuffer) readyKey.attachment();
        int realLen = -1;
        try {
            realLen = clientSocketChannel.read(contextBytes);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            clientSocketChannel.close();
            return;
        }

        if (realLen == -1) {
            LOGGER.info("===缓冲区没有数据===");
            return;
        }

        contextBytes.flip();
        byte[] messageBytes = contextBytes.array();
        String messageEncode = new String(messageBytes, StandardCharsets.UTF_8);
        String message = URLDecoder.decode(messageEncode, String.valueOf(StandardCharsets.UTF_8));
        if (message.indexOf("over") != -1) {
            contextBytes.clear();
            LOGGER.info("端口" + resourcePort + "客户端发来的信息====message : " + message);
            byte[] responseMessage = URLEncoder.encode("回发处理结果", String.valueOf(StandardCharsets.UTF_8)).getBytes();
            ByteBuffer sendBuffer = ByteBuffer.wrap(responseMessage);
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            LOGGER.info("端口" + resourcePort + " 客户端信息还没接受完，继续接受===message" + message);
            contextBytes.position(realLen);
            contextBytes.limit(contextBytes.capacity());
        }


    }
}
