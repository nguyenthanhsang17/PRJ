/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UsersDAO extends DBContext {

    public Users getAccount(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        ResultSet rs = null; // Khai báo ResultSet ngoài khối try
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            st.setString(2, password);

            rs = st.executeQuery();

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setStatus(rs.getInt("status"));
                if (user.getStatus() == 0) {
                    return null;
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng ResultSet nếu nó không null
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean checkUserByEmail(String Email) {
        String sql = "SELECT * FROM Users WHERE email = ? ;";
        ResultSet rs = null; // Khai báo ResultSet ngoài khối try
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, Email);

            rs = st.executeQuery();

            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean RegisterAccount(Users user) {
        String sql = "INSERT INTO Users (full_name, email, password, role, created_at, status) VALUES (?, ?, ?, ?, GETDATE(), ?)";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setInt(4, 3);
            st.setInt(5, 1);
            int row = st.executeUpdate();
            if (row > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int CountUser() {
        String sql = "SELECT COUNT(*) \n" + "FROM Users";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            rs = st.executeQuery();
            List<Users> userses = new ArrayList<>();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Users> GetAllUser(int index, int numperSize) {
        String sql = "SELECT * \n"
                + "FROM Users\n"
                + "ORDER BY Users.id \n"
                + "OFFSET (? - 1) * ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int indexSQL = 1;
            st.setInt(indexSQL++, index);
            st.setInt(indexSQL++, numperSize);
            st.setInt(indexSQL++, numperSize);

            rs = st.executeQuery();
            List<Users> userses = new ArrayList<>();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setStatus(rs.getInt("status"));
                userses.add(user);
            }
            return userses;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Users> GetAllUserByFullName(int index, int numperSize, String fullName) {
        String sql = "SELECT * \n"
                + "FROM Users\n"
                + "WHERE full_name LIKE ? \n"
                + "ORDER BY Users.id \n"
                + "OFFSET (? - 1) * ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int indexSQL = 1;
            st.setString(indexSQL++, "%" + fullName + "%"); // Thêm dấu % để tìm kiếm gần đúng
            st.setInt(indexSQL++, index);
            st.setInt(indexSQL++, numperSize);
            st.setInt(indexSQL++, numperSize);

            rs = st.executeQuery();
            List<Users> userses = new ArrayList<>();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setStatus(rs.getInt("status"));
                userses.add(user);
            }
            return userses;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int Ban(int uid, int status) {
        String sql = "UPDATE Users SET status = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status);
            statement.setInt(2, uid);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int CreateLecture(Users user) {
        String sql = "INSERT INTO Users (full_name, email, password, role, created_at, status) VALUES (?, ?, ?, ?, GETDATE(), ?)";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setInt(4, 2);
            st.setInt(5, 1);
            int row = st.executeUpdate();
            if (row > 0) {
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Users getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UsersDAO ud = new UsersDAO();
        ud.Ban(4, 1);
    }

}
