package ru.sapteh.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String adres;

    @OneToMany(mappedBy = "warehouse",fetch = FetchType.EAGER)
    private Set<Product> products;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", adres='" + adres + '\'' +
                '}';
    }
}
