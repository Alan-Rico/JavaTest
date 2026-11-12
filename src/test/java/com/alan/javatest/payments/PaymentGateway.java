package com.alan.javatest.payments;

public interface PaymentGateway {

    PaymentResponse requestPayment (PaymentRequest request);
}
