package guru.springframework.services.reposervices;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fudjitsu on 16.01.2017.
 */
@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user->users.add(user));
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return userRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}