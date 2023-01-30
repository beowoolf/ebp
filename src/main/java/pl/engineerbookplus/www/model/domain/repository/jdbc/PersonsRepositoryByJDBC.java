package pl.engineerbookplus.www.model.domain.repository.jdbc;

import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.processor.PersonProcessor;
import pl.engineerbookplus.www.model.domain.repository.PersonsRepository;
import pl.engineerbookplus.www.model.domain.wizard.AddPersonPreparedStatementWizard;
import pl.engineerbookplus.www.model.domain.wizard.EditPersonPreparedStatementWizard;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.jdbc.AbstractRepositoryByJDBC;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.util.List;

@Repository
public class PersonsRepositoryByJDBC
        extends AbstractRepositoryByJDBC<Person>
        implements PersonsRepository {

    @Override
    protected PreparedStatementWizard<Person> addRecordPreparedStatementWizard(Person t) {
        return new AddPersonPreparedStatementWizard();
    }

    @Override
    protected Processor<Person> getProcessor() {
        return new PersonProcessor();
    }

    @Override
    protected PreparedStatementWizard<Person> editRecordPreparedStatementWizard(Person t) {
        return new EditPersonPreparedStatementWizard();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByUserName(String userName) {
        List<Person> t = searchRecords("select * from 16120792_nebp.persons where username = '" + userName + "'");
        if (t.isEmpty())
            return null;
        else
            return t.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByMail(String mail) {
        List<Person> t = searchRecords("select * from 16120792_nebp.persons where mail = '" + mail + "'");
        if (t.isEmpty())
            return null;
        else
            return t.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByLastname(String lastname) {
        return searchRecords("select * from 16120792_nebp.persons where surrname = '" + lastname + "' or familyName = '" + lastname + "'");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFirstname(String firstname) {
        return searchRecords("select * from 16120792_nebp.persons where name = '" + firstname + "'");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return searchRecords("select * from 16120792_nebp.persons where (name = '" + arg1 + "' AND (surrname = '" + arg2 + "' OR familyName = '" + arg2 + "')) OR (name = '" + arg2 + "' AND (surrname = '" + arg1 + "' OR familyName = '" + arg1 + "'))");
    }

}
