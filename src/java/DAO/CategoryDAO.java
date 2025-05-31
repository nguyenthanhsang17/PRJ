/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Courses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {
    
    public List<Category> getCategory() {
        String sql = "select ct.*, COUNT(c.category_id)[number] from Categories ct left join Courses c on ct.id=c.category_id\n"
                + "Group by ct.id, ct.name, ct.status";
        ResultSet rs = null; // Khai báo ResultSet ngoài khối try
        List<Category> list = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                list.add(category);
            }
            return list;
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
    
    public static void main(String[] args) {
        CategoryDAO cd = new CategoryDAO();
        System.out.println(cd.getCategory().get(0).getName());
    }
}
