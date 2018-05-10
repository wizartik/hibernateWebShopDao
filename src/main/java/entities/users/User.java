package entities.users;

import entities.Address;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "webshop_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Basic
    @NaturalId
    @Column(name = "UserEmail", unique = true)
    private String userEmail;

    @Basic
    @Column(name = "UserPassword")
    private String userPassword;

    @Basic
    @Column(name = "UserFirstName")
    private String userFirstName;

    @Basic
    @Column(name = "UserLastName")
    private String userLastName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "UserAddress",
            foreignKey = @ForeignKey(name = "FK_UserAddressID"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Address address;

    @Basic
    @Column(name = "UserEmailVerified")
    private Boolean userEmailVerified;

    @Basic
    @Column(name = "UserRegistrationDate")
    private Timestamp userRegistrationDate;

    @Basic
    @Column(name = "UserVerificationCode")
    private String userVerificationCode;

    @Basic
    @NaturalId(mutable = true)
    @Column(name = "UserIP")
    private String userIp;

    @Basic
    @NaturalId(mutable = true)
    @Column(name = "UserPhone")
    private String userPhone;

    @Basic
    @Column(name = "UserFax")
    private String userFax;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getUserEmailVerified() {
        return userEmailVerified;
    }

    public void setUserEmailVerified(Boolean userEmailVerified) {
        this.userEmailVerified = userEmailVerified;
    }

    public Timestamp getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public void setUserRegistrationDate(Timestamp userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
    }

    public String getUserVerificationCode() {
        return userVerificationCode;
    }

    public void setUserVerificationCode(String userVerificationCode) {
        this.userVerificationCode = userVerificationCode;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserFax() {
        return userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getUserEmail(), user.getUserEmail()) &&
                Objects.equals(getUserPassword(), user.getUserPassword()) &&
                Objects.equals(getUserFirstName(), user.getUserFirstName()) &&
                Objects.equals(getUserLastName(), user.getUserLastName()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                Objects.equals(getUserEmailVerified(), user.getUserEmailVerified()) &&
                Objects.equals(getUserRegistrationDate(), user.getUserRegistrationDate()) &&
                Objects.equals(getUserVerificationCode(), user.getUserVerificationCode()) &&
                Objects.equals(getUserIp(), user.getUserIp()) &&
                Objects.equals(getUserPhone(), user.getUserPhone()) &&
                Objects.equals(getUserFax(), user.getUserFax());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getUserEmail(), getUserPassword(), getUserFirstName(), getUserLastName(), getAddress(), getUserEmailVerified(), getUserRegistrationDate(), getUserVerificationCode(), getUserIp(), getUserPhone(), getUserFax());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", address=" + address +
                ", userEmailVerified=" + userEmailVerified +
                ", userRegistrationDate=" + userRegistrationDate +
                ", userVerificationCode='" + userVerificationCode + '\'' +
                ", userIp='" + userIp + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userFax='" + userFax + '\'' +
                '}';
    }
}
