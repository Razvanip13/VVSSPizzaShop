package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    private static MenuRepository menuRepository;
    private static PaymentRepository paymentRepository;
    private static PizzaService service;

    @BeforeAll
    static void init() {
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository("src/main/resources/data/payments.txt");
    }

    @BeforeEach
    void setUp() {
        service = new PizzaService(menuRepository, paymentRepository);
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

    @AfterAll
    static void cleanUp() {
        service =null;
    }


    @Test
    void testAddPaymentECPValid1() {
        int table = 4;
        double amount = 5000;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAValid1() {
        int table = 1;
        double amount = 1;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAValid2() {
        int table = 8;
        double amount = 9999;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentECPInvalid1() {
        int table = -8;
        double amount = -5;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentECPInvalid2() {
        int table = 50;
        double amount = 1200;
        PaymentType type = PaymentType.Cash;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentECPValid2() {
        int table = 6;
        double amount = 6000;
        PaymentType type = PaymentType.Cash;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAInvalid1() {
        int table = 0;
        double amount = -1;
        PaymentType type = PaymentType.Cash;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testAddPaymentBVAInvalid2() {
        int table = 9;
        double amount = 10001;
        PaymentType type = PaymentType.Card;

        service.addPayment(table, type, amount);

        List<Payment> payments = service.getPayments();

        assertNotEquals(new Payment(table, type, amount), payments.get(payments.size() - 1));
    }

    @Test
    void testGetTotalAmountValid(){

        String type="Card";

        try {
            assertEquals(15015.0, service.getTotalAmount(type));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTotalAmountInvalid(){

        String type="";


        service.addPayment(4,PaymentType.Card,5);
        service.addPayment(4,PaymentType.Card,10);


        Exception exception = assertThrows(ServiceException.class, () -> service.getTotalAmount(type));

        String expectedMessage = "Invalid payment type.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetTotalAmountValid2(){

        PaymentRepository paymentRepository2 = new PaymentRepository("target/classes/data/payments_empty.txt");
        PizzaService service2 = new PizzaService(menuRepository,paymentRepository2);


        try {
            assertEquals(0,service2.getTotalAmount("Card"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

}