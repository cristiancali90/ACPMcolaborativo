package Conexion;

import Clases.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class UsuarioBD extends Conexion {

    public static Usuario USER = null;

    public UsuarioBD() {
    }

    void CrearUsuario() {

    }

    public Usuario login(String u, String p) {
        String sql = "select * from tbUsuario WHERE tbUsuario.Usuario='" + u + "' AND tbUsuario.Contrasena= '" + p + "'";
        Usuario us = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                us = new Usuario();
                us.setIdUsuario(rs.getInt("idUsuario"));
                us.setRol(rs.getInt("idRoles"));
                us.setPrimer_Nombre(rs.getString("Primer_Nombre"));
                us.setSegundo_Nombre(rs.getString("Segundo_Nombre"));
                us.setCedula(rs.getString("Cedula"));
                us.setTelefono(rs.getString("Telefono"));
                us.setPrimer_Apellido(rs.getString("Primer_Apellido"));
                us.setSegundo_Apellido(rs.getString("Segundo_Apellido"));
                us.setCorreoElectronico(rs.getString("Correo"));
                us.setDependencia(rs.getInt("Dependencia"));
                us.setCargo(rs.getInt("Cargo"));
                us.setPregunta(rs.getInt("Pregunta"));
                us.setRespuestas(rs.getString("Respuesta"));
                us.setUsuario(rs.getString("Usuario"));
                us.setContrasena(rs.getString("Contrasena"));
                us.setEstado(rs.getBoolean("Estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return us;
    }

    public void crearUsuario(Usuario u) {
        try {
            String sql1 = "Insert into tbusuario"
                    + "(idRoles, Cargo, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Cedula, Telefono, Correo, Usuario, Contrasena,Dependencia, Estado)"
                    + "VALUES ('" + u.getRol() + "', '" + u.getCargo() + "', '" + u.getPrimer_Nombre() + "', '" + u.getSegundo_Nombre() + "', '" + u.getPrimer_Apellido() + "', '" + u.getSegundo_Apellido() + "', '" + u.getCedula() + "', '" + u.getTelefono() + "', '" + u.getCorreoElectronico() + "','" + u.getUsuario() + "', '" + u.getContrasena() + "'," +"'" + u.getDependencia() + "','" + u.isEstado() + "')";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarUsuario(Usuario u, String id) {
        try {
            String sql2 = "UPDATE tbUsuario "
                    + "SET Primer_Nombre = '" + u.getPrimer_Nombre() + "', "
                    + "Segundo_Nombre = '" + u.getSegundo_Nombre() + "', "
                    + "Primer_Apellido = '" + u.getPrimer_Apellido() + "', "
                    + "Segundo_Apellido = '" + u.getSegundo_Apellido() + "', "
                    + "Cedula = '" + u.getCedula() + "', "
                    + "Telefono = '" + u.getTelefono() + "', "
                    + "Correo = '" + u.getCorreoElectronico() + "', "
                    + "Cargo = '" + u.getCargo() + "', "
                    + "Dependencia = '" + u.getDependencia() + "'"
                    + "where idusuario='" + id + "'";
            Statement stmt = con.createStatement();
            int result;
            result = stmt.executeUpdate(sql2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Usuario mostrar(String id) throws SQLException {
        try {
            String sq2 = "select * from tbUsuario where idusuario='" + id + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sq2);
            Usuario a = new Usuario();
            while (rs.next()) {
                a.setRol(rs.getInt("idRoles"));
                a.setPrimer_Nombre(rs.getString("Primer_Nombre"));
                a.setSegundo_Nombre(rs.getString("Segundo_Nombre"));
                a.setCedula(rs.getString("Cedula"));
                a.setPrimer_Apellido(rs.getString("Primer_Apellido"));
                a.setSegundo_Apellido(rs.getString("Segundo_Apellido"));
                a.setTelefono(rs.getString("Telefono"));
                a.setCorreoElectronico(rs.getString("Correo"));
                a.setDependencia(rs.getInt("Dependencia"));
                a.setCargo(rs.getInt("Cargo"));
                a.setPregunta(rs.getInt("Pregunta"));
                a.setRespuestas(rs.getString("Respuesta"));
                a.setUsuario(rs.getString("Usuario"));
                a.setContrasena(rs.getString("Contrasena"));
                a.setEstado(rs.getBoolean("Estado"));
            }
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    /*public void editarContrasena(Usuario u, String pass) {
        try {

            String modificar = "UPDATE tbUsuario "
                    + "SET Contrasena = '" + pass + "' "
                    + "WHERE Usuario = '" + u.getUsuario() + "'";

            Statement stmt = con.createStatement();
            int result;
            result = stmt.executeUpdate(modificar);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/
    
    
   //metodo para validar id y correo
    
    public Usuario encuentraMail(String correo) throws SQLException {
        try {
            String sq2 = "select * from tbUsuario where correo='"+correo+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sq2);
            Usuario a = new Usuario();
            while (rs.next()) {
                a.setRol(rs.getInt("idRoles"));
                a.setPrimer_Nombre(rs.getString("Primer_Nombre"));
                a.setSegundo_Nombre(rs.getString("Segundo_Nombre"));
                a.setCedula(rs.getString("Cedula"));
                a.setPrimer_Apellido(rs.getString("Primer_Apellido"));
                a.setSegundo_Apellido(rs.getString("Segundo_Apellido"));
                a.setTelefono(rs.getString("Telefono"));
                a.setCorreoElectronico(rs.getString("Correo"));
                a.setDependencia(rs.getInt("Dependencia"));
                a.setCargo(rs.getInt("Cargo"));
                a.setPregunta(rs.getInt("Pregunta"));
                a.setRespuestas(rs.getString("Respuesta"));
                a.setUsuario(rs.getString("Usuario"));
                a.setContrasena(rs.getString("Contrasena"));
                a.setEstado(rs.getBoolean("Estado"));
                
            }
            //cerrar conexion
            st.close();
            return a;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    

}
