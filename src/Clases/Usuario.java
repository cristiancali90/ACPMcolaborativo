package Clases;

import Conexion.UsuarioBD;
import java.*;

public class Usuario {
    
    private int idUsuario;
    private int Rol = 2;
    private String Primer_Nombre;
    private String Segundo_Nombre;
    private String Cedula;
    private String Primer_Apellido;
    private String Segundo_Apellido;
    private String CorreoElectronico;
    private int Dependencia;
    private int Cargo;
    private String Telefono;
    private int Pregunta;
    private String Respuestas;
    private String Usuario;
    private String Contrasena;
    private boolean Estado = true;

    public Usuario() {
    }

    public Usuario(int Rol, String Primer_Nombre, String Segundo_Nombre, String Primer_Apellido, String Segundo_Apellido, String Cedula, String Telefono, String CorreoElectronico, int Dependencia, int Cargo, String Usuario, String Contrasena, boolean Estado) {

        this.Rol = Rol;
        this.Primer_Nombre = Primer_Nombre;
        this.Segundo_Nombre = Segundo_Nombre;
        this.Cedula = Cedula;
        this.Primer_Apellido = Primer_Apellido;
        this.Segundo_Apellido = Segundo_Apellido;
        this.CorreoElectronico = CorreoElectronico;
        this.Dependencia = Dependencia;
        this.Cargo = Cargo;
        this.Telefono = Telefono;
        
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
        this.Estado = Estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getRol() {
        return Rol;
    }

    public void setRol(int Rol) {
        this.Rol = Rol;
    }

    public String getPrimer_Nombre() {
        return Primer_Nombre;
    }

    public void setPrimer_Nombre(String Primer_Nombre) {
        this.Primer_Nombre = Primer_Nombre;
    }

    public String getSegundo_Nombre() {
        return Segundo_Nombre;
    }

    public void setSegundo_Nombre(String Segundo_Nombre) {
        this.Segundo_Nombre = Segundo_Nombre;
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String Primer_Apellido) {
        this.Primer_Apellido = Primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String Segundo_Apellido) {
        this.Segundo_Apellido = Segundo_Apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public int getDependencia() {
        return Dependencia;
    }

    public void setDependencia(int Dependencia) {
        this.Dependencia = Dependencia;
    }

    public int getCargo() {
        return Cargo;
    }

    public void setCargo(int Cargo) {
        this.Cargo = Cargo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getPregunta() {
        return Pregunta;
    }

    public void setPregunta(int Pregunta) {
        this.Pregunta = Pregunta;
    }

    public String getRespuestas() {
        return Respuestas;
    }

    public void setRespuestas(String Respuestas) {
        this.Respuestas = Respuestas;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contraseña) {
        this.Contrasena = Contraseña;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String GenerarUser(String Cedula) {
        String User = "";
        User = Cedula;
        return User;
    }

    public String GenerarCon(String Nombre, String Cedula, String Apellido) {
        String pass = "";
        char nom = Nombre.charAt(0);
        char ap = Apellido.charAt(0);
        pass = nom + "" + Cedula + "" + ap;
        return pass;
    }

    public Usuario crearLogin() {
        UsuarioBD ubd = new UsuarioBD();
        return ubd.login(this.Usuario, this.Contrasena);
    }
}
