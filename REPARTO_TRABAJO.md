# 🌿 Plan de Trabajo y Reparto de Tareas: Práctica 2 (Plantas vs Zombies)

**Fecha límite de entrega:** Semana del 16 de noviembre  
**Componentes del grupo:**
- Naoufal Kebli
- Pablo Palacios Rico

---

## 🎯 Objetivo General de la Práctica 2

La Práctica 2 se divide en dos grandes etapas:
1. **Parte I (Refactorización Estructural):** Reorganizar la arquitectura de la Práctica 1 aplicando principios de POO:
   - Segregación de interfaces (`GameStatus`, `GameWorld`, `GameModel`).
   - Patrón **Command** (desacoplar el `Controller`).
   - Jerarquía de herencia con **`GameObject`** y callbacks con **`GameItem`** (¡prohibido `instanceof`!).
   - Almacén único con **`GameObjectContainer`** (eliminar las 3 listas separadas).
   - Patrón **Factory** (`PlantFactory`).
2. **Parte II (Nuevas Mecánicas y Objetos):**
   - Nuevas plantas: **Wall-Nut** (Nuez) y **Cherry-Bomb** (Petacereza).
   - Nuevos zombies: **BucketHead Zombie** (Caracubo), **Sporty Zombie** (Deportista), **Explosive Zombie** (Explosivo).
   - Sistema de **Soles del cielo** y comando **`catch`**.
   - Comandos auxiliares: `listZombies`, `addZombie`, `cheatPlant`.

---

## 👥 Reparto Equitativo de Tareas (50% / 50%)

Para no pisarnos en Git, dividimos el desarrollo en **dos subsistemas complementarios**:

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
       │ Control, Comandos     │                           │ Objetos de juego,     │
       │ y Economía de Soles   │                           │ Contenedor y Factorías│
       └───────────────────────┘                           └───────────────────────┘
