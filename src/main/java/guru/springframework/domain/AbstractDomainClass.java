package guru.springframework.domain;

import guru.springframework.config.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by YSkakun on 11/30/2016.
 */
@MappedSuperclass
public class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateUpdated;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    @PrePersist
    protected void onCreate() {
        dateUpdated = dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = LocalDateTime.now();
    }

}