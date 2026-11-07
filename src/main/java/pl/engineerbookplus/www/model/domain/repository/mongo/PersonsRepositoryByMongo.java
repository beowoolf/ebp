package pl.engineerbookplus.www.model.domain.repository.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.PersonsRepository;
import pl.engineerbookplus.www.model.repository.mongo.AbstractRepositoryByMongo;

import java.util.List;

@Repository
public class PersonsRepositoryByMongo
        extends AbstractRepositoryByMongo<Person>
        implements PersonsRepository {

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByUserName(String userName) {
        return (Person) mongo.findOne(Query.query(Criteria.where("username").is(userName)), Person.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Person searchPersonByMail(String mail) {
        return (Person) mongo.findOne(Query.query(Criteria.where("mail").is(mail)), Person.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByLastname(String lastname) {
        return mongo.find(Query.query(new Criteria().orOperator(Criteria.where("surrname").is(lastname), Criteria.where("familyName").is(lastname))), Person.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFirstname(String firstname) {
        return mongo.find(Query.query(Criteria.where("name").is(firstname)), Person.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> searchPersonsByFullName(String arg1, String arg2) {
        return mongo.find(
                Query.query(
                        new Criteria().orOperator(
                                new Criteria().andOperator(
                                        Criteria.where("name").is(arg1),
                                        new Criteria().orOperator(
                                                Criteria.where("surrname").is(arg2),
                                                Criteria.where("familyName").is(arg2)
                                        )
                                ),
                                new Criteria().andOperator(
                                        Criteria.where("name").is(arg2),
                                        new Criteria().orOperator(
                                                Criteria.where("surrname").is(arg1),
                                                Criteria.where("familyName").is(arg1)
                                        )
                                )
                        )
                ),
                Person.class
        );
    }

}
