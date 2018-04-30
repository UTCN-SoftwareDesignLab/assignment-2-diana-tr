package bookstore;

import bookstore.dto.UserDto;
import bookstore.entity.User;
import bookstore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserCRUDController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid UserDto userDto){
        userService.create(userDto);
        return "redirect:create?success";
    }

    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String showCreateForm(Model model){
        model.addAttribute("user",new UserDto());
        return "user-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "username") String username, Model model) {
        userService.deleteByUsername(username);
        model.addAttribute("deleteMessage", "User was successfully deleted");
        return "redirect:/users";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-update-form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password,
                         @ModelAttribute User user) {
       User user1=userService.findByUsername(username);
        user1.setUsername(username);
        user1.setPassword(password);

        userService.update(user1);
        return "redirect:update?success";
    }


}
