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

import javax.inject.Inject;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)

public class RegisterController {

    @Inject
    UserService userService;

    @Post("/signUp")
    @Produces(MediaType.APPLICATION_JSON)
    public String signUpUser(@Body User user) {
        System.out.println("request ...");
        System.out.println(user.toString());
        if (userService.signUpUser(user)) {
            return "good";
        } else {
            return "bad";
        }
    }

}
