import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JTextField joinId;
    private JTextField loginId;
    private JTextField joinPwd;
    private JTextField loginPwd;
    private JTextField pwdCk;
    private JButton btJoin;
    private JButton btLogin;
    private JPanel panel;

    public LoginScreen() {
        btJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Join j = new Join();

                String id = joinId.getText();
                String pwd = joinPwd.getText();
                String pk = pwdCk.getText();

                if (id.equals("") || pwd.equals("") || pk.equals(""))
                    JOptionPane.showMessageDialog(null, "빈 곳을 채워주세요");
                else
                    j.signUp(id, pwd, pk);
            }
        });

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();

                String id = loginId.getText();
                String pwd = loginPwd.getText();

                l.signIn(id, pwd);

                new ListScreen();
            }
        });
    }

    private void play() {
        JFrame frame = new JFrame("Joing & Login");

        frame.add(new LoginScreen().panel);

        frame.setPreferredSize(new Dimension(600, 300));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        LoginScreen l = new LoginScreen();
        l.play();
    }
}
