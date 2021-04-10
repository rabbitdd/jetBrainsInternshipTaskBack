package mishaninnikita.auth;


import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import mishaninnikita.data.User;
import mishaninnikita.data.UserRepository;
import mishaninnikita.data.UserService;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {


    @Inject
    UserService userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        assert httpRequest != null;
        System.out.println(httpRequest.getHeaders().get("login"));
        String login = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();
        User user = userService.loadUser(login);
        System.out.println(login + " " + password);
        return Flowable.create(emitter -> {
            if (user != null && user.getPassword().equals(password)) {
                emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
