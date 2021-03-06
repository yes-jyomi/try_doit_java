import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Join {
    private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "mirim2";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Join() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("드라이버 로딩 및 Connection 오류");
            e.printStackTrace();
        }
    }

//    회원가입하는 함수
    public void signUp(String id, String pwd, String pk) {
        int result = nullCheck(id, pwd, pk);
        try {
            if (result == 1) {
                String sql = "INSERT INTO profile(prof_num, id, pwd) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, 0);
                pstmt.setString(2, id);
                pstmt.setString(3, pwd);
                pstmt.executeUpdate();

                sql = "SELECT id FROM profile WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();

                if (rs.next())
                    JOptionPane.showMessageDialog(null, "가입 완료");
                else
                    JOptionPane.showMessageDialog(null, "가입 실패");
            } else {
                JOptionPane.showMessageDialog(null, "가입 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    이미 가입된 아이디인지 중복 확인
    private int nullCheck(String id, String pwd, String pk) {
        int chk = 0;
        try {
            String sql = "SELECT id FROM profile WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "다른 아이디를 사용해주세요.");
            } else {
                JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");

                int res = pwdCk(pwd, pk);
                if (res == 1)
                    chk = 1;
                else
                    chk = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chk;
    }

//    비밀번호와 비밀번호 확인이 같은지 확인
    private int pwdCk(String pwd, String pk) {
        int r = 0;
        if (pwd.equals(pk)) {
            r = 1;
            JOptionPane.showMessageDialog(null, "비밀번호가 맞습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.");
            r = 0;
        }
        return r;
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        Join j = new Join();
//        j.signUp("a", "c", "cd");
//    }
}
