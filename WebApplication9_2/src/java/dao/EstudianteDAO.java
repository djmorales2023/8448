/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import model.EstudianteModel;

public class EstudianteDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Connection conn;
    boolean respuesta = false;

    public ArrayList<EstudianteModel> BuscarTodos() throws ClassNotFoundException {
        ArrayList<EstudianteModel> lista = new ArrayList<EstudianteModel>();
        String sql = "select * from estudiantes";
        try {
            conn = conexion.Conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstudianteModel estudianteTemp = new EstudianteModel();
                estudianteTemp.setId(rs.getInt("Id"));
                estudianteTemp.setNombre(rs.getString("Nombre"));
                estudianteTemp.setApellido(rs.getString("Apellido"));
                estudianteTemp.setEdad(rs.getInt("Edad"));
                lista.add(estudianteTemp);
            }
        } catch (SQLException ex) {
            System.out.println("Error al BuscarTodos()" + ex.getMessage());
        } finally {
            conexion.Cerrar(conn);
        }
        return lista;
    }

    public EstudianteModel BuscarPorId(int id) throws ClassNotFoundException {
        EstudianteModel estudianteTemp = new EstudianteModel();
        String sql = "select * from estudiantes where id = ?";
        try {
            conn = conexion.Conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                estudianteTemp.setNombre(rs.getString("Nombre"));
                estudianteTemp.setApellido(rs.getString("Apellido"));
                estudianteTemp.setEdad(rs.getInt("Edad"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al BuscarTodos()" + ex.getMessage());
        } finally {
            conexion.Cerrar(conn);
        }
        return estudianteTemp;
    }

    public boolean Insertar(EstudianteModel eModel) throws ClassNotFoundException {
        String sql = "insert into estudiantes(id,nombre,apellido,edad) values(?,?,?,?)";
        try {
            conn = conexion.Conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, eModel.getId());
            ps.setString(2, eModel.getNombre());
            ps.setString(3, eModel.getApellido());
            ps.setInt(4, eModel.getEdad());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al BuscarTodos()" + ex.getMessage());
        } finally {
            conexion.Cerrar(conn);
            return respuesta;
        }

    }

    public boolean Actualziar(EstudianteModel eModel) throws ClassNotFoundException {
        String sql = "update estudiantes set nombre = ?, apellido = ?, edad = ? where id = ?";
        try {
            conn = conexion.Conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, eModel.getNombre());
            ps.setString(2, eModel.getApellido());
            ps.setInt(3, eModel.getEdad());
            ps.setInt(4, eModel.getId());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al BuscarTodos()" + ex.getMessage());
        } finally {
            conexion.Cerrar(conn);
            return respuesta;
        }
    }

    public boolean Eliminar(int id) throws ClassNotFoundException {
        String sql = "delete from estudiantes where id = ?";
        try {
            conn = conexion.Conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al BuscarTodos()" + ex.getMessage());
        } finally {
            conexion.Cerrar(conn);
            return respuesta;
        }
    }

}
