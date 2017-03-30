<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>
  <head>

    <title>show</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="bootstrap2/css/bootstrap.css" rel="stylesheet" />
    <link href="bootstrap2/css/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/tables.css" type="text/css" media="screen" />
  </head>
  
  <body>
    <div class="content">
        <div class="container-fluid">
            <div id="pad-wrapper">
            	<div class="table-wrapper products-table section">
            		<table class="table table-hover">
                           <thead>
                               <tr>
                                   <th class="span4">
                                       <span class="line"></span>
                                       <span>userid</span>
                                   </th>
                                   <th class="span4">
                                       <span class="line"></span>
                                       <span>rolename</span>
                                   </th>
                                   <th class="span4">
                                      <span class="line"></span>
                                      <span>username</span>
                                   </th>
                               </tr>
                           </thead>
                           <tbody>
                           		<c:forEach var="user" items="${role.testUsers}">
	                               <tr>
	                                   <td>
	                                      ${user.userid}
	                                   </td>
	                                   <td class="description">
	                                       ${user.testRole.rolename}
	                                   </td>
	                                   <td>
	                                       ${user.username}
	                                   </td>
	                               </tr>
	                              </c:forEach>
                           </tbody>
                       </table>
            	</div>
            </div>
        </div>
    </div>
  </body>
</html>
