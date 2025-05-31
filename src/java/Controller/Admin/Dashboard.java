/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.UsersDAO;
import Model.Users;
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
public class Dashboard extends HttpServlet {

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
            out.println("<title>Servlet Dashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Dashboard at " + request.getContextPath() + "</h1>");
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

//        HttpSession session1 = request.getSession();
//        var role =  session1.getAttribute("role");
//        int a= 0;
//        if (role != null) {
//            if (role instanceof Integer) {
//                a = (int) role;
//            } else {
//                a = 0;
//            }
//        }
//        if(a==0||a!=3){
//            response.sendRedirect("Login");
//            return;
//        }
        String name = request.getParameter("name")==null?"":request.getParameter("name");
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
        UsersDAO usersDAO = new UsersDAO();
        List<Users> list = usersDAO.GetAllUserByFullName(index, pagesize, name);
        UsersDAO usersDAO1 = new UsersDAO();
        int numberuser = usersDAO1.CountUser();
        int numberpage = numberuser / pagesize;
        if (numberuser % pagesize != 0) {
            numberpage++;
        }
        if (index <= 0) {
            index = 1;
        }
        if (index >= numberpage) {
            index = numberpage;
        }
        request.setAttribute("list", list);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("index", index);
        request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
    }
    private final int pagesize = 6;

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
