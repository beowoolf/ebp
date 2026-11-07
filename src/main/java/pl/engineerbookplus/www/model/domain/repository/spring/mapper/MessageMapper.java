package pl.engineerbookplus.www.model.domain.repository.spring.mapper;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.processor.MessageProcessor;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.spring.mapper.AbstractMapper;

public class MessageMapper extends AbstractMapper<Message> {

    @Override
    protected Processor<Message> getProcessor() {
        return new MessageProcessor();
    }

}
