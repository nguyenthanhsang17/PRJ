/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Courses;
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
public class CoursesDAO extends DBContext {

    public List<Courses> getThreeCouseNew() {
        String sql = "select top(3) * from Courses c join Categories ct on c.category_id=ct.id where c.status=1 and ct.status=1 order by created_at desc";
        ResultSet rs = null; // Khai báo ResultSet ngoài khối try
        List<Courses> list = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getInt(7)
                );
                course.setCategory(rs.getString(9));
                list.add(course);
            }
            return list;
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

    private String checkcategory(int categoryid) {
        if (categoryid != 0) {
            return " AND c.category_id = ? ";
        } else {
            return "";
        }
    }

    public List<Courses> GetAllCourse(String title, int categoryid, int page, int sizepage) {
        String sql = "SELECT c.*, ct.name AS category_name, u.full_name AS instructor_name\n"
                + "FROM Courses c\n"
                + "JOIN Categories ct ON c.category_id = ct.id\n"
                + "JOIN Users u ON c.instructor_id = u.id\n"
                + "WHERE c.title LIKE ?\n"
                + checkcategory(categoryid) // Chèn điều kiện category nếu cần
                + "ORDER BY c.id \n"
                + "OFFSET (? - 1) * ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";

        ResultSet rs = null;
        List<Courses> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;

            // Truyền giá trị cho LIKE
            st.setString(index++, "%" + title + "%");

            // Nếu có category_id, truyền giá trị
            if (categoryid != 0) {
                st.setInt(index++, categoryid);
            }

            // Truyền giá trị phân trang
            st.setInt(index++, page);
            st.setInt(index++, sizepage);
            st.setInt(index++, sizepage);

            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getInt(7)
                );
                if (course.getStatus() == 1) {
                    course.setCategory(rs.getString(8));
                    course.setInstructorName(rs.getString(9));

                    list.add(course);
                }
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

    public int GetNumberCourse(String title, int categoryid) {
        String sql = "SELECT COUNT(c.id)\n"
                + "FROM Courses c\n"
                + "JOIN Categories ct ON c.category_id = ct.id\n"
                + "JOIN Users u ON c.instructor_id = u.id\n"
                + "WHERE c.title LIKE ? \n"
                + checkcategory(categoryid);

        ResultSet rs = null;
        List<Courses> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;

            st.setString(index++, "%" + title + "%");
            if (categoryid != 0) {
                st.setInt(index++, categoryid);
            }

            rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
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

    public Courses GetCoursesDetails(int id) {
        String sql = "select c.*,ct.name,  u.full_name from Courses c left join Users u on c.instructor_id = u.id  left join Categories ct on  c.category_id = ct.id\n"
                + "where c.id = ?";

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, id);
            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getInt(7)
                );
                course.setCategory(rs.getString(8));
                course.setInstructorName(rs.getString(9));
                if (course.getStatus() == 0) {
                    return null;
                }

                return course;
            }
            return null;
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

    public int GetNumberStudentByCourseId(int id) {
        String sql = "select COUNT(e.id) from Courses c left join Enrollments e on c.id = e.course_id where e.course_id = ?";

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, id);
            rs = st.executeQuery();

            while (rs.next()) {
                int number = rs.getInt(1);
                return number;
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

    public boolean checkEnroll(int userid, int course) {
        String sql = "select COUNT(e.id) from Enrollments e where e.course_id = ? and e.user_id = ? ";

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, course);
            st.setInt(index++, userid);
            rs = st.executeQuery();

            while (rs.next()) {
                int number = rs.getInt(1);
                return number == 1 ? true : false;
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

    public List<Courses> GetMyCourse(int uid) {
        String sql = "select c.*,ct.name,  u.full_name from Courses c left join Users u on c.instructor_id = u.id  left join Categories ct on  c.category_id = ct.id \n"
                + "where c.id in (select Courses.id from Courses join Enrollments on Courses.id = Enrollments.course_id where Enrollments.user_id = ?);";

        ResultSet rs = null;
        List<Courses> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index, uid);

            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getInt(7)
                );
                course.setCategory(rs.getString(8));
                course.setInstructorName(rs.getString(9));
                list.add(course);
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

    public boolean JoinCourse(int uid, int courseid) {
        boolean c = checkEnroll(uid, courseid);
        if (c) {
            return false;
        }

        String sql = "INSERT INTO Enrollments (user_id, course_id, enrolled_at) VALUES (?, ?, GETDATE())";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, uid);
            statement.setInt(2, courseid);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int createCourse(Courses courses) {
        String sql = "INSERT INTO Courses (title, description, instructor_id, category_id, created_at, status) VALUES (?, ?, ?, ?, GETDATE(), 1)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, courses.getTitle());
            statement.setString(2, courses.getDescription());
            statement.setInt(3, courses.getInstructorId());
            statement.setInt(4, courses.getCategoryId());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Trả về giá trị CourseId
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Courses> GetCoursesByInstructor(int instructorId) {
        String sql = "SELECT c.*, ct.name AS category_name, u.full_name AS instructor_name "
                + "FROM Courses c "
                + "LEFT JOIN Users u ON c.instructor_id = u.id "
                + "LEFT JOIN Categories ct ON c.category_id = ct.id "
                + "WHERE c.instructor_id = ?";  // Giảng viên có instructor_id = 4

        ResultSet rs = null;
        List<Courses> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index, instructorId);  // Thay vào instructorId là 4 hoặc giá trị muốn tìm kiếm

            rs = st.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Course từ kết quả truy vấn
                Courses course = new Courses(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("instructor_id"),
                        rs.getInt("category_id"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("status")
                );
                course.setCategory(rs.getString("category_name"));
                course.setInstructorName(rs.getString("instructor_name"));

                // Thêm vào danh sách các khóa học
                list.add(course);
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

    public boolean updateCourse(int id, String title, String description, int categoryId) {
        String query = "UPDATE Courses SET title = ?, description = ?, category_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, categoryId);
            stmt.setInt(4, id);

            // Thực thi câu lệnh cập nhật
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            // Nếu có ít nhất 1 dòng bị ảnh hưởng, cập nhật thành công
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UnactivceCourse(int id, int status) {
        String query = "UPDATE Courses SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Courses GetCoursesDetailsForlecture(int id) {
        String sql = "select c.*,ct.name,  u.full_name from Courses c left join Users u on c.instructor_id = u.id  left join Categories ct on  c.category_id = ct.id\n"
                + "where c.id = ?";

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, id);
            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getTimestamp(6),
                        rs.getInt(7)
                );
                course.setCategory(rs.getString(8));
                course.setInstructorName(rs.getString(9));

                return course;
            }
            return null;
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

    public List<Courses> getAllCourseForAdmin() {
        String sql = "SELECT \n"
                + "    c.id,\n"
                + "    c.title,\n"
                + "    CAST(c.description AS NVARCHAR(MAX)) AS description,\n"
                + "    cat.name AS category_name,\n"
                + "    u.full_name AS instructor_name,\n"
                + "    COUNT(DISTINCT f.id) AS vote_count,\n"
                + "    COUNT(DISTINCT e.user_id) AS enrolled_students,\n"
                + "    COUNT(DISTINCT p.user_id) AS active_students\n"
                + "FROM \n"
                + "    Courses c\n"
                + "    LEFT JOIN Categories cat ON c.category_id = cat.id\n"
                + "    LEFT JOIN Users u ON c.instructor_id = u.id\n"
                + "    LEFT JOIN Feedback f ON c.id = f.course_id\n"
                + "    LEFT JOIN Enrollments e ON c.id = e.course_id\n"
                + "    LEFT JOIN Progress p ON p.lesson_id IN (\n"
                + "        SELECT id FROM Lessons WHERE course_id = c.id\n"
                + "    ) AND p.is_completed = 1\n"
                + "GROUP BY \n"
                + "    c.id, c.title, CAST(c.description AS NVARCHAR(MAX)), cat.name, u.full_name\n"
                + "ORDER BY \n"
                + "    COUNT(DISTINCT f.id) desc";

        ResultSet rs = null;
        List<Courses> courseses = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            rs = st.executeQuery();

            while (rs.next()) {
                Courses course = new Courses();
                course.setId(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setDescription(rs.getString(3));
                course.setCategory(rs.getString(4));
                course.setInstructorName(rs.getString(5));
                course.setVote_Count(rs.getInt(6));
                course.setEnrolled_students(rs.getInt(7));
                course.setAvgVote(GetVoteCourse(course.getId()));
                courseses.add(course);
            }
            return courseses;
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

    public double GetVoteCourse(int id) {
        String sql = "	SELECT \n"
                + "    AVG(CAST(rating AS DECIMAL(10,2))) AS average_rating,\n"
                + "    COUNT(*) AS total_votes\n"
                + "FROM \n"
                + "    Feedback\n"
                + "WHERE \n"
                + "    course_id = ?\n"
                + "    AND rating IS NOT NULL;";

        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int index = 1;
            st.setInt(index++, id);
            rs = st.executeQuery();

            while (rs.next()) {
                return  rs.getDouble(1);
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

    public static void main(String[] args) {
        CoursesDAO cdao = new CoursesDAO();
        System.out.println(cdao.GetVoteCourse(1));
    }
}
