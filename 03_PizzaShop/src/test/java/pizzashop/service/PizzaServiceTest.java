package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    private MenuRepository menuRepository;
    private PaymentRepository paymentRepository;
    private PizzaService service;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        service = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add_payment_ECP_valid_test1() {
        int table=4;
        double amount =5;
        PaymentType type=PaymentType.Card;


        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void add_payment_BVA_valid_test1() {
        int table = 1;
        double amount = 5;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void add_payment_BVA_valid_test2() {
        int table = 8;
        double amount = 5;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void add_payment_ECP_invalid_test2() {
        int table=50;
        double amount =0;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

    @Test
    void add_payment_ECP_invalid_test1() {
        int table=-4;
        double amount =-5;
        PaymentType type=PaymentType.Card;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

    @Test
    void add_payment_ECP_valid_test2() {
        int table=7;
        double amount =6000;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

	@Test
    void add_payment_BVA_invalid_test_1(){
        int table=0;
        double amount =0;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

	@Test
    void add_payment_BVA_invalid_test_2(){
        int table=9;
        double amount =20.5;
        PaymentType type=PaymentType.Card;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

}