/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lecturer;

import DAO.CategoryDAO;
import DAO.CoursesDAO;
import Model.Category;
import Model.Courses;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CreateCourse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCourse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session1 = request.getSession();
        var role = session1.getAttribute("role");
        int a = 0;

        if (role != null) {
            if (role instanceof Integer) {
                a = (int) role;
            } else {
                a = 0;
            }
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categorys = categoryDAO.getCategory();
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("CreateCourse.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("account");

        Courses courses = new Courses();
        courses.setTitle(title);
        courses.setCategoryId(Integer.parseInt(category));
        courses.setDescription(description);
        courses.setInstructorId(uid);

        CoursesDAO coursesDAO = new CoursesDAO();
        int id = coursesDAO.createCourse(courses);
        response.sendRedirect("CourseDetail?id=" + id);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
