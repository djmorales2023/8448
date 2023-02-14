<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Estudiante</h1>
        <form action="EstudiantesController" method="POST">
            <label>Id</label>
            <input type="number" name="id" value="<c:out value="${estudiante.id}"/>">
            <label>Nombre</label>
            <input type="text" name="nombre" value="<c:out value="${estudiante.nombre}"/>">
            <label>Apellido</label>
            <input type="text" name="apellido" value="<c:out value="${estudiante.apellido}"/>">
            <label>Edad</label>
            <input type="number" name="edad" value="<c:out value="${estudiante.edad}"/>">
            <input type="submit" name="accion" value="Editar">
        </form>
    </body>
</html>
