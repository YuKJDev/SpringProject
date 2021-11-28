<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #aadd0a;
}
</style>
</head>
<body>
<form action="./addNewProduct" method="POST">
     <input type="submit" value="Add new product">
</form>
<table>
  <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Cost</th>
  </tr>
      <c:forEach var="product" items="${productList}">
   <tr>
       <td>${product.id}</td>
       <td>${product.title}</td>
       <td>${product.cost}</td>
       <td><a href="/delete/${product.id}">Delete this product</a></td>
   </tr>
      </c:forEach>
</table>
</body>
</html>
