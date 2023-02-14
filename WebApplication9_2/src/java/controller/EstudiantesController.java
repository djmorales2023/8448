package controller;

import dao.EstudianteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EstudianteModel;

public class EstudiantesController extends HttpServlet {

    EstudianteDAO eDAO = new EstudianteDAO();

    protected void Error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!Doctype html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error: Recurso no encontrado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error: Recurso no encontrado.</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void listarEstudiantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            request.setAttribute("lista", eDAO.BuscarTodos());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstudiantesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("listar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
            String id = request.getParameter("id") == null ? "" : request.getParameter("id");
            switch (accion) {
                case "":
                    //Listar
                    listarEstudiantes(request, response);
                    break;
                case "Nuevo":
                    response.sendRedirect("nuevo.jsp");
                    break;
                case "Eliminar":
                    //Eliminamos                    
                    eDAO.Eliminar(Integer.parseInt(id));
                    listarEstudiantes(request, response);
                    break;
                case "Editar":
                    //Mostrar el formulario editar con los datos cargados

                    request.setAttribute("estudiante", eDAO.BuscarPorId(Integer.parseInt(id)));
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                default:
                    Error(request, response);
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstudiantesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EstudianteModel eModel = new EstudianteModel();
            EstudianteDAO eDAO = new EstudianteDAO();
            String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
            String id = request.getParameter("id") == null ? "" : request.getParameter("id");
            String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
            String apellido = request.getParameter("apellido") == null ? "" : request.getParameter("apellido");
            String edad = request.getParameter("edad") == null ? "" : request.getParameter("edad");
            
            eModel.setId(Integer.parseInt(id));
            eModel.setNombre(nombre);
            eModel.setApellido(apellido);
            eModel.setEdad(Integer.parseInt(edad));
            
            switch (accion) {
                case "Guardar":
                    eDAO.Insertar(eModel);
                    break;
                case "Editar":
                    eDAO.Actualziar(eModel);
                    break;
                default:
                    Error(request, response);
                    break;
            }
            
            listarEstudiantes(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstudiantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
