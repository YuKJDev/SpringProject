<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add new product</title>
    </head>
    <body>
    <form action="/addNewProduct" method="POST">
    <label>ID</label>
    <input type="hidden" id="id" name="id" value=${product.getById}>
    <label>Title</label>
    <input type="text" name="title">
    <label>Cost</label>
    <input type="text" name="cost">
    <input type="submit" value="addNewProduct">
    </form>
    </body>

</html>