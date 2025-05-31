<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Create Lesson</title>
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
            .lesson-container {
                max-width: 900px;
                margin: auto;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background-color: white;
            }
            .lesson-title {
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
        <!-- 🔹 Create Lesson Section -->
        <div class="container mt-5" style="margin-bottom: 15px;">
            <div class="lesson-container">
                <h2 class="lesson-title">Create New Lesson</h2>
                <form action="CreateLesson" method="post">
                    <input name="courseid" value="${courseid}"  hidden="">
                    <div class="mb-3">
                        <label for="title" class="form-label">Lesson Title</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="mb-3">
                        <label for="video_url" class="form-label">Video URL</label>
                        <input type="text" class="form-control" id="video_url" name="video_url" required>
                    </div>
                    <div class="mb-3">
                        <iframe width="560" height="315" 
                                src="https://www.youtube.com/embed/${lesson.getVideoUrl()}" 
                                frameborder="0" 
                                allowfullscreen>
                        </iframe>
                    </div>
                    <div class="mb-3">
                        <label for="position" class="form-label">Position</label>
                        <input type="number" class="form-control" id="position" name="position" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-plus"></i> Create Lesson</button>
                    </div>
                </form>
            </div>
        </div>
        <%@ include file="Footer.jsp" %>

        <script>
            // Lắng nghe sự thay đổi trong ô nhập liệu video_url
            document.getElementById('video_url').addEventListener('input', function () {
                var videoId = this.value.trim();  // Lấy giá trị từ ô input
                var iframe = document.querySelector('iframe'); // Tìm iframe

                // Kiểm tra nếu videoId không rỗng
                if (videoId) {
                    // Cập nhật src của iframe với video_id từ ô nhập liệu
                    iframe.src = 'https://www.youtube.com/embed/' + videoId;
                } else {
                    // Nếu ô nhập liệu trống, xóa URL của iframe
                    iframe.src = '';
                }
            });
        </script>

    </body>
</html>
