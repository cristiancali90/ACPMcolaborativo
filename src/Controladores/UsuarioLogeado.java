package Controladores;

import Clases.Usuario;

public class UsuarioLogeado {

    private static Usuario user;

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario aUser) {
        user = aUser;
    }
}
