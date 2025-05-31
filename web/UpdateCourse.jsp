<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Update Course</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/elearning.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

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
        <!-- 🔹 Update Course Section -->
        <div class="container mt-5" style="margin-bottom: 15px;">
            <div class="course-container">
                <h2 class="course-title">Update Course</h2>
                <form action="UpdateCourse" method="post">
                    <input type="hidden" name="courseId" value="${course.getId()}">
                    <div class="mb-3">
                        <label for="title" class="form-label">Course Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="${course.getTitle()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="category" class="form-label">Category</label>
                        <select class="form-control" id="category" name="category">
                            <c:forEach var="ct" items="${categorys}">
                                <option value="${ct.getId()}" <c:if test="${ct.getId() == course.getCategoryId()}">selected</c:if>>
                                    ${ct.getName()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Course Description</label>
                        <textarea class="form-control" id="description" name="description" rows="4" required>${course.getDescription()}</textarea>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-edit"></i> Update Course</button>
                        <a class="btn btn-primary" href="ListLessonCreate?id=${course.getId()}" style="display: inline-block;
                           background-color: #007bff;
                           color: white;
                           text-align: center;
                           text-decoration: none;
                           font-size: 16px;
                           transition: background-color 0.3s;" >Update Lesson</a>
                        <c:if test="${course.getStatus()==0}" > 
                            <a class="btn btn-primary" href="DeleteCourse?id=${course.getId()}&status=1" style="display: inline-block;
                               background-color: #007bff;
                               color: white;
                               text-align: center;
                               text-decoration: none;
                               font-size: 16px;
                               transition: background-color 0.3s;" >Active</a>
                        </c:if>
                        <c:if test="${course.getStatus()==1}" > 
                            <a class="btn btn-primary" href="DeleteCourse?id=${course.getId()}&status=0" style="display: inline-block;
                               background-color: #FF0000;
                               color: white;
                               text-align: center;
                               text-decoration: none;
                               font-size: 16px;
                               transition: background-color 0.3s;" >Deactive</a>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
        <%@ include file="Footer.jsp" %>
    </body>
</html>