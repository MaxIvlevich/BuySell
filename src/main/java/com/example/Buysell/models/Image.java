package com.example.Buysell.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "id")
    private Long id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "originalFilename")
    private String originalFilename;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "isPreviewImage")
    private Boolean isPreviewImage;
    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY) //изменил каскад тайп
    private Product product;


//    @Override
//    public String toString() {
//        return "Image{" +
//                "id=" + id +
//                ", Name='" + Name + '\'' +
//                ", originalFilename='" + originalFilename + '\'' +
//                ", size=" + size +
//                ", contentType='" + contentType + '\'' +
//                ", isPreviewImage=" + isPreviewImage +
//                ", bytes=" + Arrays.toString(bytes) +
//                ", product=" + product +
//                '}';
//    }
}


