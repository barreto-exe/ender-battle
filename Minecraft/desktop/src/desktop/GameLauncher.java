package desktop;

import basedatos.DBUsuario;

/**
 *
 * @author luisb
 */
public class GameLauncher
{
    private DBUsuario usuario;

    public GameLauncher(DBUsuario usuario)
    {
        this.usuario = usuario;
    }

    public DBUsuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(DBUsuario usuario)
    {
        this.usuario = usuario;
    }
}
