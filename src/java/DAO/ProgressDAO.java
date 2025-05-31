/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Progress;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ProgressDAO extends DBContext {

    public boolean mardone(Progress progress) {
        LessonDAO lessonDAO = new LessonDAO();
        int check = lessonDAO.checkdone(progress.getLessonId(), progress.getUserId());
        if (check != 0) {
            return false;
        }

        String sql = "INSERT INTO Progress (user_id, lesson_id, is_completed, completed_at) VALUES (?, ?, ?, GETDATE())";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, progress.getUserId());
            statement.setInt(2, progress.getLessonId());
            statement.setInt(3, 1);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Progress progress = new Progress();
        progress.setLessonId(1);
        progress.setUserId(4);
        progress.setIsCompleted(true);
        ProgressDAO pdao = new ProgressDAO();
        System.out.println(pdao.mardone(progress));
    }
}
