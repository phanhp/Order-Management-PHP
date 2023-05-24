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

    @Query (value = "select *, total from orders o join (select *, sum(quantity*unit_price) as total from order_details group by order_id) oc on o.order_id=oc.order_id where total>=?1",nativeQuery = true)
    List<OrdersEntity> findOrderWithTotalBuyGreaterOrEqualThan(double money);

    @Query (value = "select * from orders o join (select * from order_details where product_name like ?1) oc where o.order_id = oc.order_id", nativeQuery = true)
    List<OrdersEntity> findOrderWhichPurchaseProduct(String productName);
}
