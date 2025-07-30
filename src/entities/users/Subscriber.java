package entities.users;

public class Subscriber extends User {
    private final int membershipId;
    private boolean isActive;
    private int membershipLvl;

    public Subscriber(String nombre, String apellidos, int id, String contacto, String subscriptionType, int membershipId) {
        super(nombre, apellidos, id, contacto);
        this.membershipId = membershipId;
        this.isActive = true;
        this.membershipLvl = 1;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public int setMembershipId(int membershipId) {
        return membershipId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getMembershipLvl() {
        return membershipLvl;
    }

    public void setMembershipLvl(int membershipLvl) {
        this.membershipLvl = membershipLvl;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " - " + getNombre() + " " + getApellidos() + " (" + getContacto() + ") - Nivel de membresía: " + membershipLvl;
    }

}
