package mishaninnikita.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.entity.User;
import mishaninnikita.security.UtilityBCrypt;
import mishaninnikita.service.UserService;

@Controller("/user")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RegisterController {
  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @Post(uri = "/reg")
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<User> signUpUser(@Body User user) {
    String hashPassword = UtilityBCrypt.hash(user.getPassword());
    user.setPassword(hashPassword);
    if (!userService.signUpUser(user)) {
      return HttpResponse.badRequest();
    }
    return HttpResponse.ok();
  }
}
