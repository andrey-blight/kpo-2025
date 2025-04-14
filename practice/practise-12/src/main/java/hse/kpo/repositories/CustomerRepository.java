package hse.kpo.repositories;

import hse.kpo.domains.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("""
        Delete FROM Customer c 
        WHERE c.name = :name 
    """)
    List<Customer> deleteByName(
            @Param("name") String name
    );

    @Query("""
SELECT c FROM Customer c JOIN FETCH c.cars
""")
    List<Customer> getCustomers();
}