package pl.shonsu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birth_place")
    private String birthPlace;
    @Column(name = "birth_day")
    private LocalDate brithDay;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    private String pesel;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Customer customer;

    public CustomerDetails() {
    }

    public CustomerDetails(Long id, String birthPlace, LocalDate brithDay, String fatherName, String motherName, String pesel, Customer customer) {
        this.id = id;
        this.birthPlace = birthPlace;
        this.brithDay = brithDay;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.pesel = pesel;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public LocalDate getBrithDay() {
        return brithDay;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getPesel() {
        return pesel;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "id=" + id +
                ", birthPlace='" + birthPlace + '\'' +
                ", brithDay=" + brithDay +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}
