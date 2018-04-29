package bookstore;

import bookstore.dto.UserDto;
import bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /*@RequestMapping(method = RequestMethod.GET)
    public String getOne(Model model, @RequestParam("username") String username){
       model.addAttribute("user",userService.findByUsername(username));
       return "users";
    }*/

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid UserDto userDto){
        userService.create(userDto);
        return "redirect:create?success";
    }

    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String showCreateForm(Model model){
        model.addAttribute("newUser",new UserDto());
        return "user-form";
    }

}
