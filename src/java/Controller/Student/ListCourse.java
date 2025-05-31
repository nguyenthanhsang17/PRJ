/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

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
import java.util.List;

/**
 *
 * @author Admin
 */
public class ListCourse extends HttpServlet {

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
            out.println("<title>Servlet ListCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCourse at " + request.getContextPath() + "</h1>");
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
        CategoryDAO ctd = new CategoryDAO();
        List<Category> listct = ctd.getCategory();
        request.setAttribute("listct", listct);
        request.setAttribute("numberct", listct.size());
        //=================================
        String search = request.getParameter("search");
        if (search == null || search.trim().equals("")) {
            search = "";
        }
        String indexstr = request.getParameter("index");
        if (indexstr == null || indexstr.trim().equals("")) {
            indexstr = "1";
        }
        int index = 0;
        try {
            index = Integer.parseInt(indexstr);
        } catch (Exception e) {
            index = 1;
        }

        String categorystr = request.getParameter("category");
        if (categorystr == null || categorystr.trim().equals("")) {
            categorystr = "0";
        }
        int category = 0;
        try {
            category = Integer.parseInt(categorystr);
        } catch (Exception e) {
            category = 0;
        }
        //=================================
        CoursesDAO cdao = new CoursesDAO();
        List<Courses> listc = cdao.GetAllCourse(search, category, index, sizepage);
        request.setAttribute("listc", listc);

        CoursesDAO cdao2 = new CoursesDAO();
        int number = cdao2.GetNumberCourse(search, category);
        int numberpage = number / sizepage;
        if (number % sizepage != 0) {
            numberpage++;
        }
        System.out.println(index);
        if (index <= 0) {
            index = 1;
        }
        if (index >= numberpage) {
            index = numberpage;
        }
        request.setAttribute("numberPage", numberpage);
        request.setAttribute("index", index);
        request.setAttribute("search", search);
        request.getRequestDispatcher("ListCourse.jsp").forward(request, response);
    }

    private final int sizepage = 6;

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
        processRequest(request, response);
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
