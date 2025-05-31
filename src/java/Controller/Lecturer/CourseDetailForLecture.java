/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lecturer;

import DAO.CoursesDAO;
import Model.Courses;
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
public class CourseDetailForLecture extends HttpServlet {
   
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
            out.println("<title>Servlet CourseDetailForLecture</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseDetailForLecture at " + request.getContextPath () + "</h1>");
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
        String idstr = request.getParameter("id");
        boolean checkenroll = false;
        HttpSession session = request.getSession();
        
        int id = 0;
        try {
            id= Integer.parseInt(idstr);
        } catch (Exception e) {
            request.setAttribute("msg", "Course does not exist !!!");
            request.getRequestDispatcher("CourseDetail.jsp").forward(request, response);
        }
        int uid = 0;
        if(session.getAttribute("account")!=null){
            uid =(int)session.getAttribute("account");
            CoursesDAO cdao3 = new CoursesDAO();
            checkenroll = cdao3.checkEnroll(uid, id);
        }
        CoursesDAO coursesDAO = new CoursesDAO();
        Courses courses = coursesDAO.GetCoursesDetailsForlecture(id);
        if(courses==null){
            request.setAttribute("msg", "Course does not exist !!!");
            request.getRequestDispatcher("CourseDetail.jsp").forward(request, response);
        }
        if(courses.getInstructorId()==uid){
            courses.setIsOwner(1);
        }else{
            courses.setIsOwner(0);
        }
        if(courses==null){
            request.setAttribute("msg", "Course does not exist !!!");
        }else{
            CoursesDAO coursesDAO2 = new CoursesDAO();
            int numberStudent = coursesDAO2.GetNumberStudentByCourseId(id);
            request.setAttribute("numberStudent", numberStudent);
            request.setAttribute("courses", courses);
            request.setAttribute("checkenroll", checkenroll);
        }
        request.getRequestDispatcher("CourseDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
