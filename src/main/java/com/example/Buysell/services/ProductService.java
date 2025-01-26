package com.example.Buysell.services;

import com.example.Buysell.models.Image;
import com.example.Buysell.models.Product;
import com.example.Buysell.models.User;
import com.example.Buysell.repositories.ProductRepository;
import com.example.Buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts(String title) {

        if (title != null) {
            return productRepository.findByTitle(title);
        }
        return (List<Product>) productRepository.findAll();
    }

    public void saveProduct(Principal principal ,Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        product.setUser(getUserByPrincipal(principal));
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
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        Product productFromDb = productRepository.save(product);
        product.setPreviewImageId(productFromDb.getImages().get(0).getId());

        log.info("Saving new Product. Title: {}; Author email : {}", product.getTitle(),product.getUser().getEmail());

        productRepository.save(product);

        }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }

        return userRepository.findByEmail(principal.getName());
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
        @Transactional
        public void deleteProduct (Long id){

        log.info("Delete product {}", productRepository.findById(id));
              productRepository.deleteById(id);

              if(productRepository.findById(id).isPresent()){
                 log.info("Не получилось удалить товар {}", productRepository.findById(id));
             }else {
                 log.info("Товар удален");
              }
        }
        public Product getProductById (Long id){
        return productRepository.findById(id).orElse(null);
        }
    }
