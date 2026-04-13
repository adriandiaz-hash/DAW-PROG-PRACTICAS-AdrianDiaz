package vista;

import controlador.CustomerDAO;
import controlador.PaymentDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Customer;
import modelo.Payment;

/**
 * Clase principal que gestiona la Vista (interfaz de usuario) y el menú de la aplicación.
 * @autor [T] Adrián Díaz García
 */
public class PagosAdrianDiaz {

    public static void main(String[] args) {
        
        // 1. CONFIGURACIÓN DE LA BASE DE DATOS
        // He ajustado el usuario y la contraseña según la instalación de MySQL que realicé en el módulo de Base de datos.
        String url = "jdbc:mysql://localhost:3306/classicmodels";
        String user = "Adrian";
        String password = "15Octubre*";

        // 2. INICIALIZACIÓN DE LOS AGENTES TÁCTICOS (DAOs)
        CustomerDAO customerDAO = new CustomerDAO(url, user, password);
        PaymentDAO paymentDAO = new PaymentDAO(url, user, password);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("=== SISTEMA TÁCTICO DE PAGOS - INICIADO ===");

        // 3. BUCLE PRINCIPAL DEL MENÚ
        while (opcion != 4) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Top Clientes con más pagos");
            System.out.println("2. Generar informe HTML de cliente");
            System.out.println("3. Modificar pago");
            System.out.println("4. Salir");
            System.out.print("Seleccione una directiva: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del intro

                switch (opcion) {
                    case 1: // TOP CLIENTES
                        System.out.print("¿Cuántos clientes desea en el ranking?: ");
                        int limite = scanner.nextInt();
                        scanner.nextLine();

                        // Llamada al DAO. Si falla, el catch de abajo lo atrapa.
                        ArrayList<Customer> topClientes = customerDAO.obtenerTopClientes(limite);
                        
                        System.out.println("\n--- RANKING TOP " + limite + " ---");
                        System.out.printf("%-10s %-30s %-10s %-15s\n", "ID", "NOMBRE", "PAGOS", "TOTAL (€)");
                        System.out.println("-------------------------------------------------------------------");
                        for (Customer c : topClientes) {
                            System.out.printf("%-10d %-30s %-10d %-15.2f\n", 
                                    c.getCustomerNumber(), c.getCustomerName(), 
                                    c.getNumberOfPayments(), c.getTotalPaid());
                        }
                        break;

                    case 2: // GENERAR INFORME HTML
                        System.out.print("Introduzca el ID del cliente (ej. 141): ");
                        int idCliente = scanner.nextInt();
                        scanner.nextLine();

                        Customer cliente = customerDAO.obtenerClientePorId(idCliente);
                        if (cliente == null) {
                            System.out.println("[!] ERROR: No se ha encontrado ningún cliente con el ID " + idCliente);
                            break;
                        }

                        ArrayList<Payment> pagos = paymentDAO.obtenerPagosCliente(idCliente);
                        double totalPagos = paymentDAO.obtenerSumaPagosCliente(idCliente);

                        generarInformeHTML(cliente, pagos, totalPagos);
                        break;

                    case 3: // MODIFICAR PAGO
                        System.out.print("Introduzca el ID del cliente: ");
                        int idC = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Introduzca el número de cheque (ej. IN446258): ");
                        String check = scanner.nextLine();
                        
                        System.out.print("Introduzca la variación de importe (use negativo para restar): ");
                        double variacion = scanner.nextDouble();
                        scanner.nextLine();

                        boolean modificado = paymentDAO.modificarPago(idC, check, variacion);
                        if (modificado) {
                            System.out.println("[+] Operación exitosa. Pago actualizado en el servidor.");
                        } else {
                            System.out.println("[-] Error de actualización: Verifica que el cliente y el cheque existan.");
                        }
                        break;

                    case 4: // SALIR
                        System.out.println("Cerrando sesión.[T].");
                        break;

                    default:
                        System.out.println("[!] Comando no reconocido.");
                }

            } catch (InputMismatchException e) {
                System.out.println("[!] ERROR CATASTRÓFICO DE USUARIO: Debes introducir un número.");
                scanner.nextLine(); // Limpiar la basura introducida
            } catch (SQLException e) {
                System.out.println("[!] ERROR DE CONEXIÓN A BBDD: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[!] ERROR CRÍTICO INESPERADO: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Genera el archivo HTML estructurado según las especificaciones de la Opción A.
     */
    private static void generarInformeHTML(Customer cliente, ArrayList<Payment> pagos, double total) {
        String nombreArchivo = "informeAdrianDiaz.html";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            
            // Cabecera HTML y CSS incrustado
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang='es'>");
            writer.println("<head><meta charset='UTF-8'><title>Informe de Pagos - " + cliente.getCustomerName() + "</title>");
            writer.println("<style>");
            writer.println("body { font-family: 'Consolas', monospace; background-color: #f4f4f4; color: #333; padding: 20px; }");
            writer.println(".container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 800px; margin: auto; }");
            writer.println("h1 { color: #2c3e50; border-bottom: 2px solid #e74c3c; padding-bottom: 10px; }");
            writer.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            writer.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            writer.println("th { background-color: #2c3e50; color: white; }");
            writer.println(".total { font-weight: bold; font-size: 1.2em; background-color: #e8f8f5; }");
            writer.println(".footer { margin-top: 40px; font-size: 0.9em; color: #7f8c8d; text-align: right; border-top: 1px solid #ccc; padding-top: 10px; }");
            writer.println("</style></head>");
            
            // Cuerpo del documento
            writer.println("<body><div class='container'>");
            writer.println("<h1>Informe Confidencial de Cliente</h1>");
            writer.println("<p><strong>ID Cliente:</strong> " + cliente.getCustomerNumber() + "</p>");
            writer.println("<p><strong>Nombre Corporativo:</strong> " + cliente.getCustomerName() + "</p>");
            
            // Tabla de pagos
            writer.println("<table>");
            writer.println("<tr><th>Check Number</th><th>Payment Date</th><th>Amount (€)</th></tr>");
            for (Payment p : pagos) {
                writer.println("<tr>");
                writer.println("<td>" + p.getCheckNumber() + "</td>");
                writer.println("<td>" + p.getPaymentDate() + "</td>");
                writer.println("<td>" + String.format("%.2f", p.getAmount()) + "</td>");
                writer.println("</tr>");
            }
            
            // Fila de Suma Total (Última línea)
            writer.println("<tr class='total'><td colspan='2' style='text-align: right;'>SUMA TOTAL:</td>");
            writer.println("<td>" + String.format("%.2f", total) + "</td></tr>");
            writer.println("</table>");
            
            // Pie de página (Nombre y Hora temporal)
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.println("<div class='footer'>");
            writer.println("<p>Generado por: Adrián Díaz García [T]</p>");
            writer.println("<p>Fecha de emisión: " + dtf.format(now) + "</p>");
            writer.println("</div>");
            
            // Cierre
            writer.println("</div></body></html>");
            
            System.out.println("[+] Informe compilado y guardado en la raíz del proyecto como: " + nombreArchivo);
            
        } catch (IOException e) {
            System.out.println("[-] Error crítico al escribir el archivo en el disco: " + e.getMessage());
        }
    }
}