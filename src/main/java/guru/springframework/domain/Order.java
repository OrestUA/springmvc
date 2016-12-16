package guru.springframework.domain;

import guru.springframework.config.LocalDateTimeAttributeConverter;
import guru.springframework.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSkakun on 11/8/2016.
 */
@Entity
public class Order extends AbstractDomainClass {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    //@Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateShipped;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(LocalDateTime dateShipped) {
        this.dateShipped = dateShipped;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }
}