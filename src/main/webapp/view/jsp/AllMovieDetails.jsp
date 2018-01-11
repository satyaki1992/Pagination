<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/allMovies",
				contenttype: "json",
				success : function(result) {
					document.getElementById("totalCountRows").value=result;
					
					if(get('page') == null)
						document.getElementById("pageNum").value = 1;
					else
						document.getElementById("pageNum").value = get('page');
				},
				error : function(result) {
				}
			});
	});
	function get(name){
		   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
		      return decodeURIComponent(name[1]);
		}
</script>
</head>
<body>

	<table cellspacing="80">
		<tr>
			<TH>Id</th>
			<TH>Name</th>
			<TH>Rating</th>
		</tr>
		<c:forEach items="${movieDetList}" var="current">
			<tr>
				<td><c:out value="${current.id}" />
				<td>
				<td><c:out value="${current.name}" />
				<td>
				<td><c:out value="${current.rating}" />
				<td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<button onclick="takeContextPage('previous')">Previous</button>
			</td>
			<td><input type="text" size="2" id="pageNum">
			<button type="button" onclick="takeContextPage('goToPage')">Go To Page</button>
			<input type="hidden" id="totalCountRows">
			</td>
			<td>
				<button  onclick="takeContextPage('next')">Next</button>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		function takeContextPage(page) {
			var pageNum = document.getElementById("pageNum").value;
			var totalPages = document.getElementById("totalCountRows").value;
			var ctx = "${pageContext.request.contextPath}";
			if(page=='previous')
			{
				if(pageNum==1)
					alert('You are already on the first page');
				else if(pageNum>totalPages)
					alert('Total pagenum must be less or equal to total pages');
				else if(pageNum<0)
					alert('A valid pageNum must be entered');
				else{
					pageNum = +pageNum -1;
					document.getElementById("pageNum").value=pageNum;
					window.location = ctx + '/allMovies?page='+pageNum;
				}
			}
			
			if(page=='next')
			{
				if(get('page')==totalPages)
					alert('You are already on the last page');
				else if(get('page')>totalPages)
					alert('Total pagenum must be less or equal to total pages');
				else if(get('page')<0)
					alert('A valid pageNum must be entered');
				else{
					pageNum = +get('page') +1;
					document.getElementById("pageNum").value=pageNum;
					window.location = ctx + '/allMovies?page='+pageNum;
				}
			}
			if(page=='goToPage')
			{
				if(pageNum<0)
					alert('A valid pageNum must be entered');
				else if(pageNum>totalPages)
					alert('Total pagenum must be less or equal to total pages');
				else{
					document.getElementById("pageNum").value=pageNum;
					window.location = ctx + '/allMovies?page='+pageNum;
				}
			}
		}
	</script>
</body>
</html>