/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lecturer;

import DAO.LessonDAO;
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
public class UpdateLesson extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateLesson</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateLesson at " + request.getContextPath () + "</h1>");
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
        var role =  session1.getAttribute("role");
        int a =0;
        if(role!=null){
            a=(int) role;
        }
        
        if (role == null || a!=2) {
            response.sendRedirect("Login");
            return;
        }
        
        String idstr = request.getParameter("id");
        int id = Integer.parseInt(idstr);
        LessonDAO lessonDAO = new LessonDAO();
        Lesson lesson =lessonDAO.GetLessonByID(id);
        request.setAttribute("lesson", lesson);
        request.getRequestDispatcher("UpdateLesson.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String videoUrl = request.getParameter("video_url");
        int position = Integer.parseInt(request.getParameter("position"));
        Lesson lesson = new Lesson();
        lesson.setId(id);  // Gán ID
        lesson.setTitle(title);  // Gán title
        lesson.setVideoUrl(videoUrl);  // Gán video_url
        lesson.setPosition(position);  // Gán position
        lesson.setStatus(1);  // Gán status (giả sử luôn là "Active")

        // Gọi phương thức UpdateLesson trong DAO để cập nhật bài học
        LessonDAO lessonDAO = new LessonDAO();
        boolean success = lessonDAO.UpdateLesson(lesson);
        
        response.sendRedirect("UpdateLesson?id="+id);
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
