package pl.engineerbookplus.www.model.repository.hibernate.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.repository.PersonsRepository;
import pl.engineerbookplus.www.model.repository.hibernate.AbstractRepositoryByHibernate;

import java.util.List;

@Repository
@Transactional(transactionManager = "transactionManagerByHibernate")
public class PersonsRepositoryByHibernate
        extends AbstractRepositoryByHibernate<Person>
        implements PersonsRepository {

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByUserName(String userName) {
        return (Person) getSession().getNamedQuery("Person.findByUserName")
                .setString("userName", userName).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByMail(String mail) {
        return (Person) getSession().getNamedQuery("Person.findByMail")
                .setString("mail", mail).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByLastname(String lastname) {
        return getSession().getNamedQuery("Person.findByLastname")
                .setString("lastname", lastname).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFirstname(String firstname) {
        return getSession().getNamedQuery("Person.findByName")
                .setString("name", firstname).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return getSession().getNamedQuery("Person.findByFullName")
                .setString("arg1", arg1).setString("arg2", arg2).list();
    }

}
