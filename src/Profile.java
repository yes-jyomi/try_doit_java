import javax.swing.*;
import java.sql.*;

public class Profile {

    private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/project?serverTimezone=Asia/Seoul&useSSL=false";
    private final String USER_NAME = "root";
    private final String PASSWORD = "mirim2";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    int prof_num = 0;
    String id = "a";
    String pwd = "";
    String name = "";

    public Profile() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("드라이버 로딩 및 Connection 오류");
            e.printStackTrace();
        }

//        DB 에 있는 prof_num, pwd, name 을 받아옴. -> 세팅
        try {
            String sql = "SELECT prof_num, pwd, name FROM profile WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                prof_num = rs.getInt("prof_num");
                pwd = rs.getString("pwd");
                name = rs.getString("name");
            }

        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    prof_num 반환하는 함수
    public int returnNum() {
        return prof_num;
    }

//    ClosetScreen 에서 작성한 이름 DB 에 넣어주는 함수
    public void setName(String name) {
        try {
            String sql = "INSERT INTO profile(name) VALUES ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.executeUpdate();

            sql = "SELECT name FROM profile WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                JOptionPane.showMessageDialog(null, name + "이 추가 되었습니다.");
            else
                JOptionPane.showMessageDialog(null, "이름 추가 실패");

        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    name 반환하는 함수
    public String getChName() {
        try {
            String sql = "SELECT name FROM profile WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                name = rs.getString("name");
            else
                JOptionPane.showMessageDialog(null, "이름 없음");

        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        return name;
    }
}
