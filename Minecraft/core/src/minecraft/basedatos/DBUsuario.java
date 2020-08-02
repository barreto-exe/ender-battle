/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft.basedatos;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Clase con funcionalidades CRUD para los usuarios en la base de datos.
 *
 * @author luisb
 */
public final class DBUsuario implements Serializable
{
    
    private String correo, usuario, pass, ip;
    
    private int id;
    
    /**
     * Es el número (color) del personaje seleccionado por el usuario.
     * Va desde el 0 al 5.
     */
    private int personajeSeleccionado;
    private int partida;

    /**
     * Crea una instancia de entidad en relación a la tabla m_usuarios de la Base de Datos.
     * @param correo es el correo del usuario.
     * @param usuario es el nombre de usuario.
     * @param pass  es la constraseña ENCRIPTADA del usuario.
     * en la base de datos.
     */
    public DBUsuario(String correo, String usuario, String pass)
    {
        this.correo = correo;
        this.usuario = usuario;
        this.pass = encriptarMD5(pass);
        this.personajeSeleccionado = 0;
        this.partida = -1;
    }

    public DBUsuario(String usuario, String ip)
    {
        this.usuario = usuario;
        this.ip = ip;
        this.correo = "";
        this.pass = "";
        this.personajeSeleccionado = 0;
        this.partida = -1;
    }

    public DBUsuario(String usuario, String ip, int id, int personajeSeleccionado)
    {
        this.usuario = usuario;
        this.ip = ip;
        this.id = id;
        this.personajeSeleccionado = personajeSeleccionado;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public int getPersonajeSeleccionado()
    {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(int personajeSeleccionado)
    {
        this.personajeSeleccionado = personajeSeleccionado;
    }

    public int getPartida()
    {
        return partida;
    }

    public void setPartida(int partida)
    {
        this.partida = partida;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public String getPersonajeSeleccionadoString()
    {
        switch(personajeSeleccionado)
        {
            case 0:
            {
                return "NORMAL";
            }
            case 1:
            {
                return "ROJO";
            }
            case 2:
            {
                return "VERDE";
            }
            case 3:
            {
                return "AMARILLO";
            }
            case 4:
            {
                return "MORADO";
            }
            case 5:
            {
                return "GRIS";
            }
            default:
            {
                return "";
            }
        }
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

    public boolean equals(DBUsuario obj)
    {
        return this.usuario.equals(obj.usuario);
    }
}
