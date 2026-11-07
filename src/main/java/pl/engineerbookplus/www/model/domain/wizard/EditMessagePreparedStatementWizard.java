package pl.engineerbookplus.www.model.domain.wizard;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.*;

public class EditMessagePreparedStatementWizard implements PreparedStatementWizard<Message> {

    @Override
    public PreparedStatement createPreparedStatement(Connection c, Message m) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "update 16120792_nebp.messages set hasRead=?, message=?, readTime=?, recipientDelete=?, recipientId=?, send=?, senderDelete=?, senderId=? where id=?",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setBoolean(1, m.isHasRead());
        ps.setString(2, m.getMessage());
        ps.setTimestamp(3, (Timestamp) m.getReadTime());
        ps.setBoolean(4, m.isRecipientDelete());
        ps.setInt(5, m.getRecipientId());
        ps.setTimestamp(6, (Timestamp) m.getSend());
        ps.setBoolean(7, m.isSenderDelete());
        ps.setInt(8, m.getSenderId());
        ps.setInt(9, m.getId());
        return ps;
    }

}
