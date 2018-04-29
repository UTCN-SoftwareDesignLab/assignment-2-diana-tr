package bookstore.service;

import bookstore.dto.UserDto;
import bookstore.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(UserDto userDto);
    void delete(UserDto userDto);
    void update(UserDto userDto);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
    void deleteByUsername(String username);

}
