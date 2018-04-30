package bookstore.converter;

import bookstore.dto.UserDto;
import bookstore.entity.User;

public class UserDtoToUserConverter implements SuperConverter<UserDto,User>{
    @Override
    public User apply(final UserDto input){
        final User user=new User();
        user.setPassword(input.getPassword());
        user.setUsername(input.getUsername());
        return user;
    }
}
