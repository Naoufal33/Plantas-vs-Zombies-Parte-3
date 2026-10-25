# 🌿 Plan de Trabajo y Reparto de Tareas: Práctica 2 (Parte I)

**Documento de referencia:** `practica2-1.pdf` (*Plantas contra zombis refactored*)  
**Fecha límite de entrega:** Semana del 16 de noviembre  
**Componentes del grupo:**
- Naoufal Kebli
- Pablo Palacios Rico

---

## 🎯 Objetivo de la Práctica 2 (Parte I)

El objetivo de esta primera parte es **exclusivamente la refactorización arquitectónica** del código de la Práctica 1 para preparar el proyecto de cara a futuras extensiones:
- **No se añaden plantas nuevas ni zombies nuevos** en esta etapa: la funcionalidad del juego debe ser idéntica a la Práctica 1, pero con un diseño orientado a objetos limpio y desacoplado.
- Aplicar el patrón **Command** para simplificar el `Controller`.
- Segregar la clase `Game` mediante **interfaces** (`GameStatus`, `GameWorld`, `GameModel`).
- Crear la jerarquía de herencia con **`GameObject`** y resolver interacciones con **callbacks en `GameItem`** (¡terminantemente prohibido usar `instanceof`!).
- Unificar el almacén de objetos en **`GameObjectContainer`** (eliminando las listas separadas).
- Extraer la creación de plantas al patrón **Factory** (`PlantFactory`).

---

## 👥 Reparto Equitativo de Tareas (50% / 50%)

Para evitar conflictos en Git y poder trabajar en paralelo, dividimos la refactorización en dos subsistemas bien delimitados:

```
                         ┌────────────────────────────────────────┐
                         │               Game.java                │
                         │ (implementa GameStatus, World y Model) │
                         └───────────────────┬────────────────────┘
                                             │
                   ┌─────────────────────────┴─────────────────────────┐
                   ▼                                                   ▼
       ┌───────────────────────┐                           ┌───────────────────────┐
       │       PERSONA 1       │                           │       PERSONA 2       │
       │   Patrón Command y    │                           │ Jerarquía GameObject, │
       │      Controlador      │                           │  Container y Factory  │
       └───────────────────────┘                           └───────────────────────┘
```

---

### 👤 Persona 1: Patrón Command y Controlador (Páginas 2 a 5)

Encargada de la interacción del usuario y la ejecución de acciones mediante comandos desacoplados.

1. **Interfaces de Control y Modelo:**
   - `GameStatus`: Métodos de solo lectura que necesita `GamePrinter` (`getCycles()`, `getCoins()`, `getRemainingZombies()`, `positionToString()`, etc.).
   - `GameModel`: Métodos que los comandos usan para alterar la partida (`reset()`, `update()`, `quit()`, etc.).
2. **Clase Abstracta `Command`:**
   - Propiedades protegidas: `name`, `shortcut`, `details`, `help`.
   - Métodos abstractos:
     - `execute(GameModel model, GameView view)`
     - `create(String[] words)`
   - Método `matchCommand(String)` para comprobar si la entrada del usuario coincide con el comando o su atajo.
   - Método estático `Command.parse(String[] commandWords)` que recorre la lista `AVAILABLE_COMMANDS`.
3. **Refactorización de `Controller.java`:**
   - Reducir el método `run()` al nuevo bucle simplificado basado en comandos:
     ```java
     Command command = Command.parse(words);
     if (command != null) {
         command.execute(game, view);
     } else {
         view.showError(Messages.UNKNOWN_COMMAND);
     }
     ```
4. **Implementación de los Comandos Concretos:**
   - `AddPlantCommand`
   - `ListPlantsCommand`
   - `ResetCommand`
   - `HelpCommand`
   - `ExitCommand`
   - `NoneCommand`

---

### 👤 Persona 2: Jerarquía GameObject, Contenedor y Factoría (Páginas 6 a 11)

Encargada del modelo de datos de los objetos, el almacén polimórfico y la gestión de ataques sin `instanceof`.

1. **Interfaces de Entorno e Interacción:**
   - `GameWorld`: Métodos que el juego expone a los objetos (`isEmpty`, `isInsideBoard`, etc.).
   - `GameItem`: Interfaz con métodos de colisión y ataque mediante callbacks (`receiveZombieAttack(int damage)`, etc.) para **evitar el uso de `instanceof`**.
2. **Jerarquía `GameObject`:**
   - Clase abstracta `GameObject implements GameItem` con atributos `game`, `position`, `health` y métodos del ciclo de vida (`onEnter()`, `update()`, `onExit()`, `isAlive()`, etc.).
   - Clases intermedias abstractas `Plant` y `Zombie`.
   - Adaptar `Sunflower`, `Peashooter` y `Zombie` común a la nueva jerarquía.
3. **Contenedor Único `GameObjectContainer`:**
   - Almacén unificado `List<GameObject> = new ArrayList<>()`.
   - Métodos para actualizar objetos, eliminar elementos muertos y comprobar casillas.
   - **Eliminar definitivamente las 3 listas antiguas:** `SunflowerList`, `PeashooterList` y `ZombieList`.
4. **Patrón Factory (`PlantFactory`):**
   - Extraer la lógica de creación de plantas a `PlantFactory` con `AVAILABLE_PLANTS`, `spawnPlant(...)` y `getAvailablePlants()`.

---

## 🤝 Punto de Encuentro: `Game.java`

`Game` es la clase central donde convergen ambos bloques:
- Implementa las 3 interfaces:
  ```java
  public class Game implements GameStatus, GameWorld, GameModel
  ```
- Coordina al `GameObjectContainer` y a `ZombiesManager`.
- Persona 1 conecta los métodos requeridos por los comandos (`GameModel` y `GameStatus`).
- Persona 2 conecta los métodos que necesitan los objetos (`GameWorld`).

---

## 🌿 Flujo de Trabajo en Git

1. **Ramas de trabajo:**
   - Persona 1: `feature/comandos-control`
   - Persona 2: `feature/objetos-container`
2. **Integración:**
   - Al terminar cada fase o funcionalidad, se hace merge a `main` y se verifica que no haya regresiones respecto a la Práctica 1.
