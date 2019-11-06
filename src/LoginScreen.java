/*

2417 전은정

투두리스트 프로그램으로, 보다 재밌게 할 일을 해낼 수 있게 옷입히기 게임과 결합시킴.

login_screen.py 에서 실행하면 됩니다.
DB 와 연동하여 좀 느리니 기다려 주세요.

 */
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
//        가입 버튼 클릭 시
        btJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Join j = new Join();

                String id = joinId.getText();
                String pwd = joinPwd.getText();
                String pk = pwdCk.getText();

//                빈 곳이 있으면 채우라고 알림을 함.
                if (id.equals("") || pwd.equals("") || pk.equals(""))
                    JOptionPane.showMessageDialog(null, "빈 곳을 채워주세요");
                else
//                    빈 곳이 없으면 Join 클래스의 가입하는 함수 실행
                    j.signUp(id, pwd, pk);
            }
        });

//        로그인 버튼 클릭 시
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();

                String id = loginId.getText();
                String pwd = loginPwd.getText();

                l.signIn(id, pwd);

//                로그인 완료 후 ListScreen 호출
                new ListScreen();
            }
        });
    }

//    화면 담겨있는 메소드
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
