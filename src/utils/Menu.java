package utils;

import app.users.UserService;
import app.users.SubscriberDBService;
import app.courses.CourseService;
import entities.courses.Course;
import entities.users.User;
import entities.users.Subscriber;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Menu {

    UserService userService;
    CourseService courseService;
    SubscriberDBService subscriberDBService;

    public Menu(UserService userService, CourseService courseService, SubscriberDBService subscriberDBService) {
        this.userService = userService;
        this.courseService = courseService;
        this.subscriberDBService = subscriberDBService;
    }

    public static void showMenu(){
        System.out.println("--- SUSCRIPTORES ---");
        System.out.println("1. Crear suscriptor");
        System.out.println("2. Listar todos los suscriptores");
        System.out.println("3. Listar suscriptores activos");
        System.out.println("4. Buscar suscriptor por ID");
        System.out.println("5. Actualizar suscriptor");
        System.out.println("6. Desactivar suscriptor");
        System.out.println("7. Actualizar nivel de membresía");
        System.out.println("8. Eliminar suscriptor");
        System.out.println("--- INSTRUCTORES ---");
        System.out.println("9. Crear instructor");
        System.out.println("10. Eliminar instructor");
        System.out.println("--- CURSOS ---");
        System.out.println("11. Crear curso");
        System.out.println("12. Eliminar curso");
        System.out.println("13. Agregar suscriptor a curso");
        System.out.println("14. Eliminar suscriptor de curso");
        System.out.println("0. Salir");
    }

    private int selectOption(){
        while (true) {
            System.out.println("Ingrese una opción: ");
            try {
                return UserScans.sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                UserScans.sc.next();
            } catch (NoSuchElementException e) {
                System.out.println("Por favor ingrese una opción");
            }
        }
    }

    public void startMenu(){
        int option = 0;
        do {
            showMenu();
            option = selectOption();
            switch (option) {
                case 1:
                    Subscriber newSubscriber = UserScans.scanSubscriberBD();
                    if (subscriberDBService.addSubscriber(newSubscriber)) {
                        System.out.println("Suscriptor agregado a la base de datos correctamente.");
                    } else {
                        System.out.println("Error al agregar el suscriptor a la base de datos.");
                    }
                    break;
                case 2:
                    subscriberDBService.listAllSubscribers();
                    break;
                case 3:
                    subscriberDBService.listActiveSubscribers();
                    break;
                case 4:
                    System.out.println("Ingrese el ID del suscriptor a buscar: ");
                    try {
                        int subscriberId = UserScans.sc.nextInt();
                        Optional<Subscriber> subscriber = subscriberDBService.findSubscriberById(subscriberId);
                        if (subscriber.isPresent()) {
                            System.out.println("Suscriptor encontrado: " + subscriber.get());
                        } else {
                            System.out.println("Suscriptor no encontrado con el ID proporcionado.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el ID del suscriptor a actualizar: ");
                    try {
                        int subscriberId = UserScans.sc.nextInt();
                        Optional<Subscriber> subscriberOpt = subscriberDBService.findSubscriberById(subscriberId);
                        if (subscriberOpt.isPresent()) {
                            Subscriber subscriber = subscriberOpt.get();
                            System.out.println("Suscriptor actual: " + subscriber);
                            Subscriber updatedSubscriber = UserScans.updateSubscriber(subscriber);
                            if (subscriberDBService.updateSubscriber(updatedSubscriber)) {
                                System.out.println("Suscriptor actualizado correctamente.");
                            } else {
                                System.out.println("Error al actualizar el suscriptor.");
                            }
                        } else {
                            System.out.println("Suscriptor no encontrado.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el ID del suscriptor a desactivar: ");
                    try {
                        int subscriberId = UserScans.sc.nextInt();
                        if (subscriberDBService.deactivateSubscriber(subscriberId)) {
                            System.out.println("Suscriptor desactivado correctamente.");
                        } else {
                            System.out.println("Error al desactivar el suscriptor. Verifique el ID.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el ID del suscriptor: ");
                    try {
                        int subscriberId = UserScans.sc.nextInt();
                        System.out.println("Ingrese el nuevo nivel de membresía: ");
                        int newLevel = UserScans.sc.nextInt();
                        if (subscriberDBService.upgradeSubscriberMembership(subscriberId, newLevel)) {
                            System.out.println("Nivel de membresía actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el nivel de membresía.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 8:
                    System.out.println("Ingrese el ID del suscriptor a eliminar: ");
                    try {
                        int subscriberId = UserScans.sc.nextInt();
                        if (subscriberDBService.removeSubscriber(subscriberId)) {
                            System.out.println("Suscriptor eliminado de la base de datos correctamente.");
                        } else {
                            System.out.println("Error al eliminar el suscriptor. Verifique el ID.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 9:
                    User newUserInstructor = UserScans.scanInstructor();
                    if(this.userService.addUser(newUserInstructor)){
                        System.out.println("Instructor creado correctamente.");
                    } else {
                        System.out.println("Error al crear el instructor.");
                    }
                    break;
                case 10:
                    System.out.println("Ingrese el ID del instructor a eliminar: ");
                    try {
                        int instructorId = UserScans.sc.nextInt();
                        if (this.userService.removeUser(instructorId)) {
                            System.out.println("Instructor eliminado correctamente.");
                        } else {
                            System.out.println("Error al eliminar el instructor. Verifique el ID.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 11:
                    System.out.println("Instructores disponibles:");
                    userService.listInstructors();
                    System.out.println("Ingrese el ID del instructor que impartirá el curso: ");
                    try {
                        int instructorId = UserScans.sc.nextInt();
                        Course newCourse = UserScans.scanCourse();
                        if (this.courseService.addCourse(newCourse.getCourseName(), newCourse.getCoursePeriod(), newCourse.getCourseDescription(), instructorId)) {
                            System.out.println("Curso creado correctamente.");
                        } else {
                            System.out.println("Error al crear el curso. Verifique el ID del instructor.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 12:
                    System.out.println("Ingrese el ID del curso a eliminar: ");
                    try {
                        int courseId = UserScans.sc.nextInt();
                        if (this.courseService.removeCourse(courseId)) {
                            System.out.println("Curso eliminado correctamente.");
                        } else {
                            System.out.println("Error al eliminar el curso. Verifique el ID.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 13:
                    try {
                        courseService.listAllCourses();
                        System.out.println("Ingrese el ID del curso: ");
                        int courseId = UserScans.sc.nextInt();
                        userService.listSubscribers();
                        System.out.println("Ingrese el ID del suscriptor: ");
                        int subscriberId = UserScans.sc.nextInt();

                        if (courseService.addSubscriber(courseId, subscriberId)) {
                            System.out.println("Suscriptor agregado al curso correctamente.");
                        } else {
                            System.out.println("Error al agregar suscriptor. Verifique los IDs.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                    break;
                case 14:
                    try {
                        courseService.listAllCourses();
                        System.out.println("Ingrese el ID del curso: ");
                        int courseId = UserScans.sc.nextInt();

                        System.out.println("Ingrese el ID del suscriptor a quitar: ");
                        int subscriberId = UserScans.sc.nextInt();

                        if (courseService.removeSubscriber(courseId, subscriberId)) {
                            System.out.println("Suscriptor quitado del curso correctamente.");
                        } else {
                            System.out.println("Error al quitar suscriptor. Verifique los IDs.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID no válido. Por favor, ingrese un número.");
                    }
                break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

}
