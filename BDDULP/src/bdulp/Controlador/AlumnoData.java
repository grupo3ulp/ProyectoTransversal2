package bdulp.Controlador;

import bdulp.Modelo.Alumno;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoData {

    private Connection con = null;

    public AlumnoData(Conexion conexion) {
        this.con = conexion.bucarConexion();
    }

    // -- GUARDAR ALUMNO -- Funcional
    public void guardarAlumno(Alumno a) {
        String query = "INSERT INTO alumno(nombre, apellido, dni, fecha_nacimiento, estado) VALUES(?,?,?,?,?)"; // PASO 1

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // PASO 2
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFecha_nacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); // PASO 3

            if (rs.next()) {
                a.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID");
            }
            ps.close(); //CERRAR EL STATEMENT
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // -- ACTUALIZAR ALUMNO -- Debería de estar funcional, not sure
    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE `alumno` SET `id`=?,`nombre`=?,`apellido`=?,`dni`=?,`fecha_nacimiento`=?,`estado`=? WHERE id =?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getId());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setInt(4, a.getDni());
            ps.setDate(5, Date.valueOf(a.getFecha_nacimiento()));
            ps.setBoolean(6, a.isEstado());
            ps.setInt(7, a.getId());

            ps.executeUpdate();
            ps.close(); //CERRAR EL STATEMENT
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // -- BORRAR ALUMNO -- Funcional
    public void borrarAlumno(int id) {
        String query = "DELETE from alumno WHERE id =" + id;

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); // PASO 3 -- importa acá?
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // -- BUSCAR ALUMNO --
    public Alumno buscarAlumno(int id) {
        Alumno a = null;
        String query = "SELECT `id`, `nombre`, `apellido`, `dni`, `fecha_nacimiento`, `estado` FROM `alumno` WHERE id = ?"; //1

        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //3
            while (rs.next()) { //4 Creo un objeto
                a = new Alumno(); // Lo instanciamos
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setDni(rs.getInt("dni"));
                a.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

}
