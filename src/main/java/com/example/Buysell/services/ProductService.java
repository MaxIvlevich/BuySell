package com.example.Buysell.services;

import com.example.Buysell.models.Image;
import com.example.Buysell.models.Product;
import com.example.Buysell.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
//    private List<Product> products = new ArrayList<>();
//    private long ID = 1;
//
//    {
//        products.add(new Product(ID++,"Ps5","Simple description",50000,"SPB","Max"));
//        products.add(new Product(ID++,"Iphone15","Simple description",100000,"MSK","OLeg"));
//    }

    public List<Product> listProducts(String title) {

        if (title != null) {
            return productRepository.findByTitle(title);
        }
        return productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setIsPreviewImage(true);
            product.addImageToProduct(image1);
            }

        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
            }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file1);
            product.addImageToProduct(image3);
        }



        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(),product.getAuthor());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        
        productRepository.save(product);

        }

        private Image toImageEntity (MultipartFile file){

            Image image = new Image();
            image.setName(file.getName());
            image.setOriginalFilename(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            try {
                image.setBytes(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return image;

        }

        public void deleteProduct (Long id){
            log.info("Delete product {}", productRepository.findById(id));
            productRepository.deleteById(id);


        }

        public Product getProductById (Long id){

            return productRepository.findById(id).orElse(null);
        }
    }
