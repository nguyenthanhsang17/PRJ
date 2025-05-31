<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Tạo Tài Khoản Giảng Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Nền sáng */
        }
        .navbar {
            background-color: #343a40; /* Màu nền navbar */
        }
        .navbar-brand, .navbar-menu a {
            color: white; /* Màu chữ trắng */
        }
        .course-container {
            max-width: 600px;
            margin: auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
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
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Admin Dashboard</a>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Dashboard">User List</a></li>
                <li class="nav-item"><a class="nav-link" href="ListCourseFeedback">List Course</a></li>
                <li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
                <li class="nav-item"><a class="nav-link" href="CreateLectureAccount">Create Lectures Account</a></li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="course-container">
            <h2 class="text-center">Create Instructor Account</h2>
            <form action="CreateLectureAccount" method="post">
                <div class="mb-3">
                    <label for="fullname" class="form-label">Full name</label>
                    <input type="text" class="form-control" id="fullname" name="fullname" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirm-password" name="confirm-password" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Create Lecture</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>