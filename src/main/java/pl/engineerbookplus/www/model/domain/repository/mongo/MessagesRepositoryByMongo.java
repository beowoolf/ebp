package pl.engineerbookplus.www.model.domain.repository.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.MessagesRepository;
import pl.engineerbookplus.www.model.repository.mongo.AbstractRepositoryByMongo;

import java.util.List;

@Repository
public class MessagesRepositoryByMongo
        extends AbstractRepositoryByMongo<Message>
        implements MessagesRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor) {
        return mongo.find(
                Query.query(
                        new Criteria().orOperator(
                                new Criteria().andOperator(
                                        Criteria.where("sender").is(i.getIdPerson()),
                                        Criteria.where("recipient").is(interlocutor.getIdPerson())
                                ),
                                new Criteria().andOperator(
                                        Criteria.where("sender").is(interlocutor.getIdPerson()),
                                        Criteria.where("recipient").is(i.getIdPerson())
                                )
                        )
                ),
                Message.class
        );
    }

}
