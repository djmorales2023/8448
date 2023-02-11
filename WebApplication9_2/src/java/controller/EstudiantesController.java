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
            switch (accion) {
                case "":
                    //Listar
                    listarEstudiantes(request, response);
                    break;
                case "Nuevo":
                    //Mostrar el formulario nuevo
                    break;
                case "Eliminar":
                    //Eliminamos
                    String id = request.getParameter("id") == null ? "" : request.getParameter("id");
                    eDAO.Eliminar(Integer.parseInt(id));
                    listarEstudiantes(request, response);
                    break;
                case "Editar":
                    //Mostrar el formulario editar con los datos cargados
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
        EstudianteModel eModel = new EstudianteModel();
        EstudianteDAO eDAO = new EstudianteDAO();
        eModel.setId(Integer.parseInt(request.getParameter("id")));
        eModel.setNombre(request.getParameter("nombre"));
        eModel.setApellido(request.getParameter("apellido"));
        eModel.setEdad(Integer.parseInt(request.getParameter("edad")));
        try {
            eDAO.Insertar(eModel);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstudiantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
