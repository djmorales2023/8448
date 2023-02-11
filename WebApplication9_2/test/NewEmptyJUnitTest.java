/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import dao.EstudianteDAO;
import model.EstudianteModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dar-j
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Test
    public void insertarTest() throws ClassNotFoundException{
        EstudianteDAO eDAO = new EstudianteDAO();
        EstudianteModel eModel = new EstudianteModel(5, "Pruebas", "Pruebas", 5);
        eDAO.Insertar(eModel);
    }
    
}
