package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.domain.security.Role;
import guru.springframework.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fudjitsu on 30.10.16.
 */
@Service
@Profile("map")
public class RoleServiceMapImpl extends AbstractMapService implements RoleService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Role getById(Integer id) {
        return (Role)super.getById(id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
            return (Role)super.saveOrUpdate(role);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
