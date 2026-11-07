package pl.engineerbookplus.www.model.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.repository.MessagesRepository;
import pl.engineerbookplus.www.service.GenericServiceByEBP;

import java.util.List;

@Service
public class MessagesServiceByEBP
        extends GenericServiceByEBP<Message>
        implements MessagesService {

    @Autowired
    @Qualifier(value = "messagesRepositoryByMongo")
    private MessagesRepository messagesRepository;

    @Override
    public Message addRecord(Message message) {
        return messagesRepository.addRecord(message);
    }

    @Override
    public Message editRecord(Message message) {
        return messagesRepository.editRecord(message);
    }

    @Override
    public Message eraseRecord(Message message) {
        return messagesRepository.eraseRecord(message);
    }

    @Override
    public List<Message> searchMessages(Person i, Person interlocutor) {
        return messagesRepository.searchMessagesByInterlocutor(i, interlocutor);
    }

}
