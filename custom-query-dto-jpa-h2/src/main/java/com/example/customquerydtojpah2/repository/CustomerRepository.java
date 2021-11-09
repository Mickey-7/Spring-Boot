package com.example.customquerydtojpah2.repository;

import com.example.customquerydtojpah2.dto.CustomerDto;
import com.example.customquerydtojpah2.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    //1.1 To fetch/retrieve the list of all customers:
    @Query(value = "SELECT c from Customer c")
    List<Customer> getCustomers();

    //1.2 It returns the list of first name of customers:
    @Query(value = "SELECT c.firstName from Customer c")
    List<String> getCustomerFirstName();

    //1.3 It returns the list of the first name of customers whose country is the Philippines:
    @Query(value = "SELECT c.firstName from " +
            "Customer c WHERE c.country = 'Philippines' ")
    List<String> getCustomerFirstNameWhereCountry();

    //1.4 It returns the list of customers whose country name passed by the user:
    @Query(value = "SELECT c from Customer c WHERE c.country = ?1")
    List<Customer> getCustomerWhereCountry(String country);

    //Alternatively, we can also use the @Param annotation to pass the country value:
    @Query(value = "SELECT c from Customer c WHERE c.country= :country")
    List<Customer> getCustomerWhereCountry1(@Param("country") String country);

    //1.5 (OR clause) It returns the list of customers whose country or the last name passed by user:
    @Query(value = "SELECT c from Customer c WHERE c.country= :country OR c.lastName= :lastName")
    List<Customer> getCustomerWhereCountryOrLastName(@Param("country") String country, @Param("lastName") String lastName);

    //Alternate way;
    @Query(value = "SELECT c from Customer c WHERE c.country= ?1 OR c.lastName= ?2")
    List<Customer> getCustomerWhereCountryOrLastName1(String country, String lastName);

    //1.6 Bind directly to DTO–  Yes we can also bind the values directly to DTO returned by the JPQL query.
    //For example, the entity (table) has 5 member variables (columns) and we have a requirement of only 2
    //member variables (columns), in this case, create the new DTO and bind the result as shown below:
    @Query(value = "SELECT " +
            "new com.example.customquerydtojpah2.dto.CustomerDto(" +
            "c.id, c.country) from Customer c")
    List<CustomerDto> getCustomerIdAndCountry();

    //1.7 (JOINs)  In JPQL, we can fetch the data from two or more entities (tables).
    //In shorts, JPQL supports the JOIN clause too.
    //For example, we want only those customer’s records who ordered something.
    @Query(value = "SELECT c FROM Customer c INNER JOIN CustomerOrder co ON c.id=co.customerId")
    List<Customer> getCustomerWhoOrdered();

    //1.8 (JOIN with WHERE clause) Similarly, we can also add the “WHERE” clause with “JOIN” in JPQL.
    @Query(value = "SELECT c FROM Customer c INNER JOIN CustomerOrder co "
            + " ON c.id=co.customerId WHERE co.orderStatus='Rejected'")
    List<Customer> getCustomerWhoOrderedRejected();

    //1.9 JPQL also support the UPDATE and DELETE in addition to retrieving objects (SELECT queries) with @Query annotation.
    /**
     * Update customer country where id is something(e.g 9)
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer c SET c.country='Spain' WHERE c.id= 9")
    int updateCustomerCountry();

    /**
     * Delete customer records whose id is something(e.g 6)
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Customer c WHERE c.id= 6")
    void deleteCustomer();

    //1.1 To fetch/retrieve the list of all customers:
    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    List<Customer> getCustomerNative();

    //1.2 It returns the list of the first name of customers:
    @Query(value = "SELECT c.first_name FROM customer c", nativeQuery = true)
    List<String> getCustomerFirstNameNative();

    //1.3 It returns the list of the first name of customers whose country is the USA:
    @Query(value = "SELECT c.first_name FROM customer c WHERE c.country='USA'", nativeQuery = true)
    List<String> getCustomerFirstNameWhereCountryNative();

    //1.4 It returns the only 10 records of customers:
    @Query(value = "SELECT * FROM customer c LIMIT 10", nativeQuery = true)
    List<Customer> getCustomerWhereCountryNative();
}
