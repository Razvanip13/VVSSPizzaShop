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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TopDownRTest {

    private Payment payment,payment2;
    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService service;

    @BeforeEach
    public  void setUp(){
        paymentRepository =  new PaymentRepository("src/main/resources/data/payments.txt");
        menuRepository= mock(MenuRepository.class);
        payment = mock(Payment.class);
        payment2 = mock(Payment.class);
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
    }

    @Test
    void testAddPayment(){
        when(payment.toString()).thenReturn("6,Cash,18.0");
        when(payment.getAmount()).thenReturn(18d);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.getTableNumber()).thenReturn(6);


        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        List<Payment> paymentList = service.getPayments();
        Payment the_payment = paymentList.get(paymentList.size()-1);

        assertEquals(the_payment.getAmount(),payment.getAmount());
        assertEquals(the_payment.getTableNumber(),payment.getTableNumber());
        assertEquals(the_payment.getType(),payment.getType());
    }

    @Test
    void testGetPayments(){
        when(payment.toString()).thenReturn("6,Cash,18.0");
        when(payment.getAmount()).thenReturn(18d);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.getTableNumber()).thenReturn(6);
        when(payment2.toString()).thenReturn("4,Cash,15.0");
        when(payment2.getAmount()).thenReturn(12d);
        when(payment2.getType()).thenReturn(PaymentType.Cash);
        when(payment2.getTableNumber()).thenReturn(4);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        service.addPayment(payment2.getTableNumber(),payment2.getType(),payment2.getAmount());

        try {
            assertEquals(30.0,service.getTotalAmount("Cash"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
