package com.mvcapp.shawarma.service;

import com.mvcapp.shawarma.model.entity.CategoryEntity;
import com.mvcapp.shawarma.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    public CategoryEntity getCategory(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Object is null");
        }
        if (!categoryRepo.existsById(id)) {
            throw new Exception("This category does not exist");
        }

        return categoryRepo.findById(id).get();
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepo.findAll();
    }

    //Only for admins
    public CategoryEntity addCategory(String name) throws Exception {
        if (name == null || name.equals("")) {
            throw new Exception("Object is null");
        }

        var category = new CategoryEntity();
        category.setName(name);

        return categoryRepo.save(category);
    }
    //Only for ADMIN
    public void deleteCategory(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Object is null");
        }
        if (!categoryRepo.existsById(id)) {
            throw new Exception("This category does not exist");
        }
        var category = categoryRepo.findById(id);

        categoryRepo.delete(category.get());
    }

}
