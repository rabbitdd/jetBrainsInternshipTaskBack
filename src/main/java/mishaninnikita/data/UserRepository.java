package mishaninnikita.data;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Executable
    @NonNull
    @Override
    <S extends User> S save(@NonNull @Valid @NotNull S entity);

    Optional<User> findByLogin(String login);
}
