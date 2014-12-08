<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="NetPC.service.ListService" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta  charset="utf-8">
	<title>JSP AJAX Form</title>
	<meta name="author" content="Krzysztof Witczak" />
	<meta name="description" content="AJAX login" />
	<meta name="keywords" content="AJAX, LOGIN" />
	<script src="javascript/jquery-1.11.1.js"></script>
	<script src="javascript/basic.js"></script>
	<link rel="stylesheet" href="css/basic.css" type="text/css" media="screen" />
</head>
<body>




	<p class="large">Lista kontaktów - Net P.C.</p>
	<form id="updateUsername">
		<label for="username">Login:</label>
		<br>
		<input type="text" id="llogin" name="username" />
		<br>
		<label for="password">Hasło:</label>
		<br>
		<input type="password" id="lpassword" name="password" />
		<br>
		<input type="submit" value="Zaloguj się"/>
	</form>
	
	
	
	<form id="getClients">
		<label for="clientList">Lista klientów</label>

		<select id="clientList" name="clientList"  style="width: 255px">
			<%
			// Załadowanie listy kontaktów z bazy danych - na starcie
			ListService listUpdater = new ListService();
			List<String> names = listUpdater.getNames();

			for(String name:names){%>
				<option><%=name %></option>
			<%}%>		
		</select>
		
		<div id="infos">
			<input type="text" id="TBemail" name="TBemail" style="width: 250px"/>
			<br>
			<input type="text" id="TBname" name="TBname" style="width: 250px"/>
			<br>
			<input type="text" id="TBsurname" name="TBsurname" style="width: 250px"/>
			<br>
			<input type="text" id="TBphone" name="TBphone" style="width: 250px"/>
			<br>
			<input type="text" id="TBbirth" name="TBbirth" style="width: 250px"/>
		</div>
		<div id="labels">
			<br>
			<label for="email">Email</label>
			<br>
			<label for="name">Imię</label>	
			<br>
			<label for="surname">Nazwisko</label>	
			<br>
			<label for="phone">Telefon</label>	
			<br>
			<label for="birth">Data ur</label>
		</div>
	</form>
	
	
	<div id="buttons">
			<input id="editButton" type="button" value="Edytuj kontakt"/>
			<input id="addButton" type="button" value="Dodaj kontakt"/>
			<input id="deleteButton" type="button" value="Usuń kontakt"/>
	</div>
	
	
	<p id="displayName"/>
	<form id=logoutForm>
		<input id="logoutButton" type="button" value="Wyloguj się"/>
	</form>
	
 

</body>
</html>