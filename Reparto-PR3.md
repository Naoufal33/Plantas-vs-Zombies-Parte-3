# Reparto de trabajo de la práctica 3
**Plantas contra Zombis · TP1 · Rama compartida `parte-3`**

Cada persona asume el 50 % del esfuerzo estimado. Cada tarea incluye su implementación y sus pruebas.

## Naoufal · 50 %

| Peso | Entrega | Cómo comprobarla |
|---|---|---|
| 25 % | Implementar `RecordsManager`, `FileRecordsManager` y `RecordException`. Leer y guardar los récords por nivel en `record.txt`. | Acepta niveles en cualquier orden y un fichero vacío. Un nivel sin entrada tiene récord 0. Si el fichero falta o está corrupto al arrancar, el juego comunica el error y termina. |
| 15 % | Cargar al inicio y actualizar el récord al ganar, perder o abandonar. Añadir `ShowRecordCommand` y su entrada en la ayuda. | El comando `o` consulta el récord del nivel sin consumir turno. Una mejora se conserva al reabrir el juego. Una puntuación inferior respeta el máximo y los demás niveles conservan sus récords. |
| 10 % | Mostrar `Score` en el estado del juego y adaptar las salidas esperadas de las pruebas de partida. | El marcador empieza en 0. Una baja normal seguida de otra por explosión deja 30 puntos en pantalla. |

## Compañero de Naoufal · 50 %

| Peso | Entrega | Cómo comprobarla |
|---|---|---|
| 20 % | Crear `GameException`, `CommandParseException` y `CommandExecuteException`. Adaptar los comandos y centralizar los mensajes de error en `Controller`. | Probar un comando desconocido, una orden con argumentos incorrectos y una coordenada no numérica. La última conserva la `NumberFormatException` como causa. |
| 15 % | Trasladar al modelo las validaciones de posición, captura y saldo. Implementar `InvalidPositionException`, `NotCatchablePositionException` y `NotEnoughSuncoinsException`. | Probar una casilla ocupada, una posición fuera del tablero, una captura sin sol y saldo insuficiente. El error identifica la posición cuando procede y conserva el estado de la partida. |
| 15 % | Calcular los puntos en el modelo, reiniciar la puntuación al empezar una partida y exponerla con `getScore()`. | Cada zombi cuenta una sola vez. La baja normal vale 10 y la causada por explosión vale 20. Dos bajas por explosión en cadena suman 40. |

## Coordinación

1. Acordar la firma de `getScore()` y de los comandos. El compañero calcula los puntos. Naoufal usa ese valor para mostrar el marcador y actualizar el récord.
2. Trabajar en dos ramas creadas desde `parte-3`. Naoufal puede probar los ficheros con puntuaciones de ejemplo mientras su compañero desarrolla las excepciones.
3. Revisar mutuamente los cambios e integrar ambas ramas. Jugar una partida con una orden incorrecta y al menos una baja. Al volver a abrir el juego, comprobar que el récord coincide con el máximo alcanzado en ese nivel.
