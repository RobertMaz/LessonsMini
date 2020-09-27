package control;

import persist.City;
import persist.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserContr implements Serializable {

    @Inject
    private UserRepo userRepo;

    @Inject
    private CityRepo cityRepo;

    private User user;

    public String editUser(User user){
        this.user = user;
        return "";
    }

    public void deleteUser(User user){
        userRepo.delete(user.getId());
    }

    public String createUser(){
        this.user = new User();
        return "";
    }

    public String saveUser(){
        if (user.getId() != 0){
            userRepo.update(user);
        } else {
            userRepo.insert(user);
        }
        return "";
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public CityRepo getCityRepo() {
        return cityRepo;
    }

    public void setCityRepo(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    List<City> getAllCity(){
        return cityRepo.findAll();
    }
}
