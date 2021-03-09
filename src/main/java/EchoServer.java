import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(65500)){ //используем try для того чтоб автомотически закрыть socket
            System.out.println("Server started");// печатаем если запустилось
            Socket socket = serverSocket.accept();// пока кто-нибудь не подключится дальше код не будет исполнятся
            System.out.println("Connected");// печатаем если подклился
            DataInputStream in = new DataInputStream(socket.getInputStream());// читаем данные можно даже строки
            //InputStream in = new DataInputStream(socket.getInputStream()); // читаем тодько байты
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());// отправляем данные

            final Scanner SCANNER = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while (true){
                            String string = SCANNER.nextLine();
                            out.writeUTF(string);
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true){
                String message = in.readUTF(); //читаем строку
                System.out.println("Received " + message);// печатаем ее в консоли
                out.writeUTF("Echo: " + message);// отправлем обратно для подтвеждения получения "эхом"
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
