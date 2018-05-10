package entities.orders;

import entities.products.Product;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailID;

    @ManyToOne
    @JoinColumn(name = "DetailProductID",
            foreignKey = @ForeignKey(name = "FK_DetailProductID"))
    private Product product;

    @Column(name = "DetailPrice")
    private double detailPrice;

    @Column(name = "DetailQuantity")
    private int detailQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DetailOrderID")
    private Order detailOrderID;
}
