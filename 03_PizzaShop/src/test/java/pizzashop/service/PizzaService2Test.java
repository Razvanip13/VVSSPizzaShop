package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaService2Test {

    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService service;

    @BeforeEach
    public  void setUp(){
        paymentRepository =mock(PaymentRepository.class);
        menuRepository= mock(MenuRepository.class);
        this.service=new PizzaService(menuRepository,paymentRepository);
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
    void testAddPayment(){

        Payment payment=new Payment(3, PaymentType.Card,17d);
        when(paymentRepository.getAll()).thenReturn(Collections.singletonList(payment));
        doNothing().when(paymentRepository).add(payment);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        List<Payment> all =service.getPayments();
        assertEquals(all.get(all.size()-1).getAmount(),payment.getAmount());
        verify(paymentRepository).getAll();

    }

    @Test
    void getPayments(){

        when(paymentRepository.getAll()).thenReturn(null);
        assertNull(service.getPayments());
        verify(paymentRepository).getAll();

    }
}
