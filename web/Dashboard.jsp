<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
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
                <h2>User List</h2>
                <form action="Dashboard" method="get">
                    <input type="text" name="name" placeholder="Search by full name" value="${param.name}" style="padding: 5px; font-size: 16px;">
                    <button type="submit" style="padding: 5px 10px; font-size: 16px;">Search</button>
                </form>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>DateJoin</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${list}">
                            <tr>
                                <td>${u.getId()}</td>
                                <td>${u.getFullName()}</td>
                                <td>${u.getEmail()}</td>
                                <c:if test="${u.getRole()==1}">
                                    <td>admin</td>
                                </c:if>
                                <c:if test="${u.getRole()==2}">
                                    <td>Instructor</td>
                                </c:if>
                                <c:if test="${u.getRole()==3}">
                                    <td>Student</td>
                                </c:if>
                                <td>${u.getCreatedAt()}</td>
                                <c:if test="${u.getStatus()==1}">
                                    <td><a href="BanUser?uid=${u.getId()}&status=0" class="link-button" onclick="alert('Link clicked!')">Ban</a></td>
                                </c:if>
                                <c:if test="${u.getStatus()==0}">
                                    <td><a href="BanUser?uid=${u.getId()}&status=1" class="link-button" onclick="alert('Link clicked!')">Un Ban</a></td>
                                </c:if>
                                    <td><a href="UserDetail?id=${u.getId()}">View detail</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination">
                    <a hrefDashboard?index=${index-1}">&lt;</a>
                       <a href="Dashboard?index=${index}" class="active">${index}/${numberpage}</a>
                    <a href="Dashboard?index=${index+1}">&gt;</a>
                </div>
            </section>
        </div>
    </body>
</html>