import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/vuelosypasajeros";
    private static final String USER = "root";
    private static final String PASSWORD = "Dam2425*";

    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            // Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Desactivar el modo de auto-confirmación
            conn.setAutoCommit(false);
            
            // Primera operación INSERT
            String sqlInsert1 = "INSERT INTO Pasajeros (num, cod_vuelo, tipo_plaza, fumador) VALUES (?, ?, ?,?)";
            try (PreparedStatement pstmt1 = conn.prepareStatement(sqlInsert1)) {
                pstmt1.setInt(1, 144);
                pstmt1.setString(2, "FR-DC7-247");
                pstmt1.setString(3, "TU");
                pstmt1.setString(4, "NO");
                pstmt1.executeUpdate();
            }
            
            // Simulación de un fallo antes de la segunda operación
            if (true) {  // Condición de prueba para lanzar la excepción
                throw new SQLException("Error simulado antes de la segunda operación INSERT.");
            }
            
            // Segunda operación INSERT
            String sqlInsert2 = "INSERT INTO Pasajeros (num, cod_vuelo, tipo_plaza, fumador) VALUES (?, ?, ?,?)";
            try (PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert2)) {
                pstmt2.setInt(1, 145);
                pstmt2.setString(2, "FR-DC7-247");
                pstmt2.setString(3, "TU");
                pstmt2.setString(4, "SI");
                pstmt2.executeUpdate();
            }
            
            // Confirmar la transacción si ambas operaciones fueron exitosas
            conn.commit();
            System.out.println("Las inserciones se realizaron correctamente.");
            
        } catch (SQLException e) {
            // En caso de error, realizar rollback de la transacción
            System.out.println("Error en la transacción: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transacción revertida.");
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al realizar el rollback: " + rollbackEx.getMessage());
                }
            }
        } finally {
            // Restaurar el modo de auto-confirmación y cerrar la conexión
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }
}


