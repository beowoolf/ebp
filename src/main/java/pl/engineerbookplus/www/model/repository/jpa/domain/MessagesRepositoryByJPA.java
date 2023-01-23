package pl.engineerbookplus.www.model.repository.jpa.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.repository.MessagesRepository;
import pl.engineerbookplus.www.model.repository.jpa.AbstractRepositoryByJPA;

import java.util.List;

@Repository
@Transactional(transactionManager = "transactionManagerByJPA")
public class MessagesRepositoryByJPA
        extends AbstractRepositoryByJPA<Message>
        implements MessagesRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor) {
        return entityManager.createNamedQuery("Message.findByInterlocutor")
                .setParameter("p1", interlocutor.getId())
                .setParameter("p2", i.getId()).getResultList();
    }

}
