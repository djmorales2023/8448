<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Estudiante</h1>
        <form action="EstudiantesController" method="POST">
            <label>Id</label>
            <input type="number" name="id">
            <label>Nombre</label>
            <input type="text" name="nombre">
            <label>Apellido</label>
            <input type="text" name="apellido">
            <label>Edad</label>
            <input type="number" name="edad">
            <input type="submit" name="Guardar" value="Guardar">
        </form>
    </body>
</html>
