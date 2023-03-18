package mishaninnikita.controllers;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.data.User;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class LoginController {

    @Post("/signIn")
    @Produces(MediaType.APPLICATION_JSON)
    public String signInUser(@Body User user) {
        return "Вы авторизованы !" + user.toString();
    }
}
