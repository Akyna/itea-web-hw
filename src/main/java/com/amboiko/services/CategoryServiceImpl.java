package com.amboiko.services;

import com.amboiko.dao.CategoryDAO;
import com.amboiko.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }
}
