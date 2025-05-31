<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List Course</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .navbar {
                background-color: #333;
                color: white;
                padding: 15px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .navbar-menu {
                list-style: none;
                display: flex;
                margin: 0;
                padding: 0;
            }

            .navbar-menu li {
                margin-left: 20px;
            }

            .navbar-menu a {
                color: white;
                text-decoration: none;
            }

            .container {
                padding: 20px;
            }

            h2 {
                margin-top: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                margin: 0 5px;
                padding: 8px 12px;
                border: 1px solid #ddd;
                text-decoration: none;
                color: #333;
            }

            .pagination a.active {
                background-color: #007bff;
                color: white;
                border: 1px solid #007bff;
            }

            .pagination a:hover {
                background-color: #ddd;
            }

            .link-button {
                display: inline-block;
                background: none;
                border: none;
                color: blue;
                text-decoration: underline;
                font-size: 16px;
                cursor: pointer;
                font-family: inherit;
            }

            .link-button:hover {
                color: darkblue;
            }
        </style>
    </head>
    <body>
        <nav class="navbar">
            <div class="navbar-brand">Admin Dashboard</div>
            <ul class="navbar-menu">
                <li><a href="Dashboard">User List</a></li>
                <li><a href="ListCourseFeedback">List Course</a></li>
                <li><a href="Logout">Logout</a></li>
                <li><a href="CreateLectureAccount">Create Lectures account</a></li>
                
            </ul>
        </nav>



        <div class="container">
            <section id="user-list">
                <h2>Course List</h2>
                
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>title</th>
                            <th>description</th>
                            <th>category name</th>
                            <th>instructor name</th>
                            <th>vote count</th>
                            <th>Avg Vote</th>
                            <th>enrolled students</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${courseses}">
                            <tr>
                                <td>${u.getId()}</td>
                                <td>${u.getTitle()}</td>
                                <td>${u.getDescription()}</td>
                                <td>${u.getCategory()}</td>
                                <td>${u.getInstructorName()}</td>
                                <td>${u.getVote_Count()}</td>
                                <td>${u.getAvgVote()}</td>
                                <td>${u.getEnrolled_students()}</td>
                                
                                
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </div>
    </body>
</html>