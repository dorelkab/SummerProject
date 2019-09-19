import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newCachedThreadPool;


public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executor;
    Object lock;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        lock=new Object();
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            executor= newCachedThreadPool();
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            System.out.println(String.format("Server starter at %s!", serverSocket));
            System.out.println(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
            System.out.println("Server is waiting for clients...");
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    System.out.println(String.format("Client excepted: %s", clientSocket));
                    executor.execute(() -> {
                        handleClient(clientSocket);
                        System.out.println(String.format("Finished handle client: %s", clientSocket));
                    });
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket Timeout - No clients pending!");
                }
            }
            serverSocket.close();
            executor.shutdown();
        } catch (IOException e) {
            System.out.println("IOException"+ e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            System.out.println(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException"+ e);
        }
    }

    public void stop() {
        System.out.println("Stopping server..");
        stop = true;
    }
}