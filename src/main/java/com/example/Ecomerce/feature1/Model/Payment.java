package com.example.Ecomerce.feature1.Model;


import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // L'ordre associé à ce paiement

    private BigDecimal amount; // Montant total du paiement

    private String paymentMethod; // Méthode de paiement (ex. "stripe", "paypal")

    private PaymentStatuts paymentStatus; // Statut du paiement (ex. "success", "failed")

    private String paymentReference; // Référence de paiement unique (par exemple, l'ID du paiement Stripe)

    public Payment() {
    }

    public Payment(Order order, BigDecimal amount, String paymentMethod, PaymentStatuts paymentStatus, String paymentReference) {
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentReference = paymentReference;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatuts getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatuts paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}

