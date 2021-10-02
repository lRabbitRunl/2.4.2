package CRUD.controller;

import CRUD.Service.RoleService;
import CRUD.Service.UserService;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/")
    public String startPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/updateUser")
    public String updateUserForm(@RequestParam(value = "id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        roleService.updateUser(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RolesAllowed(value = "ADMIN")
    @GetMapping("/addUser")
    public String addUserForm(@ModelAttribute User user, ModelMap model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "addUser";
    }

    @RolesAllowed(value = "ADMIN")
    @PostMapping("/addUser")
    public String addUser(User user) {
        userService.setUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser(Principal user, ModelMap modelMap) {
        User userBd = userService.getUserByLogin(user.getName());
        modelMap.addAttribute("user", userBd);
        return "/user";
    }

}
