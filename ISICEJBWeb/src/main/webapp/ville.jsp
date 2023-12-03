<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Villes</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	font-family: 'Arial', sans-serif;
	padding: 20px;
	background-color: #f8f9fa;
}

form {
	margin-bottom: 20px;
}

h3 {
	color: #007bff;
}

ul {
	list-style-type: none;
	padding: 0;
}

li {
	margin-bottom: 20px;
	background-color: #fff;
	border-radius: 5px;
	padding: 15px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

input[type="text"] {
	padding: 10px;
	width: 250px;
	border: 1px solid #ced4da;
	border-radius: 4px;
}

button {
	padding: 10px 15px;
	cursor: pointer;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

.btn-warning {
	background-color: #ffc107;
	border-color: #ffc107;
}

.btn-warning:hover {
	background-color: #d39e00;
	border-color: #d39e00;
}

.btn-danger {
	background-color: #dc3545;
	border-color: #dc3545;
}

.btn-danger:hover {
	background-color: #bd2130;
	border-color: #bd2130;
}
</style>
</head>
<body>

	<!-- Bootstrap button to redirect to hotel.jsp -->
	<div class="text-right">
		<a href="hotel.jsp" class="btn btn-primary mb-3">Hôtels</a>
	</div>

	<!-- Formulaire d'ajout -->
	<form action="villeController" method="get" class="form-inline">
		<div class="form-group">
			<label for="ville">Nom :</label> <input type="text" name="ville"
				class="form-control" />
		</div>
		<button name="action" value="create" class="btn btn-primary ml-2">Enregistrer</button>
	</form>

	<!-- Liste des villes --> 
	<h3>Liste des villes :</h3>
	<ul class="list-group">
		<c:forEach items="${villes}" var="v">
			<li class="list-group-item">${v.id} - ${v.nom} <!-- Formulaire de modification -->
				<form action="villeController" method="get" class="form-inline">
					<input type="hidden" name="id" value="${v.id}" />
					<div class="form-group">
						<label for="updatedVille">Nom :</label> <input type="text"
							name="updatedVille" value="${v.nom}" class="form-control" />
					</div>
					<!-- Lien pour rediriger vers updateville.jsp -->
					<button type="submit" name="action" value="update"
						class="btn btn-warning ml-2">
						<a href="updateville.jsp?id=${v.id}&nom=${v.nom}"
							style="color: white; text-decoration: none;">Modifier</a>

					</button>
					<!-- Formulaire de suppression -->
					<button type="submit" name="action" value="delete"
						class="btn btn-danger ml-2">
						<a href="villeController?action=delete&id=${v.id}"
							style="color: white; text-decoration: none;">Supprimer</a>
					</button>
				</form>

			</li>
		</c:forEach>
	</ul>


	<!-- Bootstrap JS and Popper.js -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>