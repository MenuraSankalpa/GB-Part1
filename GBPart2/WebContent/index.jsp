<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Fund.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Fund Management</h1>

<form id="formFund" name="formFund">
 Fund Title: 
 <input id="fTitle" name="fTitle" type="text" 
 class="form-control form-control-sm">
 
 <br> Fund Description: 
 <input id="fDesc" name="fDesc" type="text" 
 class="form-control form-control-sm">
 
 <br> Product Price: 
 <input id="fPrice" name="fPrice" type="text" 
 class="form-control form-control-sm">
 
 
 <br> Funder Name:
 <input id="fuName" name="fuName" type="text" 
 class="form-control form-control-sm">
 
 
 <br> Date:
 <input id="date" name="date" type="text" 
 class="form-control form-control-sm">
 
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 
 <input type="hidden" id="hidpIdSave" 
 name="hidpIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divFundGrid">
 <%
 Fund fundObj = new Fund(); 
 out.print(fundObj.readFunds()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>