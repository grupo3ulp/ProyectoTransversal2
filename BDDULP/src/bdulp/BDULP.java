
package bdulp;

import bdulp.Controlador.AlumnoData;
import bdulp.Controlador.Conexion;
import bdulp.Modelo.Alumno;
import java.time.LocalDate;


public class BDULP {

    public static void main(String[] args) {
        
        LocalDate fecha = LocalDate.now();
        Alumno ejemplo = new Alumno("Alumno", "Ejemplo", 39333333, fecha, true);
       
        conectar(ejemplo);

    }
    
    public static void conectar (Alumno ejemplo){
        Conexion c = new Conexion ("jdbc:mariadb://localhost/universidad", "root", "");
        AlumnoData ad = new AlumnoData(c);
        
        // -- COMENTAR Y DESCOMENTAR PARA PROBAR FUNCIONES --
        
        // -- GUARDAR ALUMNO | (modificar datos en línea 15)
        //ad.guardarAlumno(ejemplo);
        
        // -- BORRAR ALUMNO | (pasar id por parámetro)
        //ad.borrarAlumno(5);
        
        // -- ACTUALIZAR ALUMNO 
        //ad.actualizarAlumno(ejemplo);
        
        // -- BUSCAR ALUMNO | (pasar id por parámetro)
        //ad.buscarAlumno(12);
    }

}
