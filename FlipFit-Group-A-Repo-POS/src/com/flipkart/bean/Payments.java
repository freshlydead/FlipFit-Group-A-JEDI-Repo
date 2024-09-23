package com.flipkart.bean;

/**
 * Represents a payment transaction with details such as card information and payment ID.
 * Contains information about the payment's unique identifier, card number, expiry date, cardholder's name, and CVV.
 */
public class Payments {

    private int paymentsId;
    private String cardNumber;
    private String expiryDate;
    private String name;
    private String cvv;

    public int getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(int paymentsId) {
        this.paymentsId = paymentsId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
