<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String frontPath = path + "/common";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<c:set var="path" value="<%=path%>"></c:set>
<c:set var="frontPath" value="<%=frontPath%>"></c:set>

<link rel="stylesheet"
	href="${frontPath }/bootstrap-3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="${frontPath }/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="${frontPath }/bootstrap-3.3.7/js/bootstrap.min.js"></script>

