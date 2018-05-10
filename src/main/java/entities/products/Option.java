package entities.products;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionID;

    @NaturalId
    @Column(name = "OptionName")
    private String optionName;


    @NaturalId
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OptionGroupID")
    private OptionGroup optionGroup;

    public Option() {
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public OptionGroup getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;
        Option option = (Option) o;
        return getOptionID() == option.getOptionID() &&
                Objects.equals(getOptionName(), option.getOptionName()) &&
                Objects.equals(getOptionGroup(), option.getOptionGroup());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOptionID(), getOptionName(), getOptionGroup());
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionID=" + optionID +
                ", optionName='" + optionName + '\'' +
                ", optionGroup=" + optionGroup +
                '}';
    }
}
