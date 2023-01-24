package pl.engineerbookplus.www.model.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.repository.PersonsRepository;
import pl.engineerbookplus.www.model.service.GenericServiceByEBP;
import pl.engineerbookplus.www.model.service.PersonsService;

import java.util.List;

@Service
public class PersonsServiceByEBP
        extends GenericServiceByEBP<Person>
        implements PersonsService {

    @Autowired
    @Qualifier(value = "personsRepositoryByMongo")
    private PersonsRepository personsRepository;

    @Override
    public Person addRecord(Person person) {
        return personsRepository.addRecord(person);
    }

    @Override
    public Person editRecord(Person person) {
        return personsRepository.editRecord(person);
    }

    @Override
    public Person eraseRecord(Person person) {
        return personsRepository.eraseRecord(person);
    }

    @Override
    public Person searchPersonByUserName(String userName) {
        return personsRepository.searchPersonByUserName(userName);
    }

    @Override
    public Person searchPersonByMail(String mail) {
        return personsRepository.searchPersonByMail(mail);
    }

    @Override
    public List<Person> searchPersonsByLastname(String lastname) {
        return personsRepository.searchPersonsByLastname(lastname);
    }

    @Override
    public List<Person> searchPersonsByFirstname(String firstname) {
        return personsRepository.searchPersonsByFirstname(firstname);
    }

    @Override
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return personsRepository.searchPersonsByFullName(arg1, arg2);
    }

    @Override
    public boolean activateProfile(Person person) {
        person.setEnabled(true);
        return personsRepository.editRecord(person).isEnabled();
    }

    @Override
    public boolean updatePassword(Person person, String password) {
        try {
            person.setPassword(password);
            return (personsRepository.editRecord(person) != null);
        } catch (Exception e) {
            return false;
        }
    }

}
