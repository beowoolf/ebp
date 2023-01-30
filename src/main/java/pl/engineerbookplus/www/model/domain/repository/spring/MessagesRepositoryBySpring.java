package pl.engineerbookplus.www.model.domain.repository.spring;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.MessagesRepository;
import pl.engineerbookplus.www.model.domain.repository.spring.mapper.MessageMapper;
import pl.engineerbookplus.www.model.domain.wizard.AddMessagePreparedStatementWizard;
import pl.engineerbookplus.www.model.domain.wizard.EditMessagePreparedStatementWizard;
import pl.engineerbookplus.www.model.repository.spring.AbstractRepositoryBySpring;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessagesRepositoryBySpring
        extends AbstractRepositoryBySpring<Message>
        implements MessagesRepository {

    private final PreparedStatementWizard<Message> addMessagePreparedStatementWizard = new AddMessagePreparedStatementWizard();
    private final PreparedStatementWizard<Message> editMessagePreparedStatementWizard = new EditMessagePreparedStatementWizard();

    @Override
    protected PreparedStatementCreator addPreparedStatementCreator(final Message m) {
        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return addMessagePreparedStatementWizard.createPreparedStatement(con, m);
            }
        };
    }

    @Override
    protected PreparedStatementCreator editPreparedStatementCreator(final Message m) {
        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return editMessagePreparedStatementWizard.createPreparedStatement(con, m);
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor) {
        return jdbcTemplate.query(
                "select m.id, m.hasRead, m.message, m.readTime, m.recipientDelete, m.recipientId, m.send, m.senderDelete, m.senderId from 16120792_nebp.messages m where (m.senderId = ? AND m.recipientId = ?) OR (m.senderId = ? AND m.recipientId = ?)",
                new MessageMapper(),
                i.getId(), interlocutor.getId(), interlocutor.getId(), i.getId()
        );
    }

}
