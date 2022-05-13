package pizzashop.payment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PaymentTest {


    @BeforeEach
    public  void setUp(){
    }

    @AfterEach
    void tearDown() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/data/payments.txt"));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("target/classes/data/payments.txt"));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("target/classes/data/payments_empty.txt"));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAmount(){
        Payment payment = new Payment(5, PaymentType.Card,200);

        assertEquals(200,payment.getAmount());
    }

    @Test
    void testGetType(){
        Payment payment = new Payment(5, PaymentType.Card,200);

        assertEquals(PaymentType.Card,payment.getType());
    }
}
