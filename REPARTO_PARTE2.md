# 🌿 Plan de Trabajo y Reparto de Tareas: Práctica 2 (Parte II)

**Documento de referencia:** `practica2-2.pdf` (*Plantas contra zombis extended*)  
**Fecha límite de entrega:** Semana del 16 de noviembre  
**Componentes del grupo:**
- Naoufal Kebli
- Pablo Palacios Rico

---

## 🎯 Objetivo de la Práctica 2 (Parte II)

En esta segunda parte se extiende la funcionalidad del juego aprovechando la arquitectura desacoplada de la Parte I:
1. **Nuevos objetos de juego:** Plantas (`WallNut`, `CherryBomb`), Zombies (`BucketHeadZombie`, `SportyZombie`, `ExplosiveZombie`) y soles (`Sun`).
2. **Nuevas factorías:** Creación de `ZombieFactory` y ampliación de `PlantFactory`.
3. **Sistema de acciones y explosiones encadenadas:** Interfaz `GameAction` y clase `ExplosionAction` gestionadas mediante una cola `Deque` en `Game` con cascada de muertes.
4. **Mecánica interactiva de soles:** Generación periódica mediante `SunsManager`, `Sunflower` spawneando soles en casillas aleatorias, y soporte para celdas compartidas (`S[01] *[02]`).
5. **Nuevos comandos:** `ResetCommand` ampliado (`[<level> <seed>]`), `ListZombiesCommand`, `AddPlantCheatCommand`, `AddZombieCommand` y `CatchCommand`.

---

## 👥 Reparto Equitativo de Tareas (50% / 50%)

```
                               ┌────────────────────────────────────────┐
                               │               Game.java                │
                               │        (Cola GameAction y Soles)       │
                               └───────────────────┬────────────────────┘
                                                   │
                         ┌─────────────────────────┴─────────────────────────┐
                         ▼                                                   ▼
             ┌───────────────────────┐                           ┌───────────────────────┐
             │       PERSONA 1       │                           │       PERSONA 2       │
             │   Comandos, Control   │                           │ Nuevos GameObjects,   │
             │ y Gestión de Soles    │                           │ Factorías, Acciones y │
             │     (SunsManager)     │                           │   Explosiones (Área)  │
             └───────────────────────┘                           └───────────────────────┘
```

---

### 👤 Persona 1: Comandos, Control y Gestión de Soles (`SunsManager`)

Encargada del flujo de entrada del usuario, comandos nuevos/cheat, ciclo de captura y el temporizador general de soles.

1. **Comandos (Nuevos y Modificados):**
   - **`ResetCommand` (extensión):** Aceptar 0 argumentos o 2 argumentos (`[<level> <seed>]`) para reiniciar nivel y semilla en caliente.
   - **`ListZombiesCommand` (`[l]ist[Z]ombies` / `lz`):** Muestra los zombies disponibles consultando a `ZombieFactory.getAvailableZombies()`.
   - **`AddPlantCheatCommand` (`[C]heat[P]lant` / `cp`):** Añade planta sin consumir suncoins y actualiza el juego (`model.update()`).
   - **`AddZombieCommand` (`[a]dd[Z]ombie` / `az`):** Añade un zombie en una casilla libre del tablero y actualiza el juego.
   - **`CatchCommand` (`[C]atch` / `c`):** Intenta recoger los soles de la casilla `<col> <row>`. **No avanza turno**. Falla si no hay soles o si ya se capturaron soles en el ciclo actual.
   - Registrar los 5 comandos en `Command.AVAILABLE_COMMANDS` respetando el orden oficial de la ayuda:
     1. `add`
     2. `addZombie`
     3. `cheatPlant`
     4. `catch`
     5. `list`
     6. `listZombies`
     7. `reset`
     8. `help`
     9. `exit`
     10. `none`
2. **Mecánica y Gestor de Soles (`SunsManager`):**
   - Clase `SunsManager`:
     - Cada 5 ciclos (en ciclos múltiplos de 5: 5, 10, 15...) añade un sol en una posición aleatoria del tablero.
     - Contadores: `generatedSuns` y `caughtSuns`.
   - Lógica de captura en `Game` / `GameModel`:
     - Solo se permite una captura exitosa por ciclo (`sunCaughtThisCycle`).
     - Al resetear o avanzar de ciclo, se reinicia el flag de captura.
