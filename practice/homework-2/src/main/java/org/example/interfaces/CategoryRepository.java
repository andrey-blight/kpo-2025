package org.example.interfaces;

import org.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// interface to work with database using ORM (JpaRepository already implement save, delete and get requests)
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
