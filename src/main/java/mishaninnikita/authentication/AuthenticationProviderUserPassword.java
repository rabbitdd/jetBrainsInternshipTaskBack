package mishaninnikita.authentication;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import jakarta.inject.Singleton;
import mishaninnikita.entity.User;
import mishaninnikita.service.UserService;
import mishaninnikita.security.UtilityBCrypt;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderUserPassword.class);

  private final UserService userService;

  public AuthenticationProviderUserPassword(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Publisher<AuthenticationResponse> authenticate(
      HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
    assert httpRequest != null;
    String login = authenticationRequest.getIdentity().toString();
    String password = authenticationRequest.getSecret().toString();
    User user = userService.loadUser(login);
    return Mono.create(
        emitter -> {
          if (isAuth(user, login, password)) {
            logger.info("User with login: {} is authenticated", login);
            emitter.success(AuthenticationResponse.success("user"));
          } else {
            logger.info("Error");
            emitter.error(new AuthenticationException(new AuthenticationFailed()));
          }
        });
  }

  private boolean isAuth(User user, String login, String password) {
    if (user == null) {
      logger.error("User with login {} not exist", login);
      return false;
    }
    return user.getLogin().equals(login) &&
            UtilityBCrypt.verifyHash(password, user.getPassword());
  }
}
