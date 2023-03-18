package mishaninnikita.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.data.User;
import mishaninnikita.data.UserService;
import mishaninnikita.security.UtilityBCrypt;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class RegisterController {
  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @Post("/signUp")
  @Produces(MediaType.APPLICATION_JSON)
  public String signUpUser(@Body User user) {
    String hashPassword = UtilityBCrypt.hash(user.getPassword());
    user.setPassword(hashPassword);
    if (userService.signUpUser(user)) {
      return "good";
    } else {
      return "bad";
    }
  }
}
