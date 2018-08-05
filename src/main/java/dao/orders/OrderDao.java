package dao.orders;

import dao.impl.orders.OrderDaoImpl;
import entities.orders.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {

    default OrderDao getInstance(){
        return new OrderDaoImpl();
    }

    Order getOrder(int id);

    Order createOrder(Order order);

    List<Order> getShippedOrders();

    List<Order> getNonShippedOrders();

    List<Order> getOrdersInDateRange(Timestamp from, Timestamp to);

    List<Order> getOrdersByPhone(String number);
}
