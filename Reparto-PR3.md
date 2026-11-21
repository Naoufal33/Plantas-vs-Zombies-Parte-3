# 🌿 Plan de Trabajo y Reparto de Tareas: Práctica 3

**Documento de referencia:** `practica3.md` (*Excepciones y ficheros*)  
**Fecha límite de entrega:** Semana del 30 de noviembre  
**Componentes del grupo:**
- Naoufal Kebli
- Pablo Palacios Rico

---

## 🎯 Objetivo de la Práctica 3

En esta tercera práctica se dota a la aplicación de mayor robustez, persistencia y jugabilidad mediante tres bloques principales:

1. **Definición y Tratamiento de Excepciones:**
   - Crear una jerarquía de excepciones propia (`GameException`) para encapsular errores de usuario y del sistema.
   - Centralizar la notificación de errores en `Controller.run()`, evitando que los comandos o el modelo impriman mensajes directamente.
   - Refactorizar las firmas de `Command` (`create`, `parse` y `execute`) para lanzar excepciones específicas (`CommandParseException`, `CommandExecuteException`).
   - Crear excepciones semánticas para posiciones inválidas (`InvalidPositionException`), casillas sin soles (`NotCatchablePositionException`) y saldo insuficiente (`NotEnoughSuncoinsException`).
   - Envolver excepciones de bajo nivel del sistema (como `NumberFormatException`) preservando su causa.

2. **Sistema de Puntuación (`Score`):**
   - Incorporar puntuación en vivo a la partida:
     - **10 puntos** por cada zombie eliminado mediante ataques comunes (disparos de Peashooter).
     - **20 puntos** por cada zombie eliminado como consecuencia de una explosión (`CherryBomb` o `ExplosiveZombie`).
   - Mostrar la puntuación en el estado de la partida (`Score: XX`).
   - Reiniciar los puntos al reiniciar la partida (`reset`).

3. **Persistencia de Récords y Gestión de Ficheros (`record.txt`):**
   - Diseñar la capa de persistencia mediante la interfaz `RecordsManager` y su implementación `FileRecordsManager` en el paquete `tp1.pvz.logic.record`.
   - Cargar y guardar los récords de cada nivel (`EASY`, `HARD`, `INSANE`) en el fichero `record.txt` en la raíz del proyecto.
   - Manejar fallos de persistencia con la excepción `RecordException`: si el fichero no existe o está corrupto durante el arranque, se notifica y se detiene el programa.
   - Añadir el comando `ShowRecordCommand` (`o` / `record`) para consultar el récord del nivel actual sin consumir turno.

---

## 👥 Reparto Equitativo de Tareas (50% / 50%)

```
                               ┌────────────────────────────────────────┐
                               │               Game.java                │
                               │        (Modelo, Score y Récords)       │
                               └───────────────────┬────────────────────┘
                                                   │
                         ┌─────────────────────────┴─────────────────────────┐
                         ▼                                                   ▼
             ┌───────────────────────┐                           ┌───────────────────────┐
             │       PERSONA 1       │                           │       PERSONA 2       │
             │     Naoufal Kebli     │                           │  Pablo Palacios Rico  │
             │   Gestión de Récords, │                           │ Jerarquía Excepciones,│
             │  Ficheros y Comando   │                           │  Refactor Comandos y  │
             │   (RecordsManager)    │                           │    Cálculo de Score   │
             └───────────────────────┘                           └───────────────────────┘
```

---

### 👤 Persona 1: Naoufal Kebli (50%)
**Área de responsabilidad:** Persistencia en ficheros (`record.txt`), interfaz `RecordsManager`, excepción `RecordException`, comando `ShowRecordCommand` e integración de la puntuación en la vista.

1. **Capa de Persistencia y Récords (`tp1.pvz.logic.record`):**
   - **Interfaz `RecordsManager`:**
     - `void load() throws RecordException;`
     - `void save() throws RecordException;`
     - `int getRecord(Level level);`
     - `void updateRecord(Level level, int score) throws RecordException;`
   - **Clase `FileRecordsManager implements RecordsManager`:**
     - Manejo de I/O mediante `BufferedReader` / `BufferedWriter` (o `Scanner` / `PrintWriter`).
     - Fichero objetivo: `record.txt` en la raíz del proyecto.
     - Formato clave-valor: `NIVEL:puntuacion` (ejemplo: `EASY:20`, `HARD:50`, `INSANE:40`).
     - Debe admitir cualquier orden de aparición de los niveles y soportar nuevos niveles futuros.
     - Si un nivel no tiene entrada registrada en el fichero, devuelve por defecto `0`.
     - El fichero puede estar vacío inicialmente hasta que se registre una puntuación mayor que 0.
   - **Excepción `RecordException`:**
     - Subclase de `GameException`.
     - Se lanza si el fichero `record.txt` no existe o si tiene un formato corrupto/inválido en el momento de arranque del juego, provocando el fin de la ejecución con su mensaje informativo.

2. **Ciclo de Vida del Récord en `Game`:**
   - En la inicialización de `Game`, instanciar y cargar el `RecordsManager`.
   - Al terminar la partida (victoria, derrota o abandono mediante `exit`), comprobar si `currentScore > record` y actualizar/guardar el nuevo récord en `record.txt`.
   - Asegurar que al cambiar de nivel en caliente con `reset <level> <seed>`, se consulte y actualice el récord del nuevo nivel seleccionado.

