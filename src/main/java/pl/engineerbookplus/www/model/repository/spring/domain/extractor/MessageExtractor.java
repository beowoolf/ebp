package pl.engineerbookplus.www.model.repository.spring.domain.extractor;

import pl.engineerbookplus.www.model.Message;
import pl.engineerbookplus.www.model.processor.MessageProcessor;
import pl.engineerbookplus.www.model.processor.Processor;

public class MessageExtractor extends AbstractExtractor<Message> {

    @Override
    protected Processor<Message> getProcessor() {
        return new MessageProcessor();
    }

}
