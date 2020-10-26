package appMain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    private UUID customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    @JsonFormat(pattern = "MMM dd, yyyy")
    private Date registrationDate;

    public Customer(){
    }

    public Customer(UUID customerId, String firstName, String lastName, int age) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.registrationDate = new Date(System.currentTimeMillis());
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID id) {
        this.customerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate){
        this.registrationDate = registrationDate;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
