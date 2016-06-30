<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

	<head>
		<style type="text/css">
			table.rez td {text-align: center;}
		</style>
	</head>

  <body>

  <h1>Krugovi</h1><br>
  
  <a href="selektirajNaSlici">
  <img alt="Krugovi" src="${pageContext.request.contextPath}/slika" width="500" height="500" ISMAP/><br><br>
	</a>

	<table border="1" cellspacing="0" class="rez">
			<thead><tr><th>Index</th><th>Definicija kruga</th><th>Naredbe</th></tr></thead>
			<tbody>
				<c:forEach var="z" items="${zapisi }">
					<tr><td>${z.index }</td><td>${z.definicija }</td><td><a href="obrisi?index=${z.index}">Obrisi ovaj krug</a> | <a href="selektiraj?index=${z.index}">Selektiraj ovaj krug</a></td></tr>
				</c:forEach>
			</tbody>
		</table>
		
		<br><br>	
	<form action="izvrsi" method="post">
		
		Naredba: <input type="text" name="naredba" value='<c:out value="${zapis.redak}"/>' size="30">
		<c:if test="${zapis.imaPogresku('naredba')}">
		<div class="greska"><c:out value="${zapis.dohvatiPogresku('naredba')}"/></div>
		</c:if>
		
		<input type="submit" value="Izvrsi">
		
		</form>

  </body>
</html>