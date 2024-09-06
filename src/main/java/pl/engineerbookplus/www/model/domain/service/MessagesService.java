package pl.engineerbookplus.www.model.domain.service;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.service.Service;

import java.util.List;

public interface MessagesService extends Service<Message> {

    List<Message> searchMessages(Person i, Person interlocutor);

}
