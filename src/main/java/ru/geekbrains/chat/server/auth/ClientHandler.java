package ru.geekbrains.chat.server.auth;

import ru.geekbrains.chat.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new  Thread(() ->{

                try {
                    doAuth();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e){
            throw new RuntimeException("ClientHandler", e);
        }
    }

    public void doAuth() throws IOException{
        while (true){
            String input = in.readUTF();
            if(input.startsWith("-auth")){
                String[] credentials = input.split("\\s");
                AuthEntry maybeAuthEntry = server.getAuthService()
                        .findUserByCredentials(credentials[1],credentials[2]);
                if(maybeAuthEntry != null){
                    if(server.isNickNameFree(maybeAuthEntry.getNickName())){
                        sendMessage("CMD: auth ok");
                        name = maybeAuthEntry.getNickName();
                        server.broadcast(name + " logged in.");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged-in");
                    }
                } else {
                    sendMessage("Unknown user. Incorrect login/password");
                }
            } else {
                sendMessage("Invalid authentication request.");
            }
        }
    }

    public void sendMessage(String message){
        try{
            out.writeUTF(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void readMessage() throws IOException{
        while (true){
            String message = in.readUTF();
            if(message.startsWith("/w")){
                String privatMessage = "";
                String[] messages = message.split("\\s");
                String nickName = messages[1];
                for (int i = 2; i < messages.length; i++) {
                    privatMessage += messages[i];
                }
                server.privateMessage(name + ": " + privatMessage,nickName);
            } else server.broadcast(name + ": " + message);
        }
    }
}
