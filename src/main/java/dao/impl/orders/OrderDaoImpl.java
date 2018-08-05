package dao.impl.orders;

import dao.orders.OrderDao;
import entities.orders.Order;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private EntityManager entityManager;

    {
        entityManager = Persistence.createEntityManagerFactory("Entities").createEntityManager();
    }

    @Override
    public Order getOrder(int id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Order createOrder(Order order) {
        if (order != null){
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
        }
        return order;
    }

    @Override
    public List<Order> getShippedOrders() {
        String query = "SELECT o FROM Order o WHERE o.orderShipped = true";
        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Order> getNonShippedOrders() {
        String query = "SELECT o FROM Order o WHERE o.orderShipped = false ";
        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Order> getOrdersInDateRange(Timestamp from, Timestamp to) {
        String query = "SELECT o FROM Order o where o.orderDate BETWEEN :fromDate AND :toDate";

        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        typedQuery.setParameter("fromDate", from);
        typedQuery.setParameter("toDate", to);
        return typedQuery.getResultList();
    }

    @Override
    public List<Order> getOrdersByPhone(String number) {
        String query = "SELECT o FROM Order o WHERE o.orderPhone = :number ";
        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        typedQuery.setParameter("number", number);
        return typedQuery.getResultList();
    }
}
