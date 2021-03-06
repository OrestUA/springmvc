package guru.springframework.services.reposervices;

import guru.springframework.domain.security.Role;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fudjitsu on 16.01.2017.
 */
@Service
@Profile("springdatajpa")
public class RoleServiceRepoImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(role -> roles.add(role));
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.delete(id);
    }
}
