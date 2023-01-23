package pl.engineerbookplus.www.model.repository.spring.domain.mapper;

import pl.engineerbookplus.www.model.Person;
import pl.engineerbookplus.www.model.processor.PersonProcessor;
import pl.engineerbookplus.www.model.processor.Processor;

public class PersonMapper extends AbstractMapper<Person> {

    @Override
    protected Processor<Person> getProcessor() {
        return new PersonProcessor();
    }

}
