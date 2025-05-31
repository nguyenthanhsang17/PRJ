/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Feedback;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends DBContext {

    public boolean CheckExits(int course_id, int user_id) {
        String sql = "SELECT * FROM Feedback WHERE course_id = ? AND user_id = ? ORDER BY created_at ASC;";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, course_id);
            st.setInt(2, user_id);

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

    public List<Feedback> GetAllFeedback(int course_id, int user_id) {
        String sql = "SELECT * FROM Feedback WHERE course_id = ? AND user_id = ? ORDER BY created_at ASC;";
        ResultSet rs = null;
        List<Feedback> feedbackList = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, course_id);
            st.setInt(2, user_id);

            rs = st.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setCourseId(rs.getInt("course_id"));
                feedback.setUserId(rs.getInt("user_id"));
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setCreatedAt(rs.getTimestamp("created_at"));
                feedbackList.add(feedback);
            }
            return feedbackList;
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

    public List<Feedback> GetAllFeedbackByCourseid(int course_id) {
        String sql = "SELECT * FROM Feedback WHERE course_id = ? ORDER BY created_at ASC;";
        ResultSet rs = null;
        List<Feedback> feedbackList = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, course_id);
            rs = st.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setCourseId(rs.getInt("course_id"));
                feedback.setUserId(rs.getInt("user_id"));
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setCreatedAt(rs.getTimestamp("created_at"));
                feedbackList.add(feedback);
            }
            return feedbackList;
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

    public boolean insertFeedback(Feedback feedback) {
        boolean check = CheckExits(feedback.getCourseId(), feedback.getUserId());
        if(check){
            return false;
        }
        
        String sql = "INSERT INTO Feedback (course_id, user_id, rating, comment, created_at) VALUES (?, ?, ?, ?, GETDATE())";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, feedback.getCourseId());
            st.setInt(2, feedback.getUserId());
            st.setInt(3, feedback.getRating());
            st.setString(4, feedback.getComment());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();

    }

}
