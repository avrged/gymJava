package app.users;

import entities.users.Subscriber;

import java.util.List;
import java.util.Optional;

public class SubscriberDBService {
    private final SubscriberDBRepo subscriberRepository;

    public SubscriberDBService(SubscriberDBRepo subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public boolean addSubscriber(Subscriber subscriber) {
        if (subscriber.getId() == 0) {
            int nextId = subscriberRepository.getNextId();
            subscriber.setId(nextId);
        }
        return subscriberRepository.addSubscriber(subscriber);
    }

    public boolean removeSubscriber(int id) {
        return subscriberRepository.removeSubscriber(id);
    }

    public Optional<Subscriber> findSubscriberById(int id) {
        return subscriberRepository.findById(id);
    }

    public void listAllSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findAllSubscribers();
        if (subscribers.isEmpty()) {
            System.out.println("No hay suscriptores registrados en la base de datos.");
        } else {
            System.out.println("=== SUSCRIPTORES REGISTRADOS (BASE DE DATOS) ===");
            for (Subscriber subscriber : subscribers) {
                System.out.println(subscriber);
            }
        }
    }

    public void listActiveSubscribers() {
        List<Subscriber> activeSubscribers = subscriberRepository.findActiveSubscribers();
        if (activeSubscribers.isEmpty()) {
            System.out.println("No hay suscriptores activos en la base de datos.");
        } else {
            System.out.println("=== SUSCRIPTORES ACTIVOS (BASE DE DATOS) ===");
            for (Subscriber subscriber : activeSubscribers) {
                System.out.println(subscriber);
            }
        }
    }

    public boolean updateSubscriber(Subscriber subscriber) {
        return subscriberRepository.updateSubscriber(subscriber);
    }

    public boolean deactivateSubscriber(int id) {
        Optional<Subscriber> subscriberOpt = findSubscriberById(id);
        if (subscriberOpt.isPresent()) {
            System.out.println("Desactivando suscriptor: " + subscriberOpt.get().getNombre() + " " + subscriberOpt.get().getApellidos());
            return subscriberRepository.deactivateSubscriber(id);
        } else {
            System.out.println("Suscriptor con ID " + id + " no encontrado.");
            return false;
        }
    }

    public boolean upgradeSubscriberMembership(int id, int newLevel) {
        Optional<Subscriber> subscriberOpt = findSubscriberById(id);
        if (subscriberOpt.isPresent()) {
            Subscriber subscriber = subscriberOpt.get();
            int oldLevel = subscriber.getMembershipLvl();
            subscriber.setMembershipLvl(newLevel);
            boolean updated = updateSubscriber(subscriber);
            if (updated) {
                System.out.println("Membresía actualizada de nivel " + oldLevel + " a nivel " + newLevel + " para " + subscriber.getNombre());
            }
            return updated;
        } else {
            System.out.println("Suscriptor con ID " + id + " no encontrado.");
            return false;
        }
    }

    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAllSubscribers();
    }

    public List<Subscriber> getActiveSubscribers() {
        return subscriberRepository.findActiveSubscribers();
    }
}
