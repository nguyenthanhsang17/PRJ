<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Detail</title>
        
    </head>
    <body>

        <div class="container">
            <section id="user-detail">
                <h2>User Detail</h2>

                <div class="user-detail">
                    <p><strong>ID:</strong> ${user.getId()}</p>
                    <p><strong>Full Name:</strong> ${user.getFullName()}</p>
                    <p><strong>Email:</strong> ${user.getEmail()}</p>
                    <p><strong>Role:</strong> 
                        <c:choose>
                            <c:when test="${user.getRole() == 1}">Admin</c:when>
                            <c:when test="${user.getRole() == 2}">Instructor</c:when>
                            <c:when test="${user.getRole() == 3}">Student</c:when>
                        </c:choose>
                    </p>
                    <p><strong>Date Joined:</strong> ${user.getCreatedAt()}</p>
                    <p><strong>Status:</strong> 
                        <c:choose>
                            <c:when test="${user.getStatus() == 1}">Active</c:when>
                            <c:when test="${user.getStatus() == 0}">Banned</c:when>
                        </c:choose>
                    </p>
                </div>

                <a href="Dashboard" class="back-button">Back to User List</a>
            </section>
        </div>
    </body>
</html>
