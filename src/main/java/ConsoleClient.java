import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",65500)){ //используем try для того чтоб автомотически закрыть socket
            System.out.println("Connected");// печатаем если подключились
            final DataInputStream in = new DataInputStream(socket.getInputStream());// читаем данные можно даже строки
            //InputStream in = new DataInputStream(socket.getInputStream()); // читаем тодько байты
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());// отправляем данные

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String message = in.readUTF();
                            System.out.println(message);
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true){
                String msg = SCANNER.nextLine(); // вводим сообщение
                out.writeUTF(msg); // отправляем сообщение
                Thread.sleep(200);
                msg = in.readUTF();
                System.out.println("Received " + msg);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
