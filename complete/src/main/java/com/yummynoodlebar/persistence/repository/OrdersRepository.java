package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.Order;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends CrudRepository<Order, String> {
	  Order findById(String key);

	  @Query(value = "select no.* from NOODLE_ORDERS no where no.ORDER_ID in (select ID from ORDER_ORDER_ITEMS where MENU_ID = :menuId)", nativeQuery = true)
	  List<Order> findOrdersContaining(@Param("menuId") String menuId);
}


/*public interface OrdersRepository {

  Order save(Order order);

  void delete(UUID key);

  Order findById(UUID key);

  Iterable<Order> findAll();
}*/