3. **Vista y Estado (`GameStatus` & `GamePrinter`):**
   - Métodos en `GameStatus`: `getGeneratedSuns()` y `getCaughtSuns()`.
   - Actualizar `GamePrinter.getInfo()` para incluir las dos líneas de soles:
     ```
     Generated suns: %d
     Caught suns: %d
     ```

---

### 👤 Persona 2: Nuevos GameObjects, Factorías, Almacén y Acciones (`GameAction`)

Encargada de las nuevas entidades del juego, sus comportamientos, la física de explosiones y el soporte de celdas compartidas en el contenedor.

1. **Nuevos Objetos de Juego:**
   - **`WallNut`:** Coste 50, resistencia 10, daño 0, símbolo `WN`.
   - **`CherryBomb`:** Coste 50, resistencia 2, daño 10. Cuenta atrás de 2 ciclos. Pasa de `c` a `C` el ciclo previo a explotar. Al explotar activa una `ExplosionAction`.
   - **`BucketHeadZombie`:** Resistencia 8, velocidad 4 (avanza cada 4 ciclos), daño 1, símbolo `Bz`.
   - **`SportyZombie`:** Resistencia 2, velocidad 1 (avanza cada ciclo), daño 1, símbolo `Sz`.
   - **`ExplosiveZombie`:** Resistencia 5, velocidad 2, daño 1, símbolo `Ez`. Si lo mata una planta, activa una `ExplosionAction`.
   - **`Sun`:** Objeto de juego con tiempo de vida de 10 ciclos, símbolo `*[XX]`. No es dañado por ataques de plantas ni zombies.
   - **`Sunflower` (actualización):** En vez de dar monedas directamente, spawnea un `Sun` en casilla aleatoria cada 3 ciclos.
2. **Factorías y Probabilidades:**
   - **`PlantFactory`:** Registrar `WallNut` y `CherryBomb` en `AVAILABLE_PLANTS`.
   - **`ZombieFactory`:** Clase factoría con `AVAILABLE_ZOMBIES`, `spawnZombie(name, game, pos)` y `getAvailableZombies()`.
   - **`ZombiesManager`:** Modificar para que los 4 tipos de zombies aparezcan con igual probabilidad (1/4 cada uno).
3. **Sistema de Acciones y Explosiones (`GameAction` & `ExplosionAction`):**
   - Paquete `tp1.pvz.logic.actions`.
   - Interfaz `GameAction`: `void execute(GameWorld game)`.
   - Clase `ExplosionAction implements GameAction`: Daña a todas las entidades enemigas en un área 3x3 alrededor del origen.
   - En `Game`: Cola `Deque<GameAction> actions`, método `pushAction()` y bucle de cascada de muertes en `update()` (`deadRemoved || arePendingActionsLeft()`).
4. **Adaptación de `GameObjectContainer`:**
   - `isEmpty(pos)`: Debe ignorar los soles (un sol no impide plantar ni que camine un zombie).
   - `positionToString(pos)`: Soportar concatenación si en la misma casilla coinciden sol y planta/zombie (ej. `S[01] *[02]`, `*[06]Sz[02]` o `*[05]`).
   - `catchSuns(pos)`: Método para extraer y eliminar todos los soles en una posición.

---

## 🤝 Contrato de Integración Común (Preparado en `main`)

Antes de abrir ramas, dejamos definidos los esqueletos en `main`:
- `GameStatus`: `getGeneratedSuns()`, `getCaughtSuns()`.
- `GameWorld`: `pushAction(GameAction)`, `addSun(Position)`.
- `GameModel`: `isValidZombie(String)`, `addZombie(String, Position)`, `addPlantCheat(String, Position)`, `catchSun(Position)`.
- `GameAction`: interfaz en `tp1.pvz.logic.actions`.
- `ZombieFactory`: clase con stubs estáticos.
- `Game`: stubs de los métodos anteriores para que compile sin fallos.

---

## 🌿 Estrategia de Ramas en Git

1. **Ramas independientes:**
   - Persona 1: `feature/comandos-soles`
   - Persona 2: `feature/objetos-explosiones`
2. **Hitos:**
   - Ambos parten de `main` con los esqueletos listos.
   - Al terminar cada bloque, se hace commit y push en la rama de la feature.
   - Se realiza el merge a `main` y se pasan los tests de `src/test/p2`.
