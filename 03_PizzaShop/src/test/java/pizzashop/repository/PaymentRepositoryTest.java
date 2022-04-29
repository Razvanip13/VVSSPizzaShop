package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentRepositoryTest {

    private Payment payment;
    private Payment payment2;
    private PaymentRepository paymentRepository;

    @BeforeEach
    public  void setUp(){
        payment=mock(Payment.class);
        payment2=mock(Payment.class);
        paymentRepository =new PaymentRepository("src/main/resources/data/payments.txt");
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
    }

    @Test
    void testAddPayment(){

        when(payment.getAmount()).thenReturn(18d);
        when(payment.getTableNumber()).thenReturn(10);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.toString()).thenReturn("10,Cash,18.0");
        paymentRepository.add(payment);
        List<Payment> payments= paymentRepository.getAll();
        Payment latestPayment= payments.get(payments.size()-1);
        assertEquals(latestPayment.getTableNumber(),payment.getTableNumber());

    }

    @Test
    void testGetAll(){

//        when(payment.getAmount()).thenReturn(18d);
//        when(payment.getTableNumber()).thenReturn(10);
//        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.toString()).thenReturn("10,Cash,18.0");
        paymentRepository.add(payment);
//        verify(payment).getAmount();

//        when(payment2.getAmount()).thenReturn(190d);
//        when(payment2.getTableNumber()).thenReturn(4);
//        when(payment2.getType()).thenReturn(PaymentType.Card);
        when(payment2.toString()).thenReturn("4,Card,190.0");
        paymentRepository.add(payment2);
//        verify(payment2).getType();

        List<Payment> payments= paymentRepository.getAll();
        Payment latestPayment= payments.get(payments.size()-1);
        assertEquals(latestPayment.getTableNumber(),payment2.getTableNumber());

        Payment theOtherPayment = payments.get(payments.size()-2);
        assertEquals(theOtherPayment.getAmount(),payment.getAmount());
    }

}
