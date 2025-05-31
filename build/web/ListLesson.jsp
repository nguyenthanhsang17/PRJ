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
                    <h1 class="mb-5">Courses list</h1>
                </div>
                <div class="container mt-4" style="margin-bottom: 20px">
                    <form action="ListCourse">
                        <div class="search-box">
                            <div class="input-group">
                                <input type="text" class="form-control" name="search" value="${search}" placeholder="Search title..." aria-label="Search">
                                <button class="btn btn-primary" type="submit">
                                    <i class="bi bi-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row g-4 justify-content-center">
                    <c:if test="${numberlesson==0}">
                        <span>No Lession available</span>
                    </c:if>
                    <c:if test="${numberlesson!=0}">
                        <c:forEach var="c" items="${lessons}">
                            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s" >
                                <div class="course-item bg-light">
                                    <div class="position-relative overflow-hidden">
                                        <img class="img-fluid" src="https://img.youtube.com/vi/${c.getVideoUrl()}/maxresdefault.jpg"  alt="">
                                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                            <a href="LessonDetail?id=${c.getId()}" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 30px 30px 30px;">Watch</a>

                                        </div>
                                    </div>
                                    <div class="text-center p-4 pb-0" >
                                        <div class="mb-4">
                                            <h5>
                                                ${c.getTitle()} <c:if test="${c.getDone()!=0}">(<i class="bi bi-check"></i>Done)</c:if>
                                            </h5>
                                        </div>
                                        <div class="mb-4"></div>
                                    </div>
                                    <div class="d-flex border-top">
                                        
                                    </div> 
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Courses End -->
        <%@ include file="Footer.jsp" %>  
    </body>
</html>
