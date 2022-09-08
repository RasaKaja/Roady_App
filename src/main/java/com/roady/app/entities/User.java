package com.roady.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 64)
    private String lastName;
    @Column(nullable = false, length = 20)
    private String phoneNumber;
    @Column(columnDefinition = "double default 0")
    private Double avrRating;
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp registeredAt;
    // this will by default create Foreign key column in User class, and default name will be "car_id"
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    @JoinColumn(name = "carId", nullable = true)
    private Car car;

//public User(String email, String password){
//    this.email=email;
//    this.password=password;
//}

}