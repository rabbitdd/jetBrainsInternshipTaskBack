package mishaninnikita.data;

import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

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
    Optional<User> userOptional = userRepository.findByLogin(login);
    return userOptional.orElse(null);
  }
}
