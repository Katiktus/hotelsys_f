<!doctype html>
<html>
<head>
    <title>JSP Test</title>
    <%!
        String message = "Hello, Webapp.";
    %>
</head>
<body>
<h2><%= message%></h2>
<%= new java.util.Date() %>
</body>
</html>