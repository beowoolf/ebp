package pl.engineerbookplus.www.model.repository;

import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.Person;

import java.util.List;

public interface MessagesRepository extends Repository<Message> {

    List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor);

}
