package entities.products;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "optiongroups")
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionGroupID;

    @NaturalId
    @Column(name = "optiongroupname")
    private String optionGroupName;

    public OptionGroup() {
    }

    public int getOptionGroupID() {
        return optionGroupID;
    }

    public void setOptionGroupID(int optionGroupID) {
        this.optionGroupID = optionGroupID;
    }

    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionGroup)) return false;
        OptionGroup that = (OptionGroup) o;
        return getOptionGroupID() == that.getOptionGroupID() &&
                Objects.equals(getOptionGroupName(), that.getOptionGroupName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOptionGroupID(), getOptionGroupName());
    }

    @Override
    public String toString() {
        return "OptionGroup{" +
                "optionGroupID=" + optionGroupID +
                ", optionGroupName='" + optionGroupName + '\'' +
                '}';
    }
}
