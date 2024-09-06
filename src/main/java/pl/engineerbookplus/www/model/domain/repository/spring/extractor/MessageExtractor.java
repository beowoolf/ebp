package pl.engineerbookplus.www.model.domain.repository.spring.extractor;

import pl.engineerbookplus.www.model.domain.Message;
import pl.engineerbookplus.www.model.domain.processor.MessageProcessor;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.spring.extractor.AbstractExtractor;

public class MessageExtractor extends AbstractExtractor<Message> {

    @Override
    protected Processor<Message> getProcessor() {
        return new MessageProcessor();
    }

}
