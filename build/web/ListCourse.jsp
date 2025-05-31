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
        <!-- Categories Start -->
        <div class="container-xxl py-5 category">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center text-primary px-3">Categories</h6>
                    <h1 class="mb-5">Courses Categories</h1>
                </div>
                <div class="row g-3">
                    <div class="col-lg-12 col-md-12">
                        <div class="row g-3">
                            <% int a =(int) request.getAttribute("numberct"); 
                               int a1 = 12/a;
                            %>
                            <c:forEach var="ct" items="${listct}">
                                <div class="col-lg-<%=a1%> col-md-12 wow zoomIn" data-wow-delay="0.5s">
                                    <a class=" d-block overflow-hidden" href="ListCourse?category=${ct.getId()}">
                                        <div class="bg-white text-center bottom-0 end-0 py-2 px-3" style="margin: 1px;">
                                            <h5 class="m-0">${ct.getName()}</h5>
                                            <small class="text-primary">${ct.getNumberCourse()} Courses</small>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Categories Start -->


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
                    <c:if test="${numberPage==0}">
                        <span>No courses available</span>
                    </c:if>
                    <c:if test="${numberPage!=0}">
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
                                        <!--                                        <h3 class="mb-0">$149.00</h3>-->
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
                    </c:if>
                </div>
                <c:if test="${numberPage!=0}">
                    <div class="container mt-4">
                        <nav aria-label="Pagination">
                            <ul class="pagination justify-content-center align-items-center">
                                <li class="page-item">
                                    <a class="page-link" href="ListCourse?search=${search}&index=${index-1}" aria-label="Previous">
                                        <span>&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item disabled">
                                    <span class="page-link border-0 bg-white text-dark">${index}/${numberPage}</span>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="ListCourse?search=${search}&index=${index+1}" aria-label="Next">
                                        <span>&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- Courses End -->
        <%@ include file="Footer.jsp" %>  
    </body>
</html>
