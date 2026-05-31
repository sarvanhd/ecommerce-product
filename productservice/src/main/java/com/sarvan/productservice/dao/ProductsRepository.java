package com.sarvan.productservice.dao;

import com.sarvan.productservice.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long>, PagingAndSortingRepository<Products, Long> {
    long count();
    List<Products> findAll();
    List<Products> findAll(Sort sort);
    Page<Products> findAll(Pageable pageable);
    Optional<Products> findById(Integer primaryKey);

}
