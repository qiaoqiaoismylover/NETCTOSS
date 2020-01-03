<%--
  Created by IntelliJ IDEA.
  User: jfr
  Date: 2019/11/14
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<img src="../images/logo.png" alt="logo" class="left"/>
<%--获取cookie
    方法一：
    request.getCookies();
    方法二：
    page->request->session->application
    从内置对象中获取
    语法：
        cookie.key.value
--%>
<%--<span>欢迎:${cookie.adminCode.value}</span>--%>
<span>欢迎:${adminCode }</span>
<a href="#">[退出]</a>

