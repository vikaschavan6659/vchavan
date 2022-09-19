package com.vikas.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikas.blog.entity.User;

public interface UserRepo extends JpaRepository< User , Integer> {

}
