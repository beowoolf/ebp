package pl.engineerbookplus.www.model.domain.service;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.service.Service;

import java.util.List;

public interface PersonsService extends Service<Person> {

    Person searchPersonByUserName(String userName);

    Person searchPersonByMail(String mail);

    List<Person> searchPersonsByLastname(String lastname);

    List<Person> searchPersonsByFirstname(String firstname);

    List<Person> searchPersonsByFullName(String arg1, String arg2);

    boolean activateProfile(Person person);

    boolean updatePassword(Person person, String password);

}
