/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.basedatos;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Clase con funcionalidades CRUD para los usuarios en la base de datos.
 *
 * @author luisb
 */
public final class DBUsuario implements Serializable
{
    public static String BASE_DATOS = "192.168.2.104";
    
    /**
     * Indica el tipo de operacion contra la base de datos.
     */
    public enum Operacion
    {
        REGISTRAR,
        INICIAR_SESION
    }
    
    /**
     * Indica el resultado arrojado por el servidor luego de la operación
     * en la base de datos.
     */
    public enum ResultadoOperacion
    {
        CORREO_NO_DISPONIBLE,
        USUARIO_NO_DISPONIBLE,
        USUARIO_REGISTRADO,
        ERROR,
        
        CREDENCIAL_INVALIDA,
        INICIAR_JUEGO
    }
    
    
    private String correo, usuario, pass;
    private Operacion operacion;

    /**
     * Crea una instancia de entidad en relación a la tabla m_usuarios de la Base de Datos.
     * @param correo es el correo del usuario.
     * @param usuario es el nombre de usuario.
     * @param pass  es la constraseña ENCRIPTADA del usuario.
     * @param operacion  es el tipo de operación que se pretende hacer con el usuario
     * en la base de datos.
     */
    public DBUsuario(String correo, String usuario, String pass, Operacion operacion)
    {
        this.correo = correo;
        this.usuario = usuario;
        this.pass = encriptarMD5(pass);
        this.operacion = operacion;
    }

    
    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = encriptarMD5(pass);
    }

    public Operacion getOperacion()
    {
        return operacion;
    }

    public void setOperacion(Operacion operacion)
    {
        this.operacion = operacion;
    }
    
    
    

    /**
     * Consulta disponibilidad de correo.
     *
     * @param correo el email que se va a consultar.
     * @return -1 si el correo no existe en la base de datos. Si existe, retorna
     * el id del usuario que lo está usando.
     */
    public static int idCorreo(String correo)
    {
        //Construir consulta de existencia 
        String consulta
                = "SELECT id FROM m_usuarios WHERE "
                + "correo  = ?";
        DBOperacion operacion = new DBOperacion(consulta);
        operacion.pasarParametro(correo);

        DBMatriz resultados = operacion.consultar();

        //Retornar el id si existe
        if (resultados.leer())
        {
            return (int) resultados.getValor("id");
        }

        //Retornar -1 si el correo está disponible
        return -1;
    }

    /**
     * Consulta disponibilidad de usuario.
     *
     * @param usuario el usuario que se va a consultar.
     * @return -1 si el usuario no existe en la base de datos. Si existe,
     * retorna el id del usuario.
     */
    public static int idUsuario(String usuario)
    {
        //Construir consulta de existencia 
        String consulta
                = "SELECT id FROM m_usuarios WHERE "
                + "usuario  = ?";
        DBOperacion operacion = new DBOperacion(consulta);
        operacion.pasarParametro(usuario);

        DBMatriz resultados = operacion.consultar();

        //Retornar el id si existe
        if (resultados.leer())
        {
            return (int) resultados.getValor("id");
        }

        //Retornar -1 si el usuario está disponible
        return -1;
    }

    /**
     * Registra un usuario en la BD.
     *
     * @param usuario instancia del usuario a registrar.
     * @return <code>true</code> si logró hacer el registro con éxito.
     */
    public static boolean registrarUsuario(DBUsuario usuario)
    {
        String query
                = "INSERT INTO `m_usuarios` (usuario, correo, password)"
                + "VALUES (?,?,?)";

        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(usuario.getUsuario());
        operacion.pasarParametro(usuario.getCorreo());
        operacion.pasarParametro(usuario.getPass());

        //Retornar la validación del registro afectado.
        return operacion.ejecutar() == 1;
    }

    /**
     * Consulta existencia de un usuario que coincida con la misma clave.
     * @param usuario es la instancia de usuario a consultar.
     * @return el id del usuario en la BD. Retorna -1 si no existe.
     */
    public static int consultarUsuario(DBUsuario usuario)
    {
        //Construir consulta de existencia 
        String consulta
                = "SELECT id FROM m_usuarios WHERE "
                + "usuario  = ? AND password = ?";
        DBOperacion operacion = new DBOperacion(consulta);
        operacion.pasarParametro(usuario.getUsuario());
        operacion.pasarParametro(usuario.getPass());

        DBMatriz resultados = operacion.consultar();

        //Retornar el id si existe
        if (resultados.leer())
        {
            return (int) resultados.getValor("id");
        }

        //Retornar -1 si el usuario está disponible
        return -1;
    }

    /**
     * Crea un hash a partir de un string con el algoritmo de encriptamiento
     * MD5.
     *
     * @param s string a encriptar.
     * @return string encriptado.
     */
    public static String encriptarMD5(String s)
    {
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
        }
        return null;
    }
}
