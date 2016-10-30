package guru.springframework.services;

import java.util.List;

/**
 * Created by Fudjitsu on 30.10.16.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