3. **Comando `ShowRecordCommand` (`Rec[o]rd`):**
   - Letra / atajo: `o` (nombre: `record`).
   - Texto de ayuda: `Rec[o]rd: show record of the current level`.
   - Comportamiento: Consulta el récord del nivel en juego y lo imprime en pantalla con el formato:
     ```
     [DEBUG] Executing: o

     <LEVEL> record is <SCORE>
     ```
   - **No consume ciclo de juego** (devuelve `false` tras ejecutarse).
   - Registrar el comando en `Command.AVAILABLE_COMMANDS` en la posición estipulada en el enunciado (justo antes de `help`).

4. **Visualización y Estado (`GamePrinter`):**
   - Actualizar `GamePrinter` para incluir la línea de puntuación en el estado de la partida:
     ```
     Score: %d
     ```
     justo debajo de `Caught suns: %d`.

---

### 👤 Persona 2: Pablo Palacios Rico (50%)
**Área de responsabilidad:** Jerarquía de excepciones (`GameException`), refactorización de `Command` y `Controller`, excepciones semánticas del juego y lógica de cálculo de puntos en el modelo.

1. **Jerarquía Base de Excepciones (`tp1.pvz.exceptions`):**
   - **`GameException extends Exception`:** Superclase base de todas las excepciones del juego, con constructores para mensaje y causa (`super(message, cause)`).
   - **`CommandParseException extends GameException`:** Excepción para errores producidos durante el análisis/parseo del comando (`create()` y `parse()`).
   - **`CommandExecuteException extends GameException`:** Excepción base para fallos producidos durante la ejecución del comando (`execute()`).

2. **Excepciones Semánticas Específicas del Juego:**
   - **`InvalidPositionException extends CommandExecuteException`:**
     - Se lanza al indicar una casilla inválida (fuera del tablero) o ya ocupada.
     - Almacena y comunica las coordenadas problemáticas `(col, row)`.
   - **`NotCatchablePositionException extends InvalidPositionException` (o `CommandExecuteException`):**
     - Se lanza cuando el usuario ejecuta `catch` en una posición donde no hay soles disponibles o si ya se capturaron soles en ese ciclo.
   - **`NotEnoughSuncoinsException extends CommandExecuteException`:**
     - Se lanza cuando el jugador no tiene suficientes suncoins para plantar una planta. Comunica los costes y monedas disponibles.

3. **Refactorización de la Jerarquía de Comandos (`Command`):**
   - Modificar las firmas de los métodos:
     - `public Command create(String[] parameters) throws CommandParseException;`
     - `public static Command parse(String[] commandWords) throws CommandParseException;`
     - `public abstract boolean execute(GameWorld game) throws CommandExecuteException;`
   - Si el comando es desconocido, lanzar `CommandParseException(Messages.UNKNOWN_COMMAND)`.
   - Si el número de parámetros no coincide, lanzar `CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER)`.
   - Si las coordenadas no son números enteros, capturar `NumberFormatException` y relanzar `CommandParseException` envolviendo la excepción original como causa.
   - Adaptar los 10 comandos existentes para eliminar validaciones manuales con `if` y lanzar las excepciones pertinentes.

4. **Centralización en `Controller`:**
   - Modificar el bucle `run()` de `Controller`:
     ```java
     try {
         Command command = Command.parse(userInput);
         command.execute(game);
     } catch (GameException e) {
         view.showError(e.getMessage());
     }
     ```
   - Ninguna clase del modelo ni de los comandos debe llamar directamente a `view.showError` o imprimir mensajes de error por consola.

5. **Lógica de Puntuación en el Modelo (`Game`):**
   - Atributo privado `score` en `Game`.
   - Método `public int getScore()` expuesto a través de `GameStatus`.
   - Sumar **10 puntos** cuando un zombi muere por daño común (disparo de `Peashooter`).
   - Sumar **20 puntos** cuando un zombi muere como resultado de una acción de explosión (`ExplosionAction`).
   - En explosiones encadenadas, cada zombi eliminado otorga 20 puntos individuales.
   - Reiniciar `score = 0` al ejecutar `reset`.

---

## 🤝 Contrato de Integración y Dependencias Mínimas

Para que ambos miembros puedan trabajar en paralelo en sus respectivas ramas sin bloquearse:

1. **Esqueleto de `GameException`:**  
   Se deja definida la clase base `GameException` en `main` para que Naoufal pueda heredar `RecordException` sin esperar a que Pablo termine toda la jerarquía.
2. **Método `getScore()`:**  
   Se deja declarado `int getScore()` en `GameStatus` con retorno temporal `0` para que Naoufal pueda conectarlo a `GamePrinter` y a `FileRecordsManager`.
3. **Formato de `record.txt`:**  
   El fichero se ubica en la raíz del proyecto. Si no existe, Naoufal lo creará en el momento del guardado, pero si se arranca con un fichero corrupto lanzará `RecordException`.

---

## 🌿 Estrategia de Ramas en Git

Ambos integrantes trabajan sobre el repositorio común `Plantas-vs-Zombies-Parte-3`:

```
main (Base inicial limpia Parte 2)
 │
 ├──► feature/records-ficheros  (Naoufal: RecordsManager, FileRecordsManager, ShowRecordCommand, Score en vista)
 │
 └──► feature/excepciones       (Pablo: GameException, refactor Command/Controller, puntuación en Game)
```

### Protocolo de Integración:
1. **Desarrollo independiente:** Cada integrante realiza commits frecuentes en su propia rama con mensajes claros en español.
2. **Pull Request / Merge:** Al completar sus tareas, cada uno fusiona su rama a `main`.
3. **Pruebas de regresión:** Se ejecutan los tests oficiales de la práctica asegurando que todas las salidas coinciden con el comportamiento esperado.
