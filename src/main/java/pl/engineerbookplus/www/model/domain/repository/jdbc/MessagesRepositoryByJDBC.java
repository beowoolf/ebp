package pl.engineerbookplus.www.model.domain.repository.jdbc;

import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.processor.MessageProcessor;
import pl.engineerbookplus.www.model.domain.repository.MessagesRepository;
import pl.engineerbookplus.www.model.domain.wizard.AddMessagePreparedStatementWizard;
import pl.engineerbookplus.www.model.domain.wizard.EditMessagePreparedStatementWizard;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.jdbc.AbstractRepositoryByJDBC;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.util.List;

@Repository
public class MessagesRepositoryByJDBC
        extends AbstractRepositoryByJDBC<Message>
        implements MessagesRepository {

    @Override
    protected PreparedStatementWizard<Message> addRecordPreparedStatementWizard(Message t) {
        return new AddMessagePreparedStatementWizard();
    }

    @Override
    protected Processor<Message> getProcessor() {
        return new MessageProcessor();
    }

    @Override
    protected PreparedStatementWizard<Message> editRecordPreparedStatementWizard(Message t) {
        return new EditMessagePreparedStatementWizard();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor) {
        return searchRecords("select id, hasRead, message, readTime, recipientDelete, recipientId, send, senderDelete, senderId from 16120792_nebp.messages where (senderId = " + i.getId() + " AND recipientId = " + interlocutor.getId() + ") OR (senderId = " + interlocutor.getId() + " AND recipientId = " + i.getId() + ")");
    }

}
