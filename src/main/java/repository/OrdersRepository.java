package repository;

import entity.OrdersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {
    @Query (value = "Select * from orders", nativeQuery = true)
    List<OrdersEntity> findAllOrders();

    @Query (value = "Select * from orders where order_id = ?1", nativeQuery = true)
    OrdersEntity findOrderById();

    @Query (value = "Select * from orders where month(order_date) = month(now()) and year(order_date) = year(now())",nativeQuery = true)
    List<OrdersEntity> findOrderByCurrentMonth();

    @Query (value = "Select * from orders o where (Select sum(quantity*unit_price) from order_details od where o.order_id = od.order_id)>=?1",nativeQuery = true)
    List<OrdersEntity> findOrderWithTotalBuyGreaterOrEqualThan(double money);

    @Query (value = "Select * from orders o join order_details od where product_name like ?1 group by od.order_id", nativeQuery = true)
    List<OrdersEntity> findOrderWhichPurchaseProduct(String productName);
}
