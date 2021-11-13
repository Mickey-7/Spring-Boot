package com.example.bulkdeletejpah2.service;

import com.example.bulkdeletejpah2.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@Service
//The service class is responsible for processing the business of the application.
//I have included the EntityManager instance from the persistent context which will be used for building CriteriaDelete query.
//An example for bulk delete may be, you want to delete all data which are older than 90 days.
//In this example, I am going to delete products.
//Though this is a simple example and in your real project you may have thousands or lakhs of records for which you want to perform bulk delete operations.
//I am updating the product’s price based on product’s name.
//@Transactional annotation from Spring framework is required on the method that performs bulk delete operations, otherwise you may get the following exception:
//javax.persistence.TransactionRequiredException: Executing an update/delete query
public class DeleteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteBulkProducts(final String productName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);
        Root<Product> root = criteriaDelete.from(Product.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("name"),productName));
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

}
