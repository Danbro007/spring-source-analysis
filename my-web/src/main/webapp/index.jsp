<%--
  Created by IntelliJ IDEA.
  User: liweimo
  Date: 2020/6/11
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="user" method="post">
  id: <input type="text" name="id"> <br>
  username: <input type="text" name="username"> <br>
  password: <input type="text" name="password"> <br>
  <input type="submit">
</form>