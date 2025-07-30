package utils;
import entities.courses.Course;
import entities.users.Instructor;
import entities.users.Subscriber;
import entities.users.User;
import app.users.UserService;

import java.util.Scanner;

public class UserScans {

    public static final Scanner sc = new Scanner(System.in);

    private static User scanUser(){
        System.out.println("Ingrese el nombre: \t");
        String nombre = sc.next();
        System.out.println("Ingrese los apellidos: \t");
        String apellidos = sc.next();
        System.out.println("Ingrese un contacto: \t");
        String contacto = sc.next();
        return new User(nombre, apellidos, 0, contacto);
    }

    public static Subscriber scanSubscriber(){
        User user = scanUser();
        System.out.println("Ingrese el tipo de suscripción: \t");
        String subscriptionType = sc.next();
        return new Subscriber(user.getNombre(), user.getApellidos(), 0, user.getContacto(), subscriptionType, 0);
    }

    public static Subscriber scanSubscriberBD(){
        System.out.println("=== CREAR SUSCRIPTOR (BASE DE DATOS) ===");
        System.out.println("Ingrese el nombre: ");
        String nombre = sc.next();
        System.out.println("Ingrese los apellidos: ");
        String apellidos = sc.next();
        System.out.println("Ingrese el contacto: ");
        String contacto = sc.next();
        System.out.println("Ingrese el nivel de membresía (1-5): ");
        int membershipLvl = sc.nextInt();

        int membershipId = membershipLvl;

        Subscriber subscriber = new Subscriber(nombre, apellidos, 0, contacto, "subscription", membershipId);
        subscriber.setMembershipLvl(membershipLvl);
        return subscriber;
    }

    public static Subscriber updateSubscriber(Subscriber subscriber){
        System.out.println("=== ACTUALIZAR SUSCRIPTOR ===");
        System.out.println("Datos actuales: " + subscriber);

        System.out.println("Nuevo nombre (actual: " + subscriber.getNombre() + ") - Enter para mantener: ");
        sc.nextLine(); // Limpiar buffer
        String nombre = sc.nextLine();
        if (!nombre.trim().isEmpty()) {
            subscriber.setNombre(nombre);
        }

        System.out.println("Nuevos apellidos (actual: " + subscriber.getApellidos() + ") - Enter para mantener: ");
        String apellidos = sc.nextLine();
        if (!apellidos.trim().isEmpty()) {
            subscriber.setApellidos(apellidos);
        }

        System.out.println("Nuevo contacto (actual: " + subscriber.getContacto() + ") - Enter para mantener: ");
        String contacto = sc.nextLine();
        if (!contacto.trim().isEmpty()) {
            subscriber.setContacto(contacto);
        }

        System.out.println("Nuevo nivel de membresía (actual: " + subscriber.getMembershipLvl() + ") - Enter para mantener: ");
        String levelStr = sc.nextLine();
        if (!levelStr.trim().isEmpty()) {
            try {
                int newLevel = Integer.parseInt(levelStr);
                subscriber.setMembershipLvl(newLevel);
            } catch (NumberFormatException e) {
                System.out.println("Nivel no válido, se mantiene el actual.");
            }
        }

        return subscriber;
    }

    public static Course scanCourse() {
        System.out.println("Ingrese el nombre del curso: ");
        String courseName = sc.next();
        System.out.println("Ingrese el período del curso: ");
        String coursePeriod = sc.next();
        System.out.println("Ingrese la descripción del curso: ");
        String courseDescription = sc.next();

        return new Course(0, coursePeriod, courseName, courseDescription, null);
    }

    public static Instructor scanInstructor(){
        User user = scanUser();
        return new Instructor(user.getNombre(), user.getApellidos(), 0, user.getContacto(), 0);
    }

}
