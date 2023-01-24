package pl.engineerbookplus.www.model.service;

import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.Person;

import java.util.List;

public interface MessagesService extends Service<Message> {

    List<Message> searchMessages(Person i, Person interlocutor);

}
