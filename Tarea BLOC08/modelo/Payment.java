package modelo;

import java.sql.Date;

/**
 * Clase que representa la entidad Payment (Pago).
 * Almacena la información de una transacción específica vinculada a un cliente.
 * * @autor [T] Adrián Díaz García
 */
public class Payment {
    
    private int customerNumber;
    private String checkNumber;
    private Date paymentDate;
    private double amount;

    public Payment() {
    }

    public Payment(int customerNumber, String checkNumber, Date paymentDate, double amount) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return checkNumber + "\t" + paymentDate + "\t" + amount + "€";
    }
}