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

    private TaskManager() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("드라이버 로딩 및 Connection 오류");
            e.printStackTrace();
        }

        try {
            String sql = "SELECT content FROM task";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                tasks.add(rs.getString("content"));
            }
            System.out.println(tasks);

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

    private void addTask(String content) {
        try {
            String sql = "INSERT INTO task(listnum, content) VALUES(?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setString(2, content);
            pstmt.executeUpdate();

            System.out.println("추가 완료");

        } catch (Exception e) {
            System.out.println("추가 실패");
            e.printStackTrace();
        } finally {
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

    private void updateTask(int listnum, String content, int percent) {
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
                        System.out.println("업데이트 되었습니다.");
                    } else {
                        System.out.println("리스트 번호가 맞지 않습니다.");
                    }
                } else {
                    System.out.println("리스트 번호가 없습니다.");
                }
            }
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

    private int getListnum(String content) {
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
        return lm;
    }

    private int checkTask(int listnum) {
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
        return lm;
    }

    private int findListnum(String content) {
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
        return lm;
    }

    private int deleteTask(int listnum) {
        int result = checkTask(listnum);
        int r = 0;

        try {
            if (result == 1) {
                String sql = "DELETE FROM task WHERE listnum = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, listnum);
                pstmt.executeUpdate();

                System.out.println("삭제 완료");
                r = 1;
            } else {
                System.out.println("삭제 실패");
                r = 0;
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return r;
    }

    private int completeTask(String task) {
        count ++;
        System.out.println(count + "카운트");

        int listnum = findListnum(task);
        int result = deleteTask(listnum);
        int r = 0;

        if (result == 1)
            r = 1;
        else
            r = 0;

        return r;
    }

    private int getCount() {
        return count;
    }
}