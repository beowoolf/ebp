package pl.engineerbookplus.www.model.domain.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.MessagesRepository;
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
                .setParameter("p1", interlocutor.getId().toString())
                .setParameter("p2", i.getId().toString()).list();
    }

}
