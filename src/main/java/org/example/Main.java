package org.example;

import configuration.OrderJpa;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.OrderDetailsRepository;
import repository.OrdersRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(OrderJpa.class);
    static OrdersRepository ordersRepository = (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static void main(String[] args) {
        Main action = new Main();
//        action.createTestData();
//        printListOfOrder(action.findAllOrder());
//        OrdersEntity orderNow = createNewOrder(LocalDate.now(),"Customer-now","Customer-now-address");
//        ordersRepository.save(orderNow);
//        OrderDetailsEntity orderDetailsNow = createNewOrderDetails("product01",4,199.83);
//        orderDetailsNow.setOrders(orderNow);
//        orderDetailsRepository.save(orderDetailsNow);
//        printListOfOrder(action.findOrderByCurrentMonth());
//        printListOfOrder(action.findOrderWhichTotalBuyGreaterOrEqualThan(1000));
        printListOfOrder(action.findOrderWhichPurchaseProduct("product04"));
    }


    //Print
    private static void printListOfOrder(List<OrdersEntity>ordersList){
        for (int i=0; i<ordersList.size();i++){
            System.out.println(ordersList.get(i).toString());
        }
    }

    private static void printOrder (OrdersEntity order){
        System.out.println(order.toString());
    }

    //Allow for users to create new orderDetail
    private static OrderDetailsEntity createNewOrderDetails(String productName, int quantity, double unitPrice) {
        return new OrderDetailsEntity(productName, quantity, unitPrice);
    }
    public OrderDetailsEntity createNewOrderDetailsFix(String productName, int quantity, double unitPrice){
       OrderDetailsEntity orderDetails = new OrderDetailsEntity(productName, quantity, unitPrice);
       orderDetailsRepository.save(orderDetails);
       return orderDetails;
    }

    //Allow for users to create new order
    private static OrdersEntity createNewOrder(LocalDate orderDate, String customerName, String customerAddress) {
        return new OrdersEntity(orderDate, customerName, customerAddress);
    }
    public OrdersEntity createNewOrderFix(LocalDate orderDate, String customerName, String customerAddress){
        OrdersEntity order = new OrdersEntity(orderDate, customerName, customerAddress);
        ordersRepository.save(order);
        return order;
    }

    private void createTestData(){
        OrderDetailsEntity orderDetails1Of1 = createNewOrderDetails("product01",1,199.83);
        OrderDetailsEntity orderDetails2Of1 = createNewOrderDetails("product02",2,122.24);
        OrderDetailsEntity orderDetails3Of1 = createNewOrderDetails("product03",1,88.33);
        OrdersEntity order1 = createNewOrder(LocalDate.parse("2020-08-17"),"Customer001","Customer001-Address");
        ordersRepository.save(order1);
        orderDetails1Of1.setOrders(order1);
        orderDetails2Of1.setOrders(order1);
        orderDetails3Of1.setOrders(order1);
        orderDetailsRepository.save(orderDetails1Of1);
        orderDetailsRepository.save(orderDetails2Of1);
        orderDetailsRepository.save(orderDetails3Of1);

        OrderDetailsEntity orderDetails1Of2 = createNewOrderDetails("product01",4,199.83);
        OrderDetailsEntity orderDetails2Of2 = createNewOrderDetails("product04",3,422.46);
        OrderDetailsEntity orderDetails3Of2 = createNewOrderDetails("product05",2,328.87);
        OrdersEntity order2 = createNewOrder(LocalDate.parse("2020-09-14"),"Customer002","Customer002-Address");
        ordersRepository.save(order2);
        orderDetails1Of2.setOrders(order2);
        orderDetails2Of2.setOrders(order2);
        orderDetails3Of2.setOrders(order2);
        orderDetailsRepository.save(orderDetails1Of2);
        orderDetailsRepository.save(orderDetails2Of2);
        orderDetailsRepository.save(orderDetails3Of2);

        OrderDetailsEntity orderDetails1Of3 = createNewOrderDetails("product01",1,199.83);
        OrderDetailsEntity orderDetails2Of3 = createNewOrderDetails("product05",3,328.87);
        OrderDetailsEntity orderDetails3Of3 = createNewOrderDetails("product03",2,88.83);
        OrdersEntity order3 = createNewOrder(LocalDate.parse("2020-10-21"),"Customer003","Customer003-Address");
        ordersRepository.save(order3);
        orderDetails1Of3.setOrders(order3);
        orderDetails2Of3.setOrders(order3);
        orderDetails3Of3.setOrders(order3);
        orderDetailsRepository.save(orderDetails1Of3);
        orderDetailsRepository.save(orderDetails2Of3);
        orderDetailsRepository.save(orderDetails2Of3);

        OrderDetailsEntity orderDetails1Of4 = createNewOrderDetails("product01",2,199.83);
        OrderDetailsEntity orderDetails2Of4 = createNewOrderDetails("product02",1,122.83);
        OrderDetailsEntity orderDetails3Of4 = createNewOrderDetails("product04",2,422.46);
        OrdersEntity order4 = createNewOrder(LocalDate.parse("2020-11-08"),"Customer001","Customer001-Address");
        ordersRepository.save(order4);
        orderDetails1Of4.setOrders(order4);
        orderDetails2Of4.setOrders(order4);
        orderDetails3Of4.setOrders(order4);
        orderDetailsRepository.save(orderDetails1Of4);
        orderDetailsRepository.save(orderDetails2Of4);
        orderDetailsRepository.save(orderDetails3Of4);
    }

    //Find all orders
    public List<OrdersEntity> findAllOrder (){
        return (List<OrdersEntity>) ordersRepository.findAllOrders();
    }

    //Find an order and order details by order_id
    public OrdersEntity findOrderByOrderId(int orderId){
        return (OrdersEntity) ordersRepository.findOrderById();
    }

    //Find all order in current month
    public List<OrdersEntity> findOrderByCurrentMonth(){
        return (List<OrdersEntity>) ordersRepository.findOrderByCurrentMonth();
    }

    //List of total buy greater than
    public List<OrdersEntity> findOrderWhichTotalBuyGreaterOrEqualThan(double money){
        return (List<OrdersEntity>) ordersRepository.findOrderWithTotalBuyGreaterOrEqualThan(money);
    }

    //List of customer that purchase a product
    public List<OrdersEntity> findOrderWhichPurchaseProduct(String productName){
        return (List<OrdersEntity>) ordersRepository.findOrderWhichPurchaseProduct(productName);
    }

}
