import javax.swing.*;

public class Basic {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleChat();
            }
        });


    }
}