```

---

### 👤 Persona 1: Subsistema de Control, Comandos y Soles

#### 🔹 Fase 1: Refactorización (Estructura de Comandos)
1. **Interfaces de Control y Modelo:**
   - Crear `GameStatus` (interfaz con métodos de solo lectura para la vista: `getCycles()`, `getCoins()`, `getRemainingZombies()`, `positionToString()`, etc.).
   - Crear `GameModel` (interfaz con métodos que los comandos usan para mutar la partida: `reset()`, `update()`, `quit()`, etc.).
2. **Arquitectura del Patrón Command:**
   - Crear la clase abstracta `Command` con:
     - Atributos `name`, `shortcut`, `details`, `help`.
     - Métodos abstractos `execute(GameModel model, GameView view)` y `create(String[] words)`.
     - Método de búsqueda `matchCommand(String token)`.
     - Método estático `parse(String[] commandWords)`.
     - Lista estática `AVAILABLE_COMMANDS`.
3. **Refactorizar `Controller.java`:**
   - Reducir el método `run()` al nuevo bucle simplificado:
     ```java
     Command command = Command.parse(words);
     if (command != null) command.execute(game, view);
     else view.showError(Messages.UNKNOWN_COMMAND);
     ```
4. **Implementar los Comandos Base:**
   - `HelpCommand`
   - `ExitCommand`
   - `NoneCommand`
   - `ResetCommand` (con soporte opcional para `reset [<level> <seed>]`)
   - `ListPlantsCommand`
   - `AddPlantCommand`

#### 🔹 Fase 2: Comandos Nuevos y Sistema de Soles
1. **Nuevos Comandos:**
   - `ListZombiesCommand` (`[l]ist[Z]ombies`)
   - `AddZombieCommand` (`[a]dd[Z]ombie <zombie> <col> <row>`)
   - `CheatPlantCommand` (`[C]heat[P]lant <plant> <col> <row>`)
   - `CatchCommand` (`[C]atch <col> <row>`)
2. **Sistema de Soles y Economía:**
   - Implementar la caída aleatoria de soles del cielo según la frecuencia del nivel.
   - Contadores y visualización de `Generated suns` y `Caught suns`.
   - Lógica de atrapar el sol con `CatchCommand` y sumar sus soles al monedero.

---

### 👤 Persona 2: Subsistema de Objetos, Contenedor y Factorías

#### 🔹 Fase 1: Refactorización (Jerarquía y Almacén Único)
1. **Interfaces de Entorno e Interacción:**
   - Crear `GameWorld` (interfaz del entorno: `NUM_ROWS`, `NUM_COLS`, `isEmpty()`, `isInsideBoard()`, etc.).
   - Crear `GameItem` (interfaz de callbacks sin `instanceof`: `receiveZombieAttack(int damage)`, etc.).
2. **Clase Abstracta `GameObject`:**
   - Hereda o implementa `GameItem`.
   - Atributos protegidos: `GameWorld game`, `Position position`, `health`.
   - Métodos del ciclo de vida: `onEnter()`, `update()`, `onExit()`, `isAlive()`, etc.
   - Clases intermedias abstractas: `Plant` y `Zombie`.
3. **Migración de Personajes Existentes:**
   - Adaptar `Sunflower`, `Peashooter` y `CommonZombie` para heredar de `Plant` y `Zombie`.
4. **Creación de `GameObjectContainer`:**
   - Almacén único `List<GameObject> gameObjects = new ArrayList<>();`.
   - Métodos: `add(GameObject object)`, `update()`, `removeDead()`, `isEmpty(Position p)`, `positionToString(Position p)`.
   - **Eliminar definitivamente:** `SunflowerList`, `PeashooterList` y `ZombieList`.

#### 🔹 Fase 2: Factorías y Nuevos Personajes
1. **Factorías:**
   - `PlantFactory` (con `AVAILABLE_PLANTS`, `spawnPlant(...)`, `getAvailablePlants()`).
   - `ZombieFactory` (con `AVAILABLE_ZOMBIES`, `spawnZombie(...)`, `getAvailableZombies()`).
2. **Nuevas Plantas:**
   - **Wall-Nut (`WN`):** Nuez. Coste 50, resistencia 10, no ataca.
   - **Cherry-Bomb (`c`):** Petacereza. Coste 50, resistencia 2. Explota a los 2 ciclos infligiendo 10 puntos de daño en área 3x3.
3. **Nuevos Zombies:**
   - **BucketHead Zombie (`Bz`):** Caracubo. Resistencia 8, velocidad estándar.
   - **Sporty Zombie (`Sz`):** Deportista. Resistencia 2, avanza cada ciclo (`MOVE_EVERY_CYCLES = 1`).
   - **Explosive Zombie (`Ez`):** Explosivo. Al morir detona dañando casillas adyacentes.

---

## 🤝 Punto de Encuentro: `Game.java`

`Game` es la clase coordinadora donde convergen ambos bloques:
- Implementa las 3 interfaces: `public class Game implements GameStatus, GameWorld, GameModel`
- Contiene y delega en el `GameObjectContainer`.
- Persona 1 conecta los métodos requeridos por los comandos (`GameModel` y `GameStatus`).
- Persona 2 conecta los métodos que necesitan los objetos (`GameWorld`).

---

## 🌿 Flujo de Trabajo en Git (Para evitar conflictos)

1. **Trabajar en ramas separadas:**
   - Persona 1: `git checkout -b feature/comandos-control`
   - Persona 2: `git checkout -b feature/objetos-container`
2. **Integración de la Fase 1:**
   - Cuando ambos terminen la Fase 1, se hace merge a `main` y se pasa la primera ronda de pruebas.
3. **Integración de la Fase 2:**
   - Se crean las ramas `feature/nuevos-comandos` y `feature/nuevas-plantas-zombies`.
   - Se integran a `main` y se pasan los tests oficiales (`00` a `05`).
