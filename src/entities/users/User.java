package entities.users;

public class User {
    private String nombre;
    private String apellidos;
    private int id;
    private String contacto;

    public User(String nombre, String apellidos, int id, String contacto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id = id;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + nombre + " " + apellidos + " (" + contacto + ")";
    }
}
