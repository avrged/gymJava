package app.users;

import config.DatabaseConfig;
import entities.users.Subscriber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDBRepo {
    private final DatabaseConfig dbConfig;

    public SubscriberDBRepo() {
        this.dbConfig = DatabaseConfig.getInstance();
    }

    public boolean addSubscriber(Subscriber subscriber) {
        String sql = "INSERT INTO subscribers (id, nombre, apellidos, contacto, membership_id, is_active, membership_lvl) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConfig.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, subscriber.getId());
            stmt.setString(2, subscriber.getNombre());
            stmt.setString(3, subscriber.getApellidos());
            stmt.setString(4, subscriber.getContacto());
            stmt.setInt(5, subscriber.getMembershipId());
            stmt.setBoolean(6, subscriber.isActive());
            stmt.setInt(7, subscriber.getMembershipLvl());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error agregando suscriptor: " + e.getMessage());
            return false;
        }
    }

    public boolean removeSubscriber(int id) {
        String sql = "DELETE FROM subscribers WHERE id = ?";

        try (PreparedStatement stmt = dbConfig.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando suscriptor: " + e.getMessage());
            return false;
        }
    }

    public Optional<Subscriber> findById(int id) {
        String sql = "SELECT * FROM subscribers WHERE id = ?";

        try (PreparedStatement stmt = dbConfig.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subscriber subscriber = new Subscriber(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("id"),
                        rs.getString("contacto"),
                        "subscription",
                        rs.getInt("membership_id")
                    );
                    subscriber.setActive(rs.getBoolean("is_active"));
                    subscriber.setMembershipLvl(rs.getInt("membership_lvl"));
                    return Optional.of(subscriber);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error buscando suscriptor por ID: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Subscriber> findAllSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        String sql = "SELECT * FROM subscribers";

        try (Statement stmt = dbConfig.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subscriber subscriber = new Subscriber(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getInt("id"),
                    rs.getString("contacto"),
                    "subscription",
                    rs.getInt("membership_id")
                );
                subscriber.setActive(rs.getBoolean("is_active"));
                subscriber.setMembershipLvl(rs.getInt("membership_lvl"));
                subscribers.add(subscriber);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo todos los suscriptores: " + e.getMessage());
        }

        return subscribers;
    }

    public List<Subscriber> findActiveSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        String sql = "SELECT * FROM subscribers WHERE is_active = true";

        try (Statement stmt = dbConfig.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subscriber subscriber = new Subscriber(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getInt("id"),
                    rs.getString("contacto"),
                    "subscription",
                    rs.getInt("membership_id")
                );
                subscriber.setActive(rs.getBoolean("is_active"));
                subscriber.setMembershipLvl(rs.getInt("membership_lvl"));
                subscribers.add(subscriber);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo suscriptores activos: " + e.getMessage());
        }

        return subscribers;
    }

    public boolean updateSubscriber(Subscriber subscriber) {
        String sql = "UPDATE subscribers SET nombre = ?, apellidos = ?, contacto = ?, membership_id = ?, is_active = ?, membership_lvl = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConfig.getConnection().prepareStatement(sql)) {
            stmt.setString(1, subscriber.getNombre());
            stmt.setString(2, subscriber.getApellidos());
            stmt.setString(3, subscriber.getContacto());
            stmt.setInt(4, subscriber.getMembershipId());
            stmt.setBoolean(5, subscriber.isActive());
            stmt.setInt(6, subscriber.getMembershipLvl());
            stmt.setInt(7, subscriber.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando suscriptor: " + e.getMessage());
            return false;
        }
    }

    public boolean deactivateSubscriber(int id) {
        String sql = "UPDATE subscribers SET is_active = false WHERE id = ?";

        try (PreparedStatement stmt = dbConfig.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error desactivando suscriptor: " + e.getMessage());
            return false;
        }
    }

    public int getNextId() {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 as next_id FROM subscribers";

        try (Statement stmt = dbConfig.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("next_id");
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo siguiente ID: " + e.getMessage());
        }

        return 1;
    }
}
