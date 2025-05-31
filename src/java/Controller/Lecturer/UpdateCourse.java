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
public class UpdateCourse extends HttpServlet {

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
            out.println("<title>Servlet UpdateCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCourse at " + request.getContextPath() + "</h1>");
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
        String idstr = request.getParameter("id");
        HttpSession session = request.getSession();
        HttpSession session1 = request.getSession();
        var role =  session1.getAttribute("role");
        int a =0;
        if(role!=null){
            a=(int) role;
        }
        
        if (role == null || a!=2) {
            response.sendRedirect("Login");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idstr);
        } catch (Exception e) {
            request.setAttribute("msg", "Course does not exist !!!");
            request.getRequestDispatcher("CourseDetail.jsp").forward(request, response);
        }
        int uid = 0;
        if (session.getAttribute("account") != null) {
            uid = (int) session.getAttribute("account");
        }
        CoursesDAO coursesDAO = new CoursesDAO();
        Courses courses = coursesDAO.GetCoursesDetailsForlecture(id);
        if (courses.getInstructorId() == uid) {
            courses.setIsOwner(1);
        } else {
            courses.setIsOwner(0);
        }
        if (courses == null) {
            request.setAttribute("msg", "Course does not exist !!!");
        } else {
            request.setAttribute("course", courses);
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categorys = categoryDAO.getCategory();
        request.setAttribute("categorys", categorys);

        request.getRequestDispatcher("UpdateCourse.jsp").forward(request, response);
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
        String idstr = request.getParameter("courseId");
        int id = Integer.parseInt(idstr);
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        
        String description = request.getParameter("description");
        
        CoursesDAO coursesDAO = new CoursesDAO();
        coursesDAO.updateCourse(id, title, description, Integer.parseInt(category));
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
