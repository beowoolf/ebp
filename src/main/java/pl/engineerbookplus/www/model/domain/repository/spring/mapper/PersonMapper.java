package pl.engineerbookplus.www.model.domain.repository.spring.mapper;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.processor.PersonProcessor;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.spring.mapper.AbstractMapper;

public class PersonMapper extends AbstractMapper<Person> {

    @Override
    protected Processor<Person> getProcessor() {
        return new PersonProcessor();
    }

}
