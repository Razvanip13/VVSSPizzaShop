package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    private static MenuRepository menuRepository;
    private static PaymentRepository paymentRepository;
    private PizzaService service;

    @BeforeAll
    static void init(){
        menuRepository =new MenuRepository();
        paymentRepository =new PaymentRepository();
    }

    @BeforeEach
    void setUp() {
        service =new PizzaService(menuRepository,paymentRepository);
    }

    @AfterEach
    void tearDown() {
        //Nu are sens. Stim cum merge
        //Glory to Ukraine. Glory to Ukraine
    }

    @AfterAll
    static void cleanUp(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("target/classes/data/payments.txt"));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddPaymentECPValid1() {
        int table=4;
        double amount =5;
        PaymentType type=PaymentType.Card;


        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAValid1() {
        int table = 1;
        double amount = 5;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAValid2() {
        int table = 8;
        double amount = 5;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentECPInvalid1() {
        int table=50;
        double amount =0;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

    @Test
    void testAddPaymentECPInvalid2() {
        int table=-4;
        double amount =-5;
        PaymentType type=PaymentType.Card;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

    @Test
    void testAddPaymentECPValid2() {
        int table=7;
        double amount =6000;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

	@Test
    void testAddPaymentBVAInvalid1(){
        int table=2;
        double amount =0;
        PaymentType type=PaymentType.Cash;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

	@Test
    void testAddPaymentBVAInvalid2(){
        int table=2;
        double amount =-1;
        PaymentType type=PaymentType.Card;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }

}