package pl.engineerbookplus.www.model.domain.processor;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.processor.Processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageProcessor implements Processor<Message> {

    @Override
    public Message process(ResultSet rs) throws SQLException {
        Message msg = new Message();
        msg.setId(rs.getInt("id"));
        msg.setHasRead(rs.getBoolean("hasRead"));
        msg.setMessage(rs.getString("message"));
        msg.setReadTime(rs.getDate("readTime"));
        msg.setRecipientDelete(rs.getBoolean("recipientDelete"));
        msg.setRecipientId(rs.getInt("recipientId"));
        msg.setSend(rs.getDate("send"));
        msg.setSenderDelete(rs.getBoolean("senderDelete"));
        msg.setSenderId(rs.getInt("senderId"));
        return msg;
    }

}
