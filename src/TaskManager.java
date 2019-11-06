import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class TaskManager {

    private final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/project?serverTimezone=Asia/Seoul&useSSL=false";
    private final String USER_NAME = "root";
    private final String PASSWORD = "mirim2";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    ArrayList<String> tasks = new ArrayList<>();
    static int count = 0;

    public TaskManager() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("드라이버 로딩 및 Connection 오류");
            e.printStackTrace();
        }

//        DB 에 있는 할 일 내용을 가져와서 tasks 라는 ArrayList 에 넣음
        try {
            String sql = "SELECT content FROM task";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                tasks.add(rs.getString("content"));
            }
//            System.out.println(tasks);

        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    할 일 DB에 추가하는 함수
    public void addTask(String content) {
        try {
            String sql = "INSERT INTO task(listnum, content) VALUES(?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setString(2, content);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "추가 완료");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "추가 실패");
            e.printStackTrace();
        }

    }

//    할 일 DB 에 업데이트하는 함수
    public void updateTask(int listnum, String content, int percent) {
        int result = checkTask(listnum);

        try {
            if (result == 1) {
                String sql = "SELECT listnum FROM task WHERE listnum = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, listnum);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    int rLN = rs.getInt("listnum");
                    if (listnum == rLN) {
                        sql = "UPDATE task SET content = ?, percent = ? WHERE listnum = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, content);
                        pstmt.setInt(2, percent);
                        pstmt.setInt(3, listnum);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "업데이트 되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "리스트 번호가 맞지 않습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "리스트 번호가 없습니다.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    리스트 번호 반환하는 함수
    public int getListnum(String content) {
        int lm = 0;

        try {
            String sql = "SELECT listnum FROM task WHERE content = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                lm = rs.getInt("listnum");
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lm;
    }

//    할 일이 목록에 있는지 확인하는 함수
    public int checkTask(int listnum) {
        int lm = 0;

        try {
            String sql = "SELECT listnum FROM task WHERE listnum = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, listnum);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                int rLN = rs.getInt("listnum");
                if (rLN == listnum)
                    lm = 1;
                else
                    lm = 0;
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
            lm = 0;
        } catch (Exception e) {
            e.printStackTrace();
            lm = 0;
        }
        return lm;
    }

//    listnum 가져오는 함수
    public int findListnum(String content) {
        int lm = 0;

        try {
            String sql = "SELECT listnum FROM task WHERE content = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                lm = rs.getInt("listnum");
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lm;
    }

//    할 일 DB에서 삭제하는 함수
    public int deleteTask(int listnum) {
        int result = checkTask(listnum);
        int r = 0;

        try {
            if (result == 1) {
                String sql = "DELETE FROM task WHERE listnum = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, listnum);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "삭제 완료");
                r = 1;
            } else {
                JOptionPane.showMessageDialog(null, "삭제 실패");
                r = 0;
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

//    할 일 완료하는 함수
    public int completeTask(String task) {
        count ++;

        int listnum = findListnum(task);
        int result = deleteTask(listnum);
        int r = 0;

        if (result == 1)
            r = 1;
        else
            r = 0;

        return r;
    }

//    count 반환하는 함수
    public int getCount() {
        return count;
    }
}