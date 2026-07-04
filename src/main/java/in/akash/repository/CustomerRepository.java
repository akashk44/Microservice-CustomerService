package in.akash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<in.akash.entity.CustomerEntity, Long>
{

    @Query(value = "SELECT COUNT(*) FROM CUSTOMER WHERE phone_number = ?1 AND password = ?2", nativeQuery = true)
    Integer checkLogin(Long phoneNumber, String password);
}
