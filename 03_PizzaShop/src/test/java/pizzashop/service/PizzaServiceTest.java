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
        menuRepository =new MenuRepository();
        paymentRepository =new PaymentRepository();
        service =new PizzaService(menuRepository,paymentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add_payment_ECP_valid_test() {
        int table=4;
        double amount =5;
        PaymentType type=PaymentType.Card;

        service.addPayment(table,type,amount);

        List<Payment> payments = service.getPayments();

        assertEquals(new Payment(table,type,amount),payments.get(payments.size()-1));
    }
}