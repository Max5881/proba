package ru.sapteh.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Product {

    public Product(String title, Warehouse warehouse, Category category) {
        this.title = title;
        this.warehouse = warehouse;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
