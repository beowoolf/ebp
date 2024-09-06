package pl.engineerbookplus.www.model.domain.wizard;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.*;

public class AddMessagePreparedStatementWizard implements PreparedStatementWizard<Message> {

    @Override
    public PreparedStatement createPreparedStatement(Connection c, Message m) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "insert into 16120792_nebp.messages (hasRead, message, readTime, recipientDelete, recipientId, send, senderDelete, senderId) values (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setBoolean(1, m.isHasRead());
        ps.setString(2, m.getMessage());
        if (m.getReadTime() == null) {
            ps.setObject(3, null);
        } else {
            ps.setTimestamp(3, new Timestamp(m.getReadTime().getTime()));
        }
        ps.setBoolean(4, m.isRecipientDelete());
        ps.setInt(5, m.getRecipientId());
        ps.setTimestamp(6, new Timestamp(m.getSend().getTime()));
        ps.setBoolean(7, m.isSenderDelete());
        ps.setInt(8, m.getSenderId());
        return ps;
    }

}
