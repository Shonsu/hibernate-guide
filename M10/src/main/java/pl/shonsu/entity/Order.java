package pl.shonsu.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

//@NamedEntityGraphs({
//        @NamedEntityGraph(
//                name = "order-rows",
//                attributeNodes = {
//                        @NamedAttributeNode(value = "orderRows", subgraph = "orderRows1"),
//                        @NamedAttributeNode("customer")
//                },
//                subgraphs = @NamedSubgraph(
//                        name = "orderRows1",
//                        attributeNodes = @NamedAttributeNode("product"))
//        ),
//        @NamedEntityGraph(
//                name = "order-and-rows",
//                attributeNodes = @NamedAttributeNode("orderRows")
//        )
//
//})

@Entity
@Table(name = "`order`")
@Cacheable
@org.hibernate.annotations.Cache(region = "order", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private BigDecimal total;

    @OneToMany(orphanRemoval = true)//(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @BatchSize(size = 10)
    private Set<OrderRow> orderRows;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "uuid", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
//        return Objects.equals(created, order.created) &&
//                Objects.equals(customer, order.customer);
        return Objects.equals(uuid, order.uuid);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(created, customer);
        return Objects.hash(uuid);
    }
}
