package mishaninnikita.repository;

import io.micronaut.context.annotation.Executable;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import mishaninnikita.entity.Task;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Executable
    @NonNull
    @Override
    <S extends Task> S save(@NonNull @Valid @NotNull S entity);

    Iterable<Task> findAllByOwner(String owner);
    void deleteByOwnerAndName(String owner, String taskName);
    Task findByOwnerAndName(String owner, String taskName);

    @NonNull
    @Override
    <S extends Task> S update(@NonNull @Valid @NotNull S entity);
}
