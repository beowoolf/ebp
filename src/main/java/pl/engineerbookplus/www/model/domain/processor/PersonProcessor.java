package pl.engineerbookplus.www.model.domain.processor;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.processor.Processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonProcessor implements Processor<Person> {

    @Override
    public Person process(ResultSet rs) throws SQLException {
        Person per = new Person();
        per.setId(rs.getInt("id"));
        per.setBirthday(rs.getDate("birthday"));
        per.setEnabled(rs.getBoolean("enabled"));
        per.setFamilyName(rs.getString("familyName"));
        per.setHideBirthday(rs.getBoolean("hideBirthday"));
        per.setAbout(null);
        per.setMail(rs.getString("mail"));
        per.setMaleGender(rs.getBoolean("maleGender"));
        per.setName(rs.getString("name"));
        per.setPassword(rs.getString("password"));
        per.setSurname(rs.getString("surname"));
        per.setUsername(rs.getString("username"));
        return per;
    }

}
