package pl.engineerbookplus.www.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(catalog = "16120792_nebp", name = "abouts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "About.findAll", query = "SELECT a FROM About a"),
        @NamedQuery(name = "About.findById", query = "SELECT a FROM About a WHERE a.id = :id"),
        @NamedQuery(name = "About.findByHide", query = "SELECT a FROM About a WHERE a.hide = :hide")})
public class About implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String aboutMe;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hide;
    @Transient
    @OneToMany(mappedBy = "about")
    @ToString.Exclude
    private Collection<Person> personsCollection;

    public About(Integer id) {
        this.id = id;
    }

    public About(Integer id, String aboutMe, boolean hide) {
        this.id = id;
        this.aboutMe = aboutMe;
        this.hide = hide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        About about = (About) o;
        return id != null && Objects.equals(id, about.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
