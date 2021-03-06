package guru.springframework.domain.security

import guru.springframework.domain.AbstractDomainClass
import guru.springframework.domain.User

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

/**
 * Created by YSkakun on 12/16/2016.
 */
@Entity
class Role extends AbstractDomainClass {

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<User> users = new ArrayList<>();

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
        if (!user.getRoles().contains(this)) {
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
