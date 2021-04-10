package mishaninnikita.data;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    UserRepository userRepository;

    public boolean signUpUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loadUser(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            return userRepository.findByLogin(login).get();
        } else {
            System.out.println("Пользователь с таким именем не существует !");
            return null;
        }

    }

}
