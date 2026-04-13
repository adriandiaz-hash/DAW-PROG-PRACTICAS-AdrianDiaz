package modelo;

/**
 * Clase que representa la entidad Customer (Cliente).
 * Contiene únicamente los atributos estrictamente necesarios para la 
 * visualización de informes y rankings de pagos, descartando datos de contacto.
 * * @autor [T] Adrián Díaz García
 */
public class Customer {
    
    private int customerNumber;
    private String customerName;
   
    private int numberOfPayments;
    private double totalPaid;

    public Customer() {
    }

    public Customer(int customerNumber, String customerName) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
    }

    public Customer(int customerNumber, String customerName, int numberOfPayments, double totalPaid) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.numberOfPayments = numberOfPayments;
        this.totalPaid = totalPaid;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public void setNumberOfPayments(int numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    @Override
    public String toString() {
        return "Cliente " + customerNumber + " - " + customerName;
    }
}