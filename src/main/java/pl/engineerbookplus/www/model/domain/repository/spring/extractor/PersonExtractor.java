package pl.engineerbookplus.www.model.domain.repository.spring.extractor;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.processor.PersonProcessor;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.spring.extractor.AbstractExtractor;

public class PersonExtractor extends AbstractExtractor<Person> {

    @Override
    protected Processor<Person> getProcessor() {
        return new PersonProcessor();
    }

}
