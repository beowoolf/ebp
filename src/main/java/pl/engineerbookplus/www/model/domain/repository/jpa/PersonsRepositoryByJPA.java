package pl.engineerbookplus.www.model.domain.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.PersonsRepository;
import pl.engineerbookplus.www.model.repository.jpa.AbstractRepositoryByJPA;

import jakarta.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional(transactionManager = "transactionManagerByJPA")
public class PersonsRepositoryByJPA
        extends AbstractRepositoryByJPA<Person>
        implements PersonsRepository {

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByUserName(String userName) {
        try {
            return (Person) entityManager.createNamedQuery("Person.findByUserName")
                    .setParameter("userName", userName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByMail(String mail) {
        try {
            return (Person) entityManager.createNamedQuery("Person.findByMail")
                    .setParameter("mail", mail).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByLastname(String lastname) {
        return entityManager.createNamedQuery("Person.findByLastname")
                .setParameter("lastname", lastname).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFirstname(String firstname) {
        return entityManager.createNamedQuery("Person.findByName")
                .setParameter("name", firstname).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return entityManager.createNamedQuery("Person.findByFullName")
                .setParameter("arg1", arg1).setParameter("arg2", arg2).getResultList();
    }

}
