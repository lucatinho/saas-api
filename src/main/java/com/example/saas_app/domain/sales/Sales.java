package com.example.saas_app.domain.sales;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "sales")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Number desconto;
    private Number valorTotal;
    private LocalDateTime dataVenda;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist() {
        this.dataVenda = LocalDateTime.now();
    }
}
