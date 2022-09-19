package com.vikas.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikas.blog.entity.Category;
import com.vikas.blog.exceptions.ResourceNotFoundException;
import com.vikas.blog.payloads.CategoryDto;
import com.vikas.blog.repositories.CategoryRepo;
import com.vikas.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	public ModelMapper modelmapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelmapper.map(categoryDto, Category.class);
		Category addedcat=this.categoryRepo.save(cat);
		return this.modelmapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("category", "category Id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatecat=this.categoryRepo.save(cat);
		
		return this.modelmapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));
		
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelmapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return catDtos;
	}

}
