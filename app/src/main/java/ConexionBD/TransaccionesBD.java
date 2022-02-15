package ConexionBD;

public class TransaccionesBD {


    public static final String NameDatabase = "AgendaPersonas";
    public static final String tablaAgendaPersonas = "personas";
    public static final String id="id";
    public static final String nombres="nombres";
    public static final String apellidos="apellidos";
    public static final String edad="edad";
    public static final String correo="correo";
    public static final String direccion="direccion";


    public static final String CreateTableAgendaPersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombres TEXT, apellidos TEXT, edad INTEGER , correo TEXT, direccion TEXT)";

    public static final String DROPTABLEAgendaPersonas ="DROP TABLE IF EXISTS personas";
}
