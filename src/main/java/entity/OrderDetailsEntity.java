package entity;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private int orderDetailsId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    @ManyToOne
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JoinColumn(name = "order_id")
    private OrdersEntity orders;

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int id) {
        this.orderDetailsId = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public OrderDetailsEntity() {

    }

    public OrderDetailsEntity(String productName, int quantity, double unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderDetailsEntity(int orderDetailsId, String productName, int quantity, double unitPrice, OrdersEntity orders) {
        this.orderDetailsId = orderDetailsId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "orderDetailId=" + orderDetailsId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice+"___";
    }
}
