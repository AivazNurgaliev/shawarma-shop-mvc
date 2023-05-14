package com.mvcapp.shawarma.model.entity;


//import com.mvcapp.shawarma.model.security.Role;
import com.mvcapp.shawarma.model.security.Role;
import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import lombok.*;
>>>>>>> 4efc3ca817021522269eef4510b09998c7fd113f

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.mvcapp.shawarma.model.entity.ProductEntity;
// TODO: 15.04.2023 использовать еще регексы(скорее в дто закинуть в рекордс)
<<<<<<< HEAD
@Data
=======
>>>>>>> 4efc3ca817021522269eef4510b09998c7fd113f
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    // TODO: 15.04.2023 затычка для роли пока что
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;

<<<<<<< HEAD
    @Transient
    private List<ProductEntity> cart = new ArrayList<>();
=======
    @OneToMany(mappedBy = "userCart",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartEntity> userCart;
>>>>>>> 4efc3ca817021522269eef4510b09998c7fd113f
}
