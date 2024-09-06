package pl.engineerbookplus.www.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Document(collection = "persons")
@Table(catalog = "16120792_nebp", name = "persons", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"mail"}),
        @UniqueConstraint(columnNames = {"username"})})
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.findByUserName", query = "SELECT p FROM Person p WHERE p.username = :userName"),
        @NamedQuery(name = "Person.findByPassword", query = "SELECT p FROM Person p WHERE p.password = :password"),
        @NamedQuery(name = "Person.findByEnabled", query = "SELECT p FROM Person p WHERE p.enabled = :enabled"),
        @NamedQuery(name = "Person.findByMail", query = "SELECT p FROM Person p WHERE p.mail = :mail"),
        @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
        @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname"),
        @NamedQuery(name = "Person.findByFamilyName", query = "SELECT p FROM Person p WHERE p.familyName = :familyName"),
        @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE (p.surname = :lastname OR p.familyName = :lastname)"),
        @NamedQuery(name = "Person.findByFullName", query = "SELECT p FROM Person p WHERE (p.name = :arg1 AND (p.surname = :arg2 OR p.familyName = :arg2)) OR (p.name = :arg2 AND (p.surname = :arg1 OR p.familyName = :arg1))"),
        @NamedQuery(name = "Person.findByMaleGender", query = "SELECT p FROM Person p WHERE p.maleGender = :maleGender"),
        @NamedQuery(name = "Person.findByBirthday", query = "SELECT p FROM Person p WHERE p.birthday = :birthday"),
        @NamedQuery(name = "Person.findByHideBirthday", query = "SELECT p FROM Person p WHERE p.hideBirthday = :hideBirthday")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @org.springframework.data.annotation.Transient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Transient
    @org.springframework.data.annotation.Id
    private ObjectId idPerson;
    @Indexed(unique = true)
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String username;
    @Basic(optional = false)
    @Column(nullable = false, length = 60)
    private String password;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean enabled;
    @Indexed(unique = true)
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String mail;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String surname;
    @Column(length = 45)
    private String familyName;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean maleGender;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthday;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hideBirthday;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Gg> numbersGG;
    @JoinColumn(name = "idAbout", referencedColumnName = "id")
    @ManyToOne
    private About about;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Language> languages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Skype> skypes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Collection<Invitation> sentInvitations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
    private Collection<Invitation> receivedAnInvitations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Achievement> achievements;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Experience> experiences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Phone> phones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Education> educations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<Homepage> homepages;

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String username, String password, boolean enabled, String mail, String name, String surname, boolean maleGender, Date birthday, boolean hideBirthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.maleGender = maleGender;
        this.birthday = birthday;
        this.hideBirthday = hideBirthday;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Pomyślnie zarejestrowano profil o następujących informacjach:<br />"
                + "Imię: " + name + "<br />"
                + "Nazwisko: " + surname + "<br />"
                + "Nazwa użytkownika: " + username + "<br />"
                + "Adres e-mail: " + mail + "<br />"
                + "Data urodzenia: " + birthday.getDate() + "." + (birthday.getMonth() + 1) + "." + (birthday.getYear() + 1900) + "<br />"
                + "Płeć: " + (maleGender ? "mężczyzna" : "kobieta") + "<br />"
                + "<br />Hasło do Twojego konta zostało wysłane na adres podany w formularzu rejestracyjnym.<br />"
                + "Sprawdź pocztę i użyj hasła wysłanego na adres podany w formularzu rejestracyjnym w formularzu poniżej, aby aktywować konto i móc się na nie logować.<br />";
    }

}
