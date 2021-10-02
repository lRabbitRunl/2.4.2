package CRUD.Service;

import CRUD.Dao.UserDao;
import CRUD.model.Role;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Set<Role> allRoles;

    @Autowired
    public UserServiceImpl(UserDao userDao, Set<Role> allRoles) {
        this.userDao = userDao;
        this.allRoles = allRoles;
    }

    @Override
    public void setUser(User user) {
        Set<Role> temp = new HashSet<>();
        if (user.getRoles().isEmpty()) {
            user.addRole(new Role("USER"));
        }
        allRoles.stream().filter(role -> user.getRoles().contains(new Role(role.getName()))).forEach(temp::add);
        user.setRoles(temp);
        userDao.setUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id).orElse(null);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login).orElse(null);
    }


}
