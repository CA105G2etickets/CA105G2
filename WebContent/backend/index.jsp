<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>ETIckeTs後台 - 首頁</title>
</head>
<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.administratorphoto {
	border-radius: 50px;
	position: absolute;
	margin-top: 30;
	z-index:1;
}
.administratormenu {
	margin-top: 8em;
	margin-left: 20%;
}
.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: visible;
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	cursor: pointer;
}
body{
	font-family:微軟正黑體!important;
}
</style>

<body>
   <!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script>
	//佩涵需要清除datatable的預設
	$(document).ready(function() {
		localStorage.removeItem("DataTables_eventTitleListTable");
		localStorage.removeItem("DataTables_venueListTable");
		localStorage.removeItem("DataTables_advertisementListTable");
	});
	</script>
</body>
</html>