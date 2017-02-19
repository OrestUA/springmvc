package guru.springframework.services.mapservices;

import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Fudjitsu on 30.10.16.
 */
@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        if(user.getPassword()!=null){
            user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }
        return (User) super.saveOrUpdate(user);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public User findByUserName(String userName) {
        User returnUser = (User) domainMap.values().stream().filter(domainObject ->
                ((User) domainObject).getUsername().equalsIgnoreCase(userName)
        ).findFirst().get();
        return returnUser;
    }
}