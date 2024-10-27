# UD2ManejoTransaccion
Ejercicio Manejo de transacciones 
* a.	Escribe un programa Java que realice dos operaciones `INSERT` en la base de datos de vuelospasajeros dentro de una transacción. Si la segunda operación falla, la transacción debe ser revertida (rollback). Simula esta situación.

Sugerencias:
 *	Usa `setAutoCommit(false)` para desactivar el modo de auto-confirmación.
 *	Implementa al menos dos operaciones `INSERT` y lanza una excepción en medio para probar la cancelación de las transacciones. Por ejemplo:
    -	String sqlInsert1 = "INSERT INTO Pasajeros (num, cod_vuelo, tipo_plaza, fumador) VALUES (?, ?, ?,?)";
    -	INSERT INTO PASAJEROS VALUES(144,'FR-DC7-247','TU','NO');
    -	String sqlInsert2= "INSERT INTO Pasajeros (num, cod_vuelo, tipo_plaza, fumador) VALUES (?, ?, ?,?)";
    -	INSERT INTO PASAJEROS VALUES(145,'FR-DC7-247','TU','SI');
 *  Usa `commit()` para confirmar los cambios o `rollback()` para deshacer los cambios si hay errores.
 *  Usa el bloque `try-catch-finally` para garantizar que se realice `commit` o `rollback` según corresponda.
