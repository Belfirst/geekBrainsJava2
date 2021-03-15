package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthService;
import ru.geekbrains.chat.server.auth.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private final AuthService authService;
    private final ArrayList<ClientHandler> handlers;

    public Server(){
        authService = new AuthService();
        handlers = new ArrayList<ClientHandler>();
        try {
            serverSocket = new ServerSocket(8989);
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException{

        while (true){
            System.out.println("Server is waiting for a connected...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted" + client);
            new ClientHandler(this,client);
        }

    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickNameFree(String nickName){
        for(ClientHandler handler : handlers){
            if(handler.getName().equals(nickName)){
                return  false;
            }
        }
        return true;
    }

    public void broadcast(String message){
        for (ClientHandler handler : handlers) {
            handler.sendMessage(message);
        }
    }

    public void privateMessage(String message, String nickName){
        for(ClientHandler handler : handlers) {
            if (handler.getName().equals(nickName)) {
                handler.sendMessage(message);
            }
        }
    }

    public void subscribe(ClientHandler handler){
        handlers.add(handler);
    }

    public void unsubscribe(ClientHandler handler){
        handlers.remove(handler);
    }
}
