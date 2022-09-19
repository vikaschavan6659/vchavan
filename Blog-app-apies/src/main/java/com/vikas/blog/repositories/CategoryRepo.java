package com.vikas.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikas.blog.entity.Category;

public interface CategoryRepo extends JpaRepository< Category , Integer> {

}
