package pl.engineerbookplus.www.model.domain.repository.spring;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.PersonsRepository;
import pl.engineerbookplus.www.model.domain.repository.spring.extractor.PersonExtractor;
import pl.engineerbookplus.www.model.domain.repository.spring.mapper.PersonMapper;
import pl.engineerbookplus.www.model.domain.wizard.AddPersonPreparedStatementWizard;
import pl.engineerbookplus.www.model.domain.wizard.EditPersonPreparedStatementWizard;
import pl.engineerbookplus.www.model.repository.spring.AbstractRepositoryBySpring;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonsRepositoryBySpring
        extends AbstractRepositoryBySpring<Person>
        implements PersonsRepository {

    private final PreparedStatementWizard<Person> addPersonPreparedStatementWizard = new AddPersonPreparedStatementWizard();
    private final PreparedStatementWizard<Person> editPersonPreparedStatementWizard = new EditPersonPreparedStatementWizard();

    @Override
    protected PreparedStatementCreator addPreparedStatementCreator(final Person p) {
        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return addPersonPreparedStatementWizard.createPreparedStatement(con, p);
            }
        };
    }

    @Override
    protected PreparedStatementCreator editPreparedStatementCreator(final Person p) {
        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return editPersonPreparedStatementWizard.createPreparedStatement(con, p);
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByUserName(String userName) {
        return jdbcTemplate.query(
                "select * from 16120792_nebp.persons p where p.username=?",
                new PersonExtractor(),
                userName
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByMail(String mail) {
        return jdbcTemplate.query(
                "select * from 16120792_nebp.persons p where p.mail=?",
                new PersonExtractor(),
                mail
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByLastname(String lastname) {
        return jdbcTemplate.query(
                "select * from 16120792_nebp.persons p where p.surrname=? or p.familyName=?",
                new PersonMapper(),
                lastname, lastname
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFirstname(String firstname) {
        return jdbcTemplate.query(
                "select * from 16120792_nebp.persons p where p.name=?",
                new PersonMapper(),
                firstname
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return jdbcTemplate.query(
                "select * from 16120792_nebp.persons p where (p.name = ? AND (p.surrname = ? OR p.familyName = ?)) OR (p.name = ? AND (p.surrname = ? OR p.familyName = ?))",
                new PersonMapper(),
                arg1, arg2, arg2, arg2, arg1, arg1
        );
    }

}
