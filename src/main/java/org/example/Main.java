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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(OrderJpa.class);
    static OrdersRepository ordersRepository = (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static void main(String[] args) {
        Main action = new Main();
//        action.createTestData();
//        action.createNewOrder(LocalDate.now(), "Customer-new", "Customer-new-address", "product08", 2, 124.93);
//        action.findAllOrder();
//        action.findOrderByOrderId(6);
//        action.findOrderByCurrentMonth();
//        action.findOrderWhichTotalBuyGreaterOrEqualThan(1300);
        action.findOrderWhichPurchaseProduct("product04");
    }

    private static OrderDetailsEntity createNewOrderDetails(String productName, int quantity, double unitPrice) {
        return new OrderDetailsEntity(productName, quantity, unitPrice);
    }

    private static OrdersEntity createNewOrder(LocalDate orderDate, String customerName, String customerAddress) {
        return new OrdersEntity(orderDate, customerName, customerAddress);
    }

    private void createTestData() {
        OrdersEntity order1 = createNewOrder(LocalDate.parse("2022-08-17"), "Customer001", "Customer001-Address");
        ordersRepository.save(order1);
        OrderDetailsEntity orderDetails1Of1 = createNewOrderDetails("product01", 1, 199.83);
        OrderDetailsEntity orderDetails2Of1 = createNewOrderDetails("product02", 2, 122.24);
        OrderDetailsEntity orderDetails3Of1 = createNewOrderDetails("product03", 1, 88.33);
        orderDetails1Of1.setOrders(order1);
        orderDetails2Of1.setOrders(order1);
        orderDetails3Of1.setOrders(order1);
        orderDetailsRepository.save(orderDetails1Of1);
        orderDetailsRepository.save(orderDetails2Of1);
        orderDetailsRepository.save(orderDetails3Of1);

        OrdersEntity order2 = createNewOrder(LocalDate.parse("2022-11-14"), "Customer002", "Customer002-Address");
        ordersRepository.save(order2);
        OrderDetailsEntity orderDetails1Of2 = createNewOrderDetails("product01", 4, 199.83);
        OrderDetailsEntity orderDetails2Of2 = createNewOrderDetails("product04", 3, 422.46);
        OrderDetailsEntity orderDetails3Of2 = createNewOrderDetails("product05", 2, 328.87);
        orderDetails1Of2.setOrders(order2);
        orderDetails2Of2.setOrders(order2);
        orderDetails3Of2.setOrders(order2);
        orderDetailsRepository.save(orderDetails1Of2);
        orderDetailsRepository.save(orderDetails2Of2);
        orderDetailsRepository.save(orderDetails3Of2);

        OrdersEntity order3 = createNewOrder(LocalDate.parse("2023-01-21"), "Customer003", "Customer003-Address");
        ordersRepository.save(order3);
        OrderDetailsEntity orderDetails1Of3 = createNewOrderDetails("product01", 1, 199.83);
        OrderDetailsEntity orderDetails2Of3 = createNewOrderDetails("product05", 3, 328.87);
        OrderDetailsEntity orderDetails3Of3 = createNewOrderDetails("product03", 2, 88.83);
        orderDetails1Of3.setOrders(order3);
        orderDetails2Of3.setOrders(order3);
        orderDetails3Of3.setOrders(order3);
        orderDetailsRepository.save(orderDetails1Of3);
        orderDetailsRepository.save(orderDetails2Of3);
        orderDetailsRepository.save(orderDetails3Of3);

        OrdersEntity order4 = createNewOrder(LocalDate.parse("2023-04-08"), "Customer001", "Customer001-Address");
        ordersRepository.save(order4);
        OrderDetailsEntity orderDetails1Of4 = createNewOrderDetails("product01", 2, 199.83);
        OrderDetailsEntity orderDetails2Of4 = createNewOrderDetails("product02", 1, 122.83);
        OrderDetailsEntity orderDetails3Of4 = createNewOrderDetails("product04", 2, 422.46);
        orderDetails1Of4.setOrders(order4);
        orderDetails2Of4.setOrders(order4);
        orderDetails3Of4.setOrders(order4);
        orderDetailsRepository.save(orderDetails1Of4);
        orderDetailsRepository.save(orderDetails2Of4);
        orderDetailsRepository.save(orderDetails3Of4);

        OrdersEntity orderNow = createNewOrder(LocalDate.now(), "Customer-now", "Customer-now-address");
        ordersRepository.save(orderNow);
        OrderDetailsEntity orderDetails1OfNow = createNewOrderDetails("product01", 3, 199.83);
        OrderDetailsEntity orderDetails2OfNow = createNewOrderDetails("product05", 1, 328.87);
        OrderDetailsEntity orderDetails3OfNow = createNewOrderDetails("product06", 3, 22.46);
        orderDetails1OfNow.setOrders(orderNow);
        orderDetails2OfNow.setOrders(orderNow);
        orderDetails3OfNow.setOrders(orderNow);
        orderDetailsRepository.save(orderDetails1OfNow);
        orderDetailsRepository.save(orderDetails2OfNow);
        orderDetailsRepository.save(orderDetails3OfNow);
    }

    public static void updateOrderDetailsQuantity(int quantity, OrderDetailsEntity orderDetails) {
        orderDetails.setQuantity(quantity);
        orderDetailsRepository.save(orderDetails);

    }

    public OrderDetailsEntity findOrderDetailsByOrderDetailsId(int orderDetailsId) {
        return (OrderDetailsEntity) orderDetailsRepository.findOrderDetailsByOrderDetailsId(orderDetailsId);
    }

    public int findOrderIdOfOrderDetails(OrderDetailsEntity orderDetails) {
        return orderDetails.getOrders().getOrderId();
    }

    public boolean isOrderRepeat(OrdersEntity order1, OrdersEntity order2) {
        if (order1.getOrderDate().equals(order2.getOrderDate()) && order1.getCustomerName().equalsIgnoreCase(order2.getCustomerName())) {
            return true;
        }
        return false;
    }

    public boolean isOrderDetailsRepeat(OrderDetailsEntity orderDetails1, OrderDetailsEntity orderDetails2) {
        if (orderDetails1.getUnitPrice() == orderDetails2.getUnitPrice() && orderDetails1.getProductName().equalsIgnoreCase(orderDetails2.getProductName())) {
            return true;
        }
        return false;
    }

    //Allow for users to create new order
    public void createNewOrder(LocalDate orderDate, String customerName, String customerAddress, String productName, int quantity, double unitPrice) {
        Main action = new Main();
        List<OrdersEntity> allOrderList = (List<OrdersEntity>) ordersRepository.findAllOrders();

        OrdersEntity order = new OrdersEntity(orderDate, customerName, customerAddress);
        OrderDetailsEntity orderDetails = new OrderDetailsEntity(productName, quantity, unitPrice);

        int count = 0;
        for (int i = 0; i < allOrderList.size(); i++) {
            if (isOrderRepeat(order, allOrderList.get(i)) == true) {
                System.out.println("The order's already set in the past");

                List<OrderDetailsEntity> orderDetailsList = (List<OrderDetailsEntity>) orderDetailsRepository.findOrderDetailsByOrderId(allOrderList.get(i).getOrderId());
                int count1 = 0;
                for (int j = 0; j < orderDetailsList.size(); j++) {
                    if (isOrderDetailsRepeat(orderDetails, orderDetailsList.get(j))==true) {
                        int oldQuantity = orderDetailsList.get(j).getQuantity();
                        if (quantity > 0) {
                            updateOrderDetailsQuantity((oldQuantity + quantity), orderDetailsList.get(j));
                            System.out.println(allOrderList.get(i).getCustomerName() + " purchase " + quantity + " more of " + productName);
                            System.out.println("New order detail is: Product-name: " + productName + ", Quantity: " + (oldQuantity + quantity) + ", Unit-price: " + unitPrice);
                        } else if (quantity <= 0 && quantity + oldQuantity >= 0) {
                            updateOrderDetailsQuantity((oldQuantity + quantity), orderDetailsList.get(j));
                            System.out.println(allOrderList.get(i).getCustomerName() + " return " + (quantity * -1) + " of " + productName);
                            System.out.println("New order detail is: Product-name: " + productName + ", Quantity: " + (oldQuantity + quantity) + ", Unit-price: " + unitPrice);
                        } else {
                            System.out.println("Invalid return item");
                        }
                        count1++;
                        break;
                    }
                }

                if (count1 == 0) {
                    if (quantity > 0) {
                        orderDetails.setOrders(allOrderList.get(i));
                        orderDetailsRepository.save(orderDetails);
                        System.out.println("New order detail with: Product-name: " + productName + ", Quantity: " + quantity + ", Unit-price: " + unitPrice + " is saved");
                    } else {
                        System.out.println("Invalid quantity input");
                    }
                }

                count++;
                break;
            }
        }

        if (count == 0) {
            if (quantity > 0) {
                int count1 = 0;
                ordersRepository.save(order);
                orderDetails.setOrders(order);
                orderDetailsRepository.save(orderDetails);
                System.out.println("New order set successfully");
                System.out.println("New order with: Customer-name: " + customerName + ", Customer-address: " + customerAddress + ", Date: " + orderDate.toString());
                System.out.println("New order detail with: Product-name: " + productName + " Quantity: " + quantity + " Unit-price: " + unitPrice + " is saved");
            } else {
                System.out.println("Invalid quantity input");
            }
        }
    }

    //Find all orders
    public List<OrdersEntity> findAllOrder() {
        List<OrdersEntity> ordersList = (List<OrdersEntity>) ordersRepository.findAllOrders();
        System.out.println("There are " + ordersList.size() + " orders in the list");
        if (ordersList.size() != 0) {
            System.out.println("Orders List Detail: ");
            for (int i = 0; i < ordersList.size(); i++) {
                System.out.println(ordersList.get(i).toString());
            }
        }
        return ordersList;
    }

    //Find an order by order_id
    public OrdersEntity findOrderByOrderId(int orderId) {
        try {
            OrdersEntity order = (OrdersEntity) ordersRepository.findOrderById(orderId);
            System.out.println("The selected order is:");
            System.out.println("   " + order.toString());
            return order;
        } catch (Exception e) {
            System.out.println("   Can not find selected order");
            return null;
        }
    }

    //Find all order in current month
    public List<OrdersEntity> findOrderByCurrentMonth() {
        List<OrdersEntity> ordersList = (List<OrdersEntity>) ordersRepository.findOrderByCurrentMonth();
        System.out.println("There are " + ordersList.size() + " orders in the list");
        if (ordersList.size() != 0) {
            System.out.println("Orders List Detail: ");
            for (int i = 0; i < ordersList.size(); i++) {
                System.out.println(ordersList.get(i).toString());
            }
        }
        return ordersList;
    }

    //List of total buy greater than
    public List<OrdersEntity> findOrderWhichTotalBuyGreaterOrEqualThan(double money) {
        List<OrdersEntity> ordersList = (List<OrdersEntity>) ordersRepository.findOrderWithTotalBuyGreaterOrEqualThan(money);
        System.out.println("There are " + ordersList.size() + " orders in the list");
        if (ordersList.size() != 0) {
            System.out.println("Orders List Detail: ");
            for (int i = 0; i < ordersList.size(); i++) {
                System.out.println(ordersList.get(i).toString());
            }
        }
        return ordersList;
    }

    //List of customer that purchase a product
    public List<OrdersEntity> findOrderWhichPurchaseProduct(String productName) {
        List<OrdersEntity> ordersList = (List<OrdersEntity>) ordersRepository.findOrderWhichPurchaseProduct(productName);
        System.out.println("There are " + ordersList.size() + " orders in the list");
        if (ordersList.size() != 0) {
            System.out.println("Orders List Detail: ");
            for (int i = 0; i < ordersList.size(); i++) {
                System.out.println(ordersList.get(i).toString());
            }
        }
        return ordersList;
    }

}
