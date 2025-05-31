<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Course Detail</title>
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
            .feedback-container {
                margin-top: 30px;
                padding: 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .feedback-title {
                font-weight: bold;
                color: #0056b3;
            }
            .feedback-item {
                padding: 15px;
                margin-bottom: 10px;
                background-color: #ffffff;
                border: 1px solid #e0e0e0;
                border-radius: 5px;
            }
            .feedback-rating {
                font-weight: bold;
                color: #ff9900;
            }
            .feedback-comment {
                margin-top: 10px;
            }
            .form-group {
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>
        <!-- 🔹 Course Detail Section -->
        <div class="container mt-5" style="margin-bottom: 15px;">
            <c:if test="${courses != null}">
                <div class="course-container">
                    <h2 class="course-title">${courses.getTitle()}</h2>
                    <p><i class="fas fa-user"></i> Lecturer: <strong>${courses.getInstructorName()}</strong></p>
                    <p><i class="fas fa-layer-group"></i> Category: <strong>${courses.getCategory()}</strong></p>
                    <p><i class="fas fa-calendar"></i> Create at: <strong>${courses.FormatDate()}</strong></p>
                    <hr>
                    <h4 class="text-primary">Course description</h4>
                    <p>${courses.getDescription()}</p>
                    <hr>
                    <div class="text-center">
                        <c:if test="${courses.getIsOwner() == 1}">
                            <a href="UpdateCourse?id=${courses.getId()}" class="btn btn-primary">Update</a>
                        </c:if>
                        <c:if test="${courses.getIsOwner() == 0}">
                            <c:if test="${checkenroll == true}">
                                <a href="#" class="btn btn-primary"><i class="fas fa-play"></i> Start Learning </a>
                                <a href="ListLesson?idcourse=${courses.getId()}" class="btn btn-outline-primary"><i class="fas fa-book"></i> View course</a>
                            </c:if>
                            <c:if test="${checkenroll == false}">
                                <a href="JoinCourse?id=${courses.getId()}" class="btn btn-primary"><i class="fas fa-play"></i> Join Now</a>
                            </c:if>
                        </c:if> 
                    </div>
                </div>
                <c:if test="${checkenroll == true}">
                    <!-- 🔹 Add New Feedback Form -->
                    <h4 class="feedback-title">Leave Your Feedback</h4>
                    <form action="SubmitFeedback" method="get">
                        <div class="form-group">
                            <label for="rating">Rating:</label>
                            <select class="form-control" id="rating" name="rating">
                                <option value="1">1 - Poor</option>
                                <option value="2">2 - Fair</option>
                                <option value="3">3 - Good</option>
                                <option value="4">4 - Very Good</option>
                                <option value="5">5 - Excellent</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="comment">Comment:</label>
                            <textarea class="form-control" id="comment" name="comment" rows="4" placeholder="Write your comment here..."></textarea>
                        </div>
                        <input type="hidden" name="courseId" value="${courses.getId()}">
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit Feedback</button>
                        </div>
                    </form>
                </c:if>
                <!-- 🔹 Feedback Section -->
                <div class="feedback-container">
                    <h4 class="feedback-title">Student Feedback</h4>
                    <c:if test="${feedbackList != null && !feedbackList.isEmpty()}">
                        <c:forEach var="feedback" items="${feedbackList}">
                            <div class="feedback-item">
                                <p class="feedback-rating">Rating: ${feedback.getRating()} / 5</p>
                                <p class="feedback-comment">${feedback.getComment()}</p>
                                <p><strong>On:</strong> ${feedback.getCreatedAt()}</p>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty feedbackList}">
                        <p>No feedback available for this course.</p>
                    </c:if>


                </div>

            </c:if>
            <c:if test="${courses == null}">
                <h2 class="course-title" style="height: 279px">${msg}</h2>
            </c:if>
        </div>
        <%@ include file="Footer.jsp" %>
    </body>
</html>
