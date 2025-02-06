package com.example.Buysell.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private long previewImageId;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)//CascadeType.REFRESH {CascadeType.REFRESH,CascadeType.REMOVE.,CascadeType.MERGE}
    @JoinColumn
    private User user;

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
//                ", images=" + images.getClass().toString() +
                ", previewImageId=" + previewImageId +
//                ", user" + user.getClass().toString() +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }
}
