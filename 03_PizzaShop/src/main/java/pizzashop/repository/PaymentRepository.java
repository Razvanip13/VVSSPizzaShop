package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private String filename = "data/payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository(String filename){
        this.paymentList = new ArrayList<>();
        this.filename = filename;
        readPayments();

    }

    private void readPayments(){
//        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
//        File file = new File(classLoader.getResource(filename).getFile());
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                if(payment!=null) {
                    paymentList.add(payment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment){
        paymentList.add(payment);
        writeAll();
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
//        File file = new File(classLoader.getResource(filename).getFile());
        File file = new File(filename);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}