package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DAO<A> {

    Collection<A> getAll();

    Optional<A> getById(int id);

    void create (A a);

}

