# Ejercicio inicial
Proyecto de introducción al curso 2027

## Todo-List v1.2
Pequeña aplicación para gestionar listas de tareas desde la consola.

## Funcionalidades
* Añadir una nueva tarea
* Borrar una tarea
* Listar tareas (con estado de completado y nivel de prioridad)
* Marcar una tarea como completada
* Asignar prioridad a una tarea (Alta, Media, Baja)
* Filtrar tareas según su prioridad
* Salir del programa

## Implementación
* Se usa la última versión de Java (JDK 27).
* Almacenamiento de tareas en memoria usando `ArrayList<>`.
* Modelo de datos inmutable representado con `record Tarea`.
* Gestión de la interfaz mediante consola con `System.out.println` y `Scanner`.

## Limitaciones
* No se ha implementado persistencia de datos (las tareas se pierden al reiniciar la aplicación).
* No se ha implementado autenticación de usuarios.
* No se ha implementado una interfaz gráfica (GUI).