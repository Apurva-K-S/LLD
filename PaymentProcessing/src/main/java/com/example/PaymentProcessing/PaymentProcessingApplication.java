package com.example.PaymentProcessing;

import com.example.PaymentProcessing.Entity.PaymentRequest;
import com.example.PaymentProcessing.Entity.PaymentResponse;
import com.example.PaymentProcessing.Enum.Currency;
import com.example.PaymentProcessing.Enum.PaymentMethod;
import com.example.PaymentProcessing.Service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class PaymentProcessingApplication {

	public static void main(String[] args) {
		PaymentService paymentService = new PaymentService();

		// Example credit card payment request
		Map<String, String> ccDetails = new HashMap<>();
		ccDetails.put("cardNumber", "1234-5678-9876-5432");
		ccDetails.put("cvv", "123");

		UUID uuid = UUID.randomUUID();
		PaymentRequest ccRequest = new PaymentRequest(200.50, "user123", PaymentMethod.CREDIT_CARD, Currency.USD, ccDetails, "txn1001", uuid.toString());

		PaymentResponse ccResponse = paymentService.processPayment(ccRequest);
		System.out.println("Credit Card Payment: " + ccResponse.getMessage());

		// Example wallet payment request with insufficient balance simulation
		Map<String, String> walletDetails = new HashMap<>();
		walletDetails.put("walletId", "wallet789");
		UUID uuid1 = UUID.randomUUID();
		PaymentRequest walletRequest = new PaymentRequest(
				1200.00, // large amount to simulate failure if you add logic
				"user456", PaymentMethod.WALLET, Currency.USD, walletDetails, "txn2002", uuid1.toString());

		PaymentResponse walletResponse = paymentService.processPayment(walletRequest);
		System.out.println("Wallet Payment: " + walletResponse.getMessage());

		// Processing refund
		PaymentResponse refundResponse = paymentService.refundPayment(PaymentMethod.CREDIT_CARD, "txn1001");
		System.out.println("Refund response: " + refundResponse.getMessage());

		// Get status
		System.out.println("Payment Status: " + paymentService.getPaymentStatus(PaymentMethod.CREDIT_CARD, "txn1001"));

	}

}
