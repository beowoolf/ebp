package pl.engineerbookplus.www.model.repository.hibernate.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.repository.MessagesRepository;
import pl.engineerbookplus.www.model.repository.hibernate.AbstractRepositoryByHibernate;

import java.util.List;

@Repository
@Transactional(transactionManager = "transactionManagerByHibernate")
public class MessagesRepositoryByHibernate
        extends AbstractRepositoryByHibernate<Message>
        implements MessagesRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor) {
        return getSession().getNamedQuery("Message.findByInterlocutor")
                .setString("p1", interlocutor.getId().toString())
                .setString("p2", i.getId().toString()).list();
    }

}
