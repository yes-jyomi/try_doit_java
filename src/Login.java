import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {

    private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/project?serverTimezone=Asia/Seoul&useSSL=false";
    private final String USER_NAME = "root";
    private final String PASSWORD = "mirim2";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Login() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("드라이버 로딩 및 Connection 오류");
            e.printStackTrace();
        }
    }

    private int checkAccount(String inId, String inPwd) {
        int result = 0;
        String id = "", pwd = "";
        try {
            String sql = "SELECT id, pwd FROM profile WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getString("id");
                pwd = rs.getString("pwd");
                if (pwd.equals(inPwd))
                    // 성공
                    result = 1;
                else
                     // 비밀번호 오류
                    result = 3;
            } else
                // 로그인 오류
                result = 2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void signIn(String id, String pwd) {
        int result = checkAccount(id, pwd);
        if (result == 1) {
            // 성공
            JOptionPane.showMessageDialog(null, id + "님, 로그인 되었습니다.");
        } else if (result == 2) {
            // 아이디 오류
            JOptionPane.showMessageDialog(null, "id를 다시 입력해주세요.");
        } else if (result == 3) {
            // 비밀번호 오류
            JOptionPane.showMessageDialog(null, "password를 다시 입력해주세요.");
        }
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("id 입력 : ");
//        String id = sc.next();
//        System.out.print("pwd 입력 : ");
//        String pwd = sc.next();
//
//        Login l = new Login();
//        l.signIn(id, pwd);
//    }
}
