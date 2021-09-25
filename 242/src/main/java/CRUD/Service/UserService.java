package CRUD.Service;

import CRUD.model.User;

import java.util.List;

public interface UserService {
    void setUser(User user);

    User getUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUserByLogin(String login);
}
