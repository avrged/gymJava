package entities.users;

public class Instructor extends User {
    private final int instructorId;

    public Instructor(String nombre, String apellidos, int id, String contacto, int instructorID) {
            super(nombre, apellidos, id, contacto);
            this.instructorId = instructorID;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {}

    @Override
    public String toString() {
        return "ID: " + getId() + " - " + getNombre() + " " + getApellidos() + " (" + getContacto() + ")";
    }
}