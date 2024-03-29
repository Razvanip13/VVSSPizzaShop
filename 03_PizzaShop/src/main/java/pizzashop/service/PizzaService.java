package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;


import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        if(table <= 0 || table >8) return;
        if(amount <= 0 || amount > 10000) return;

        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(String type) throws ServiceException {

        double total=0.0f;
        List<Payment> l=getPayments();
        if(!type.equals("Card") && !type.equals("Cash")){
            throw new ServiceException("Invalid payment type.");
        }

        if(l==null || l.isEmpty()){
            return total;
        }
        for (Payment p:l){
            if (p.getType().name().equals(type))
                total+=p.getAmount();
        }
        return total;
    }
}