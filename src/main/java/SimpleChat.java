import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleChat extends JFrame implements ActionListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private final JPanel panelMain = new JPanel(new BorderLayout());

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenu help = new JMenu("Help");
    final JMenuItem connect = menu.add(new StyledEditorKit.ForegroundAction("Connect", Color.LIGHT_GRAY));
    final JMenuItem pass = menu.add(new StyledEditorKit.ForegroundAction("Password", Color.LIGHT_GRAY));
    final JMenuItem userName = menu.add(new StyledEditorKit.ForegroundAction("Name", Color.LIGHT_GRAY));
    final JMenuItem mail = help.add(new StyledEditorKit.ForegroundAction("Support@mail.com", Color.LIGHT_GRAY));

    private final JPanel panelBottomUp = new JPanel(new GridLayout(1,4));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JLabel lIPAddress = new JLabel("IPAddress");
    private final JLabel lPort = new JLabel("Port");

    private final JTextArea chat = new JTextArea();

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnSend = new JButton("Send");
    private final JTextField tfMessage = new JTextField();

    private String name;
    private String password;

    private final JList<String> userList = new JList<String>();

    public SimpleChat(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        chat.setEditable(false);
        userList.setBackground(Color.LIGHT_GRAY);
        chat.setBackground(Color.LIGHT_GRAY);

        add(menuBar, BorderLayout.NORTH);
        menuBar.add(menu);
        menuBar.add(help);

        panelBottomUp.add(lIPAddress);
        panelBottomUp.add(tfIPAddress);
        panelBottomUp.add(lPort);
        panelBottomUp.add(tfPort);

        JScrollPane scrollChat = new JScrollPane(chat);
        JScrollPane scrollUser = new JScrollPane(userList);
        scrollUser.setPreferredSize(new Dimension(100, 0));
        String[] users = {"user1", "user2", "user3", "user4", "user5", "user6",
                "user_with_an_exceptionally_long_nickname"};
        userList.setListData(users);
        btnSend.addActionListener(this);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelMain, BorderLayout.CENTER);
        panelMain.add(scrollChat, BorderLayout.CENTER);
        panelMain.add(scrollUser, BorderLayout.EAST);
        panelMain.add(panelBottom, BorderLayout.SOUTH);
        panelMain.add(panelBottomUp,BorderLayout.NORTH);

        tfMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chat.append(e.getActionCommand() + "\n");
                tfMessage.setText("");
            }
        });

        userName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(
                        SimpleChat.this,"Введите имя");
                name = result;
            }
        });

        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(pass != null) {
                    if (!pass.equals(password)) {
                        JOptionPane.showInputDialog(SimpleChat.this,
                                new String[] {"Неверно введен пароль!",
                                        "Повторите пароль :"},
                                "Авторизация",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });

        pass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(
                        SimpleChat.this,"Введите пароль");
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnSend) {
//            throw new RuntimeException("HI");
            chat.append(tfMessage.getText() + "\n");
            tfMessage.setText("");
            String message = tfMessage.getText();
        }
    }
}
