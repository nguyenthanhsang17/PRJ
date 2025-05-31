<%-- 
    Document   : ListCourse
    Created on : Mar 12, 2025, 5:36:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course</title>
        <link href="img/elearning.png" rel="icon">
        <style>
            .search-box {
                max-width: 400px;
                margin: auto;
            }

            .search-box .form-control {
                border-radius: 50px;
                padding: 10px 20px;
                border: 2px solid #007bff;
            }

            .search-box .btn {
                border-radius: 50px;
                background-color: #0056b3;
                border-color: #0056b3;
                color: white;
            }

            .search-box .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>
        <!-- Courses Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center text-primary px-3">Courses</h6>
                    <h1 class="mb-5">My Courses</h1>
                </div>
                <div class="row g-4 justify-content-center">
                    <c:if test="${listc==null}">
                        <span>No courses available</span>
                    </c:if>
                    <c:forEach var="c" items="${listc}">
                        <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s" >
                            <div class="course-item bg-light">
                                <div class="position-relative overflow-hidden">
                                    <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                        <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 0 0 30px;">Read More</a>
                                        <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 0 30px 30px 0;">Join Now</a>
                                    </div>
                                </div>
                                <div class="text-center p-4 pb-0" style="height: 200px">
                                    <div class="mb-4">
                                        <h5>
                                            ${c.getTitle()}
                                        </h5>
                                    </div>
                                    <div class="mb-4">${c.getDescription()}</div>
                                </div>
                                <div class="d-flex border-top">
                                    <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i>${c.getInstructorName()}</small>
                                    <small class="flex-fill text-center py-2"><i class="fa fa-file-alt text-primary me-2"></i><a href="CourseDetail?id=${c.getId()}">Read More</a></small>
                                </div> 
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Courses End -->
        <%@ include file="Footer.jsp" %>  
    </body>
</html>
