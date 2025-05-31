
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Lesson detail</title>
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
            .btn-primary {
                background-color: #0056b3;
                border: none;
            }
            .btn-primary:hover {
                background-color: #004494;
            }
        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>
        <!-- 🔹 Course Detail Section -->
        <div class="container mt-5" style="margin-bottom: 15px;">
            <c:if test="${lesson!=null}">
                <div class="course-container">
                    <h2 class="course-title">${lesson.getTitle()}</h2>
                    <hr>
                    <h4 class="text-primary">Lesson video:</h4>
                    <iframe width="560" height="315" 
                            src="https://www.youtube.com/embed/${lesson.getVideoUrl()}" 
                            frameborder="0" 
                            allowfullscreen>
                    </iframe>
                    <hr>
                    <form action="Markdone">
                        <input hidden="" value="${lesson.getId()}" name="id">
                        <button style="background-color: #007bff; /* Màu nền xanh */
                                color: white; /* Màu chữ trắng */
                                padding: 12px 24px; /* Khoảng cách giữa chữ và viền */
                                border-radius: 30px; /* Bo tròn góc */
                                border: none; /* Không viền */
                                font-size: 16px; /* Kích thước chữ */
                                cursor: pointer; /* Con trỏ chuột khi hover */
                                transition: all 0.3s ease; /* Hiệu ứng chuyển động */
                                box-shadow: 0 4px 8px rgba(0, 123, 255, 0.4); /* Tạo bóng nhẹ */">Mark done</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${courses==null}">
                <h2 class="course-title" style="height: 279px">${msg}</h2>
            </c:if>
        </div>
        <%@ include file="Footer.jsp" %>
    </body>
</html>
