/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lecturer;

import DAO.LessonDAO;
import Model.Courses;
import Model.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CreateLesson extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CreateLesson</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateLesson at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        
        String courseid = request.getParameter("courseid");
        request.setAttribute("courseid", courseid);
        request.getRequestDispatcher("CreateLesson.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String title = request.getParameter("title");
        String videoUrl = request.getParameter("video_url");
        int position = Integer.parseInt(request.getParameter("position"));
        String courseidstr = request.getParameter("courseid");
        int courseid = Integer.parseInt(courseidstr);
        
        Lesson  c = new Lesson();
        c.setTitle(title);
        c.setVideoUrl(videoUrl);
        c.setPosition(position);
        c.setStatus(1);
        c.setCourseId(courseid);
        
        LessonDAO lessonDAO = new LessonDAO();
        lessonDAO.CreateLesson(c);
        
        response.sendRedirect("ListLessonCreate?id="+courseid);
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
