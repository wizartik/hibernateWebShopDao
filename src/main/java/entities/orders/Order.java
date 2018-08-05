package entities.orders;

import entities.Address;
import entities.users.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders", schema = "webshop_db")
@NamedEntityGraph(name = "entities.orders.Order.orderDetails",
        attributeNodes = @NamedAttributeNode("orderDetails"))
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Basic
    @Column(name = "OrderPrice")
    private double orderPrice;

    @Basic
    @Column(name = "OrderShipName")
    private String orderShipName;

    @Basic
    @NaturalId
    @Column(name = "OrderPhone")
    private String orderPhone;

    @Basic
    @Column(name = "OrderFax")
    private String orderFax;

    @Basic
    @Column(name = "OrderShipping")
    private double orderShipping;

    @Basic
    @Column(name = "OrderTax")
    private double orderTax;

    @Basic
    @NaturalId
    @Column(name = "OrderEmail")
    private String orderEmail;

    @Basic
    @NaturalId
    @Column(name = "OrderDate")
    private Timestamp orderDate;

    @Basic
    @NaturalId
    @Column(name = "OrderShipped")
    private boolean orderShipped;

    @Basic
    @NaturalId
    @Column(name = "OrderTrackingNumber")
    private String orderTrackingNumber;

    @ManyToOne
    @JoinColumn(name = "OrderUserID")
    private User orderUser;

    @NaturalId
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "OrderAddressID",
            foreignKey = @ForeignKey(name = "FK_OrderAddressID"))
    private Address address;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.REMOVE},
            fetch = FetchType.LAZY, mappedBy = "detailOrderID")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderShipName() {
        return orderShipName;
    }

    public void setOrderShipName(String orderShipName) {
        this.orderShipName = orderShipName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderFax() {
        return orderFax;
    }

    public void setOrderFax(String orderFax) {
        this.orderFax = orderFax;
    }

    public double getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(double orderShipping) {
        this.orderShipping = orderShipping;
    }

    public double getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(double orderTax) {
        this.orderTax = orderTax;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderShipped() {
        return orderShipped;
    }

    public void setOrderShipped(boolean orderShipped) {
        this.orderShipped = orderShipped;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                Double.compare(order.getOrderPrice(), getOrderPrice()) == 0 &&
                Double.compare(order.getOrderShipping(), getOrderShipping()) == 0 &&
                Double.compare(order.getOrderTax(), getOrderTax()) == 0 &&
                isOrderShipped() == order.isOrderShipped() &&
                Objects.equals(getOrderShipName(), order.getOrderShipName()) &&
                Objects.equals(getOrderPhone(), order.getOrderPhone()) &&
                Objects.equals(getOrderFax(), order.getOrderFax()) &&
                Objects.equals(getOrderEmail(), order.getOrderEmail()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getOrderTrackingNumber(), order.getOrderTrackingNumber()) &&
                Objects.equals(getOrderUser(), order.getOrderUser()) &&
                Objects.equals(getAddress(), order.getAddress()) &&
                Objects.equals(getOrderDetails(), order.getOrderDetails());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOrderId(), getOrderPrice(), getOrderShipName(), getOrderPhone(), getOrderFax(), getOrderShipping(), getOrderTax(), getOrderEmail(), getOrderDate(), isOrderShipped(), getOrderTrackingNumber(), getOrderUser(), getAddress(), getOrderDetails());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderPrice=" + orderPrice +
                ", orderShipName='" + orderShipName + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderFax='" + orderFax + '\'' +
                ", orderShipping=" + orderShipping +
                ", orderTax=" + orderTax +
                ", orderEmail='" + orderEmail + '\'' +
                ", orderDate=" + orderDate +
                ", orderShipped=" + orderShipped +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", orderUser=" + orderUser +
                ", address=" + address +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
