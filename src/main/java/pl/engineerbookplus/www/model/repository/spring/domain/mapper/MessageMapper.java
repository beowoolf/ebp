package pl.engineerbookplus.www.model.repository.spring.domain.mapper;

import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.processor.MessageProcessor;
import pl.engineerbookplus.www.model.processor.Processor;

public class MessageMapper extends AbstractMapper<Message> {

    @Override
    protected Processor<Message> getProcessor() {
        return new MessageProcessor();
    }

}
