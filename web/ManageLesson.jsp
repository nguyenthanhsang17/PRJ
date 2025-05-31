<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Lesson List</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <link href="img/elearning.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="../css/style.css" rel="stylesheet">
        <style>
            body {
                background-color: #ffffff; /* Nền trắng */
            }
            .course-container {
                max-width: 900px;
                margin: auto;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background-color: white;
            }
            .course-title {
                color: #0056b3;
                font-weight: bold;
            }
            .lesson-item {
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .lesson-title {
                color: #0056b3;
                font-weight: bold;
            }
            .lesson-description {
                font-size: 14px;
                color: #555;
            }
            .btn-primary {
                background-color: #0056b3;
                border: none;
                padding: 10px 20px;
                color: white;
                font-size: 14px;
                border-radius: 25px;
                text-decoration: none;
            }
            .btn-primary:hover {
                background-color: #004494;
            }
        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>
        <!-- 🔹 Lesson List Section -->
        <div class="container mt-5">
            <c:if test="${lessons != null}">
                <div class="course-container">
                    <h2 class="course-title">List Lesson</h2>
                    <hr>
                    <a style="margin-bottom: 50px" class="btn-primary" href="CreateLesson?courseid=${courseid}" >Create Lessons</a>
                    <c:forEach var="lesson" items="${lessons}">
                        <div class="lesson-item">
                            <h4 class="lesson-title">ID: ${lesson.id} - Title: ${lesson.title}</h4>
                            <p><strong>Course ID:</strong> ${lesson.courseId}</p>
                            <p><strong>Video URL:</strong> <a href="${lesson.videoUrl}" target="_blank">${lesson.videoUrl}</a></p>
                            <p><strong>Position:</strong> ${lesson.position}</p>
                            <p><strong>Status:</strong> ${lesson.status == 1 ? 'Active' : 'Inactive'}</p>
                            <a href="UpdateLesson?id=${lesson.id}" class="btn-primary">View Lesson</a>
                            <a href="DeleteLesson?id=${lesson.id}&idcourse=${lesson.courseId}" style="background-color: red; color: white" class="btn-primary">Delete Lesson</a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${lessons == null}">
                <h2 class="course-title" style="height: 279px">${msg}</h2>
            </c:if>
        </div>
        <%@ include file="Footer.jsp" %>
    </body>
</html>
