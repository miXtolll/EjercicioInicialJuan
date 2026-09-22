import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Scanner utilizado para leer los datos introducidos por el usuario.
    private static final Scanner SCANNER = new Scanner(System.in);

    // Lista que almacena las tareas mientras el programa está en ejecución.
    private static final List<Tarea> TAREAS = new ArrayList<>();

    public static void main(String[] args) {
        // Controla cuándo debe finalizar el programa.
        boolean salir = false;

        System.out.println("=== Gestor de tareas To-Do ===");

        // Muestra el menú repetidamente hasta que el usuario elija salir.
        while (salir) {
            mostrarMenu();

            // Ejecuta una acción según la opción seleccionada.
            switch (leerNumero("Selecciona una opción: ")) {
                case 1 -> mostrarTareas();
                case 2 -> agregarTarea();
                case 3 -> completarTarea();
                case 4 -> eliminarTarea();
                case 0 -> salir = true; //ALEX; yo hubiera puesto saliendo puesto que esta opcion sale del bucle
                default -> System.out.println("Opción no válida.");
            }
        }

        System.out.println("¡Hasta pronto!");

        // Cierra el Scanner antes de finalizar el programa.
        SCANNER.close();
    }

    /**
     * Muestra en la consola todas las opciones disponibles.
     */
    private static void mostrarMenu() {
        System.out.println("""
                
                1. Mostrar tareas
                2. Agregar tarea
                3. Completar tarea
                4. Eliminar tarea
                0. Salir
                """);
    }

    /**
     * Muestra todas las tareas junto con su estado.
     * [ ] significa pendiente y [X] significa completada.
     */
    private static void mostrarTareas() {
        // Evita recorrer la lista si todavía no existen tareas.
        if (TAREAS.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        System.out.println("\n--- Tareas ---");

        // Recorre la lista y muestra cada tarea con una numeración.
        for (int i = 0; i < TAREAS.size(); i++) {
            Tarea tarea = TAREAS.get(i);
            String estado = tarea.completada() ? "[X]" : "[ ]";

            System.out.printf("%d. %s %s%n",
                    i + 1,
                    estado,
                    tarea.descripcion());
        }
    }

    /**
     * Solicita una descripción y agrega una nueva tarea pendiente.
     */
    private static void agregarTarea() {
        System.out.print("Descripción de la tarea: ");
        String descripcion = SCANNER.nextLine().trim();

        // No permite guardar tareas sin descripción.
        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }

        TAREAS.add(new Tarea(descripcion, false));
        System.out.println("Tarea agregada correctamente.");
    }

    /**
     * Permite seleccionar una tarea y marcarla como completada.
     */
    private static void completarTarea() {
        mostrarTareas();

        // No solicita un número si no existen tareas.
        if (TAREAS.isEmpty()) {
            return;
        }

        // Se resta uno porque los índices de las listas comienzan en cero.
        int indice = leerNumero("Número de la tarea completada: ") - 1;

        // Comprueba que la tarea seleccionada exista.
        if (!indiceValido(indice)) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        Tarea tarea = TAREAS.get(indice);

        // Los record son inmutables, por lo que se reemplaza la tarea
        // por otra con la misma descripción y el estado completado.
        TAREAS.set(indice, new Tarea(tarea.descripcion(), true));

        System.out.println("Tarea marcada como completada.");
    }

    /**
     * Permite seleccionar y eliminar una tarea de la lista.
     */
    private static void eliminarTarea() {
        mostrarTareas();

        // No solicita un número si no existen tareas.
        if (TAREAS.isEmpty()) {
            return;
        }

        // Convierte el número visible para el usuario en un índice de la lista.
        int indice = leerNumero(
                "Número de la tarea que deseas eliminar: ") - 1;

        // Comprueba que el índice esté dentro de los límites de la lista.
        if (!indiceValido(indice)) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        Tarea eliminada = TAREAS.remove(indice);
        System.out.println("Tarea eliminada: " + eliminada.descripcion());
    }

    /**
     * Lee un número entero y vuelve a solicitarlo si la entrada no es válida.
     *
     * @param mensaje texto que se muestra antes de leer el número
     * @return número entero introducido por el usuario
     */
    private static int leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = SCANNER.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException exception) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    /**
     * Comprueba si un índice corresponde a una tarea existente.
     *
     * @param indice índice que se desea comprobar
     * @return true si el índice es válido; false en caso contrario
     */
    private static boolean indiceValido(int indice) {
        return indice >= 0 && indice < TAREAS.size();
    }

    /**
     * Representa una tarea mediante su descripción y su estado.
     *
     * @param descripcion texto descriptivo de la tarea
     * @param completada indica si la tarea ya fue completada
     */
    private record Tarea(String descripcion, boolean completada) {
    }

}