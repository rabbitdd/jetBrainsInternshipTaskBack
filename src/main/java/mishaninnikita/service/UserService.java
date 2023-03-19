package mishaninnikita.service;

import jakarta.inject.Singleton;
import mishaninnikita.entity.User;
import mishaninnikita.repository.UserRepository;

import java.util.Optional;

@Singleton
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean signUpUser(User user) {
    if (existUser(user)) {
      return false;
    }
    userRepository.save(user);
    return true;
  }

  public User loadUser(String login) {
    Optional<User> userOptional = userRepository.findByLogin(login);
    return userOptional.orElse(null);
  }

  private boolean existUser(User user) {
    return userRepository.existsByLogin(user.getLogin());
  }
}
