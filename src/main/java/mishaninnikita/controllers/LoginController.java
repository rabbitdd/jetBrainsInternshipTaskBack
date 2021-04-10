package mishaninnikita.controllers;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.data.User;
import mishaninnikita.data.UserRepository;

import javax.inject.Inject;
import java.sql.SQLDataException;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class LoginController {

    @Inject
    UserRepository userRepository;

    @Post("/signIn")
    @Produces(MediaType.APPLICATION_JSON)

    public String signInUser(@Body User user) {
        return "Вы авторизованы !" + user.toString();
    }
}
