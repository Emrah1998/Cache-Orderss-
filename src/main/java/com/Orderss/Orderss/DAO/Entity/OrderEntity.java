package com.Orderss.Orderss.DAO.Entity;

import com.Orderss.Orderss.MODEL.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of="id")
@Table(name="orderss")
public class OrderEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String name;
private String surname;
private int age;
@Enumerated(EnumType.STRING)
    private OrderStatus status;
}
