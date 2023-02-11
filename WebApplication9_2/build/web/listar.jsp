<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tr>
                <td>Nombre</td>
                <td>Apellido</td>
                <td>Edad</td>
                <td></td>
                <td></td>
            <c:forEach var="oEstudiante" items="${lista}">
                <tr>
                <td><c:out value="${oEstudiante.nombre}"/></td>
                <td><c:out value="${oEstudiante.apellido}"/></td>
                <td><c:out value="${oEstudiante.edad}"/></td>
                <td>
                    <form action="EstudiantesController">
                        <input type="hidden" name="id" value="<c:out value="${oEstudiante.id}"/>"/>
                        <input type="submit" name="accion" value="Eliminar"/>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>