package entities.products;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productcategories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryID;

    @Basic
    @NaturalId
    @Column(name = "categoryName")
    private String categoryName;

    @NaturalId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SuperCategoryID")
    private Category superCategory;

    public Category() {
    }

    public long getCategoryID() {

        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryID() == category.getCategoryID() &&
                Objects.equals(getCategoryName(), category.getCategoryName()) &&
                Objects.equals(getSuperCategory(), category.getSuperCategory());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCategoryID(), getCategoryName(), getSuperCategory());
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", superCategory=" + superCategory +
                '}';
    }
}
