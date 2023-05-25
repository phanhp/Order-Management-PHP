package repository;

import entity.OrderDetailsEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity, Integer> {
    @Query(value = "select * from order_details where order_details_id = ?1", nativeQuery = true)
    OrderDetailsEntity findOrderDetailsByOrderDetailsId(int orderDetailsId);
    @Query(value = "select * from order_details where order_id = ?1",nativeQuery = true)
    List<OrderDetailsEntity> findOrderDetailsByOrderId(int orderId);
    @Query(value = "select * from order_details where order_id = ?1 and product_name = ?2 and unit_price = ?3", nativeQuery = true)
    OrderDetailsEntity findOrderDetailsByOrderIdAndProductNameAndUnitPrice(int orderId, String productName, double unitPrice);

}
