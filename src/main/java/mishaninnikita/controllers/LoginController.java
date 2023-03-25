package mishaninnikita.controllers;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.entity.User;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/user")
public class LoginController {

    @Post(uri = "/login")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<User> signInUser(@Body User user) {
        return HttpResponse.ok().body(user);
    }
}
