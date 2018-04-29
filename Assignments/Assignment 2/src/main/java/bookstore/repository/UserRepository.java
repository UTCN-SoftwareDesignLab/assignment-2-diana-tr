package bookstore.repository;

import bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
    void deleteByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
