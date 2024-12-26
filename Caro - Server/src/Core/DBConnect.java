package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
    // Cấu hình kết nối MySQL

    private static final String URL = "jdbc:mysql://localhost:3307/carogame";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Thêm người dùng vào bảng user_game
        addUser("john_doe", "password123");

        // Thêm bạn bè vào bảng friend
        addFriend(1, "jane_doe");  // user_id 1 là ID của người dùng john_doe
    }

    public static void addUser(String username, String password) {
        String insertUserSQL = "INSERT INTO user_game (username, password) VALUES (?, ?)";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            // Set giá trị vào PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Thực thi câu lệnh
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into user_game table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveGame(String gameArr, int id) {
        String insertUserSQL = "INSERT INTO history (user_game_id, data) VALUES (?, ?)";
        System.out.println("ID" + id);
        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            // Set giá trị vào PreparedStatement
            pstmt.setInt(1, id);
            pstmt.setString(2, "VL");

            // Thực thi câu lệnh
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted history");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    // Kiểm tra xem username đã tồn tại chưa
    public static boolean isUsernameExist(String username) {
        String checkUserSQL = "SELECT id FROM user_game WHERE username = ? ";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(checkUserSQL)) {

            // Set giá trị cho PreparedStatement
            pstmt.setString(1, username);

            // Thực thi truy vấn SELECT
            try ( ResultSet rs = pstmt.executeQuery()) {
                // Nếu có kết quả trả về, nghĩa là username đã tồn tại
                if (rs.next()) {
                    return true;  // username đã tồn tại
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // username không tồn tại
    }

    public static int getIdByusername(String username) {
        System.out.println("urname: " + username);
        String checkUserSQL = "SELECT id FROM user_game WHERE username = ?";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(checkUserSQL);  ResultSet rs = pstmt.executeQuery()) {
            pstmt.setString(1, username);
            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                int id = rs.getInt("id");
//                String urname = rs.getString("username");

                // In ra dữ liệu của người dùng
                System.out.println("ID: " + id);
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;  // username không tồn tại
    }

    public static void getUsers() {
        String query = "SELECT id, username, password FROM user_game";  // Câu lệnh SQL lấy dữ liệu từ bảng user_game

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            // Lặp qua kết quả truy vấn
            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                // In ra dữ liệu của người dùng
                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUserById(int id) {
        // Câu lệnh SQL để lấy dữ liệu người dùng theo id
        String query = "SELECT id, username, password FROM user_game WHERE id = ?";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set giá trị cho tham số id trong câu lệnh SQL
            pstmt.setInt(1, id);

            // Thực thi câu lệnh và lấy kết quả
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    int userId = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");

                    // In ra thông tin người dùng
                    System.out.println("ID: " + userId);
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    return username + ";" + password;
                } else {
                    return "NULL";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    public static void addFriend(int userId, String friendUsername) {
        String insertFriendSQL = "INSERT INTO friend (user_id, friend_username) VALUES (?, ?)";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(insertFriendSQL)) {

            // Set giá trị vào PreparedStatement
            pstmt.setInt(1, userId);
            pstmt.setString(2, friendUsername);

            // Thực thi câu lệnh
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into friend table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean isTruePassword(String username, String passwd) {
        String checkUserSQL = "SELECT id FROM user_game WHERE username = ? AND password = ? ";

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  PreparedStatement pstmt = conn.prepareStatement(checkUserSQL)) {

            // Set giá trị cho PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, passwd);

            // Thực thi truy vấn SELECT
            try ( ResultSet rs = pstmt.executeQuery()) {
                // Nếu có kết quả trả về, nghĩa là username đã tồn tại
                if (rs.next()) {
                    return true;  // username đã tồn tại
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // username không tồn tại
    }
}
