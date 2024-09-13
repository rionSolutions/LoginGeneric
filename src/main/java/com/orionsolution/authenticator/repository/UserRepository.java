package com.orionsolution.authenticator.repository;

import com.orionsolution.authenticator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByDocument(String document);




}
