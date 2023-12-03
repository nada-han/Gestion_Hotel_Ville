<%@ page import="entities.Ville" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    // Récupérer l'identifiant de la ville depuis la requête
    String villeId = request.getParameter("id");
    String updatedNom = request.getParameter("nom");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une Ville</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }

        form {
            margin-bottom: 20px;
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

        .btn-warning {
            background-color: #ffc107;
            border-color: #ffc107;
        }

        .btn-warning:hover {
            background-color: #d39e00;
            border-color: #d39e00;
        }
    </style>
</head>
<body>

    <!-- Formulaire de modification -->
    <h3>Modifier la ville :</h3>
    <form action="villeController" method="get" class="form-inline">
        <input type="hidden" name="id" value="<%= villeId %>" />
        <div class="form-group">
            <label for="updatedVille">Nom :</label>
            <input type="text" name="updatedVille" value="<%= updatedNom %>" class="form-control" />
        </div>
        <button name="action" value="update" class="btn btn-warning ml-2">Modifier</button>
    </form>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Afficher les informations dans la console du navigateur
        console.log("Id : <%= villeId %>");
        console.log("Nom : <%= updatedNom %>");
    </script>
</body>
</html>