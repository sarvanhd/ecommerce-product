package com.sarvan.userservice.dao;

import com.sarvan.userservice.entities.Users;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long>, PagingAndSortingRepository<Users, Long> {
    long count();
    List<Users> findAll();
    List<Users> findAll(Sort sort);
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findById(Integer primaryKey);

}
