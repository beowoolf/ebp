package pl.engineerbookplus.www.model.repository;

import pl.engineerbookplus.www.model.Person;

import java.util.List;

public interface PersonsRepository extends Repository<Person> {

    Person searchPersonByUserName(String userName);

    Person searchPersonByMail(String mail);

    List<Person> searchPersonsByLastname(String lastname);

    List<Person> searchPersonsByFirstname(String firstname);

    List<Person> searchPersonsByFullName(String arg1, String arg2);

}
