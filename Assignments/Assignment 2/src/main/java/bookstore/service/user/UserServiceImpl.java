package bookstore.service.user;

import bookstore.dto.UserDto;
import bookstore.entity.User;
import bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.List;

@Service
@Transactional
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
    public User create(UserDto userDto) {
        User user = new User(userDto.getUsername(), encodePassword(userDto.getPassword()), userDto.isEnabled(), userDto.getRole());
        return userRepository.save(user);
    }

    @Override
    public void delete(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.isEnabled(), userDto.getRole());
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {

        User newUser = userRepository.findByUsername(user.getUsername());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encodePassword(user.getPassword()));

        userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
