package com.example.Buysell.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id ;
    @Column(name = "title")
    private String title;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "city")
    private String city;
    @Column(name = "author")
    private String author;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "product")

    private List<Image> images =new ArrayList<>();

    private long previewImageId;

    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();

    }

    public void addImageToProduct(Image image){
        image.setProduct(this);
        images.add(image);

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                ", author='" + author + '\'' +
                ", images=" + images.getClass().toString() +
                ", previewImageId=" + previewImageId +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }
}
