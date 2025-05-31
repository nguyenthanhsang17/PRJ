<%-- 
    Document   : Login
    Created on : Mar 11, 2025, 3:47:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="img/elearning.png" rel="icon">
        <style>
            /* Đảm bảo rằng chỉ phần login-container được áp dụng CSS này */
            .login-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                width: 100%;
                background: linear-gradient(to right, #6a11cb, #2575fc); /* Màu nền đẹp */
            }

            /* Hộp login */
            .login-box {
                background: #fff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                width: 100%;
                max-width: 500px;
                text-align: center;
            }

            /* Tiêu đề của form */
            .login-box h2 {
                margin-bottom: 20px;
                color: #333;
                font-size: 24px;
            }

            /* Các trường input trong login */
            .login-box .textbox input {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: 2px solid #ccc;
                border-radius: 4px;
                outline: none;
                transition: border-color 0.3s ease;
            }

            .login-box .textbox input:focus {
                border-color: #2575fc;
            }

            /* Nút login */
            .login-box .btn {
                width: 100%;
                padding: 12px;
                background-color: #2575fc;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 18px;
                transition: background-color 0.3s ease;
            }

            .login-box .btn:hover {
                background-color: #6a11cb;
            }

        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>

        <div class="login-container">
            <div class="login-box">
                <h2>Register</h2>
                <form action="Register" method="POST">
                    <div class="textbox" style="margin-bottom: 20px">
                        <input type="text" placeholder="Email" name="Email" required>
                    </div>
                    <div class="textbox" style="margin-bottom: 20px">
                        <input type="text" placeholder="Fullname" name="fullname" required>
                    </div>
                    <div class="textbox" style="margin-bottom: 20px">
                        <input type="password" placeholder="password" name="password" required>
                    </div>
                    <div class="textbox" style="margin-bottom: 20px">
                        <input type="password" placeholder="confirm password" name="confirmpassword" required>
                    </div>
                    <div class="textbox" style="margin-bottom: 20px">
                        <span style="color: #1fc2d1; ">Do you have acccount ? <a style="color: blue" href="Login">Login</a></span>
                    </div>
                    <div class="textbox" style="margin-bottom: 20px">
                        <span style="color: red; ">${msg}</span>
                    </div>
                    <input type="submit" value="Register" class="btn">
                </form>
            </div>
        </div>
        <%@ include file="Footer.jsp" %>  
    </body>
</html>

