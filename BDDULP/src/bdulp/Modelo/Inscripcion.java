package bdulp.Modelo;

public class Inscripcion {
    
    private int id_materia;
    private int id_alumno;
    private float nota;

    public Inscripcion() {

    }

    public Inscripcion( int id_materia, int id_alumno, float nota) {
        this.id_materia = id_materia;
        this.id_alumno = id_alumno;
        this.nota = nota;
    }


    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
    
}
