package pl.engineerbookplus.www.model.repository.spring.domain.extractor;

import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.processor.PersonProcessor;
import pl.engineerbookplus.www.model.processor.Processor;

public class PersonExtractor extends AbstractExtractor<Person> {

    @Override
    protected Processor<Person> getProcessor() {
        return new PersonProcessor();
    }

}
