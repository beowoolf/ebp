package pl.engineerbookplus.www.model.domain.repository;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.repository.Repository;

import java.util.List;

public interface MessagesRepository extends Repository<Message> {

    List<Message> searchMessagesByInterlocutor(Person i, Person interlocutor);

}
