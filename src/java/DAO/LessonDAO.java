/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Lesson;
import Model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LessonDAO extends DBContext {

    public List<Lesson> GetAllLesson(int course_id, int userid) {
        String sql = "select * from Lessons l where course_id = ? and l.status = 1 order by position asc;";
        ResultSet rs = null;
        List<Lesson> ListLesson = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, course_id);

            rs = st.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setCourseId(rs.getInt(2));
                lesson.setTitle(rs.getString(3));
                lesson.setVideoUrl(rs.getString(4));
                lesson.setPosition(rs.getInt(5));
                lesson.setStatus(rs.getInt(6));
                lesson.setDone(checkdone(lesson.getId(), userid));
                ListLesson.add(lesson);
            }
            return ListLesson;
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

    public int checkdone(int lesson, int userid) {
        String sql = "select * from Progress where lesson_id = ? and user_id = ? and is_completed = 1";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, lesson);
            st.setInt(index++, userid);

            rs = st.executeQuery();

            while (rs.next()) {
                int count = rs.getInt(1);
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

    public Lesson GetLessonID(int id, int userid) {
        String sql = "select * from Lessons where id = ? and status = 1; ";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setCourseId(rs.getInt(2));
                lesson.setTitle(rs.getString(3));
                lesson.setVideoUrl(rs.getString(4));
                lesson.setPosition(rs.getInt(5));
                lesson.setStatus(rs.getInt(6));
                lesson.setDone(checkdone(lesson.getId(), userid));
                return lesson;
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
        return null;
    }

    public List<Lesson> GetAllLessonByCourse(int course_id) {
        String sql = "select * from Lessons l where course_id = ? and l.status = 1 order by position asc;";
        ResultSet rs = null;
        List<Lesson> ListLesson = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, course_id);

            rs = st.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setCourseId(rs.getInt(2));
                lesson.setTitle(rs.getString(3));
                lesson.setVideoUrl(rs.getString(4));
                lesson.setPosition(rs.getInt(5));
                lesson.setStatus(rs.getInt(6));
                ListLesson.add(lesson);
            }
            return ListLesson;
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

    public boolean updateLessonStatusToZero(int lessonId) {
        String query = "UPDATE Lessons SET status = 0 WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, lessonId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int CreateLesson(Lesson lesson) {
        String query = "INSERT INTO Lessons (course_id, title, video_url, position, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Thiết lập các tham số vào câu lệnh SQL
            stmt.setInt(1, lesson.getCourseId()); // Đặt course_id
            stmt.setString(2, lesson.getTitle());  // Đặt title
            stmt.setString(3, lesson.getVideoUrl()); // Đặt video_url
            stmt.setInt(4, lesson.getPosition());   // Đặt position
            stmt.setInt(5, lesson.getStatus());     // Đặt status

            // Thực thi câu lệnh INSERT
            int rowsAffected = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về số dòng bị ảnh hưởng
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Lesson GetLessonByID(int id) {
        String sql = "select * from Lessons where id = ? and status = 1; ";
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setCourseId(rs.getInt(2));
                lesson.setTitle(rs.getString(3));
                lesson.setVideoUrl(rs.getString(4));
                lesson.setPosition(rs.getInt(5));
                lesson.setStatus(rs.getInt(6));
                return lesson;
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
        return null;
    }

    public boolean UpdateLesson(Lesson lesson) {

        String query = "UPDATE Lessons SET title = ?, video_url = ?, position = ?, status = 1 WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, lesson.getTitle());
            stmt.setString(2, lesson.getVideoUrl());
            stmt.setInt(3, lesson.getPosition());
            stmt.setInt(4, lesson.getId());

            // Thực thi câu lệnh UPDATE
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        LessonDAO lessonDAO = new LessonDAO();
        System.out.println(lessonDAO.checkdone(1, 4));
    }
}
