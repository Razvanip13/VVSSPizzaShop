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


class TopDownETest {

    private Payment payment, payment2;
    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService service;

    @BeforeEach
    public void setUp() {
        paymentRepository = new PaymentRepository("src/main/resources/data/payments.txt");

        payment = new Payment(6, PaymentType.Cash, 18d);
        payment2 = new Payment(4, PaymentType.Cash, 12);

        this.service = new PizzaService(menuRepository, paymentRepository);
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
    void testAddPayment() {

        service.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        List<Payment> paymentList = service.getPayments();
        Payment the_payment = paymentList.get(paymentList.size() - 1);

        assertEquals(the_payment, payment);
    }

    @Test
    void testGetPayments() {
        service.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        service.addPayment(payment2.getTableNumber(), payment2.getType(), payment2.getAmount());

        try {
            assertEquals(30.0, service.getTotalAmount("Cash"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
