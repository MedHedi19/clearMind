package esprit.javafxesprit.utils;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import esprit.javafxesprit.controllers.patient.PaymentController;

public class StripePaymentService {
    public static String createCheckoutSession(double amount, PaymentController controller) throws Exception {
        String stripeApiKey = "sk_test_51R19kHHJiJV0Z4FudkPJ492FhXd8dW1cIt22baOgoI48wEFoE2HN1aD53OO06rQszsSeVNxYB2MgA2AFQzZVsGDz00G83s6TVN";

        if (stripeApiKey == null || stripeApiKey.isEmpty()) {
            throw new RuntimeException("La clé API Stripe n'est pas configurée.");
        }

        Stripe.apiKey = stripeApiKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success") // You'll need to implement this endpoint
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount((long) (amount * 100))
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Réservation Événement")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);
        return session.getUrl();
    }
}