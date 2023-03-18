package mishaninnikita.data;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @NonNull
    User save(@NotBlank @NonNull User entity);
    Optional<User> findByLogin(String login);
}
