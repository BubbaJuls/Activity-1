<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="/registerProcess" method="post">
    Username: <input type="text" name="username" required minlength="5" maxlength="25"><br>
    Password: <input type="password" name="password" required minlength="5" maxlength="25"><br>
    <button type="submit">Register</button>
</form>
<a href="/login">Already have an account? Login</a>
<p>${message}</p>
</body>
</html>
