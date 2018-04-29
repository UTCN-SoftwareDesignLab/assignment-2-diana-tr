package bookstore.service;

import bookstore.dto.UserDto;
import bookstore.entity.User;
import bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto)
    {
           User user=new User(userDto.getUsername(),userDto.getPassword(),userDto.isEnabled(),userDto.getRole());
           return userRepository.save(user);
    }

    @Override
    public void delete(UserDto userDto) {
        User user=new User(userDto.getUsername(),userDto.getPassword(),userDto.isEnabled(),userDto.getRole());
        userRepository.delete(user);
    }

    @Override
    public void update(UserDto userDto) {
        User user=userRepository.findByUsername(userDto.getUsername());

    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
