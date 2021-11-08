package com.example.bulkupdatejpah2.service;

import com.example.bulkupdatejpah2.model.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Service
//The service class is responsible for processing the business of the application.
//I have included the EntityManager instance from the persistent context which will be used for building CriteriaUpdate query.
//An example for bulk update may be, you want to give 15% hike to all employees.
//In this example, I am going to update the price of a product.
//Though this is a simple example and in your real project you may have thousands or lakhs of records for which you want to perform bulk update operations.
//I am updating the product’s price based on product’s name.
//@Transactional annotation from Spring framework is required otherwise you may get the following exception:
//javax.persistence.TransactionRequiredException: Executing an update/delete query
public class UpdateService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateBulkProducts(final String productName, final double price){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);
        Root<Product> root = criteriaUpdate.from(Product.class);
        criteriaUpdate.set("price",price);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("name"),productName));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
