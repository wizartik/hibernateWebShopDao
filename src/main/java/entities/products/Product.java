package entities.products;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products", schema = "webshop_db")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Basic
    @NaturalId
    @Column(name = "ProductSKU", length = 50)
    private String productSku;

    @Basic
    @NaturalId
    @Column(name = "ProductName", length = 100)
    private String productName;

    @Basic
    @Column(name = "ProductPrice")
    private double productPrice;

    @Basic
    @Column(name = "ProductWeight")
    private double productWeight;

    @Basic
    @Column(name = "ProductCartDesc", length = 250)
    private String productCartDesc;

    @Basic
    @Column(name = "ProductShortDesc", length = 1000)
    private String productShortDesc;

    @Basic
    @Column(name = "ProductLongDesc")
    private String productLongDesc;

    @Basic
    @Column(name = "ProductThumb", length = 100)
    private String productThumb;

    @Basic
    @Column(name = "ProductImage", length = 100)
    private String productImage;

    @Basic
    @Column(name = "ProductUpdateDate")
    private Timestamp productUpdateDate;

    @Basic
    @Column(name = "ProductStock")
    private Double productStock;

    @Basic
    @Column(name = "ProductLive")
    private boolean productLive;

    @Basic
    @Column(name = "ProductUnlimited")
    private boolean productUnlimited;

    @Basic
    @Column(name = "ProductLocation", length = 250)
    private String productLocation;

    @NaturalId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductCategoryID",
            foreignKey = @ForeignKey(name = "FK_ProductCategoryID"))
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "productoptions",
            joinColumns = @JoinColumn(name = "OptionID"),
            inverseJoinColumns = @JoinColumn(name = "ProductID"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Option> options;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductCartDesc() {
        return productCartDesc;
    }

    public void setProductCartDesc(String productCartDesc) {
        this.productCartDesc = productCartDesc;
    }

    public String getProductShortDesc() {
        return productShortDesc;
    }

    public void setProductShortDesc(String productShortDesc) {
        this.productShortDesc = productShortDesc;
    }

    public String getProductLongDesc() {
        return productLongDesc;
    }

    public void setProductLongDesc(String productLongDesc) {
        this.productLongDesc = productLongDesc;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Timestamp getProductUpdateDate() {
        return productUpdateDate;
    }

    public void setProductUpdateDate(Timestamp productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }

    public Double getProductStock() {
        return productStock;
    }

    public void setProductStock(Double productStock) {
        this.productStock = productStock;
    }

    public boolean isProductLive() {
        return productLive;
    }

    public void setProductLive(boolean productLive) {
        this.productLive = productLive;
    }

    public boolean isProductUnlimited() {
        return productUnlimited;
    }

    public void setProductUnlimited(boolean productUnlimited) {
        this.productUnlimited = productUnlimited;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId() &&
                Double.compare(product.getProductPrice(), getProductPrice()) == 0 &&
                Double.compare(product.getProductWeight(), getProductWeight()) == 0 &&
                isProductLive() == product.isProductLive() &&
                isProductUnlimited() == product.isProductUnlimited() &&
                Objects.equals(getProductSku(), product.getProductSku()) &&
                Objects.equals(getProductName(), product.getProductName()) &&
                Objects.equals(getProductCartDesc(), product.getProductCartDesc()) &&
                Objects.equals(getProductShortDesc(), product.getProductShortDesc()) &&
                Objects.equals(getProductLongDesc(), product.getProductLongDesc()) &&
                Objects.equals(getProductThumb(), product.getProductThumb()) &&
                Objects.equals(getProductImage(), product.getProductImage()) &&
                Objects.equals(getProductUpdateDate(), product.getProductUpdateDate()) &&
                Objects.equals(getProductStock(), product.getProductStock()) &&
                Objects.equals(getProductLocation(), product.getProductLocation()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getOptions(), product.getOptions());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProductId(), getProductSku(), getProductName(), getProductPrice(), getProductWeight(), getProductCartDesc(), getProductShortDesc(), getProductLongDesc(), getProductThumb(), getProductImage(), getProductUpdateDate(), getProductStock(), isProductLive(), isProductUnlimited(), getProductLocation(), getCategory(), getOptions());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productSku='" + productSku + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeight=" + productWeight +
                ", productCartDesc='" + productCartDesc + '\'' +
                ", productShortDesc='" + productShortDesc + '\'' +
                ", productLongDesc='" + productLongDesc + '\'' +
                ", productThumb='" + productThumb + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productUpdateDate=" + productUpdateDate +
                ", productStock=" + productStock +
                ", productLive=" + productLive +
                ", productUnlimited=" + productUnlimited +
                ", productLocation='" + productLocation + '\'' +
                ", category=" + category +
                ", options=" + options +
                '}';
    }
}
