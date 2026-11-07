package pl.engineerbookplus.www.model.domain.wizard;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPersonPreparedStatementWizard implements PreparedStatementWizard<Person> {

    @Override
    public PreparedStatement createPreparedStatement(Connection c, Person p) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "insert into 16120792_nebp.persons (birthday, enabled, familyName, hideBirthday, idAbout, mail, maleGender, name, password, surrname, username) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, (p.getBirthday().getYear() + 1900) + "-" + (((p.getBirthday().getMonth() + 1) > 9) ? (p.getBirthday().getMonth() + 1) : "0" + (p.getBirthday().getMonth() + 1)) + "-" + (((p.getBirthday().getDate()) > 9) ? (p.getBirthday().getDate()) : "0" + (p.getBirthday().getDate())));
        ps.setBoolean(2, p.isEnabled());
        ps.setString(3, p.getFamilyName());
        ps.setBoolean(4, p.isHideBirthday());
        if (p.getAbout() == null) {
            ps.setObject(5, null);
        } else {
            ps.setInt(5, p.getAbout().getId());
        }
        ps.setString(6, p.getMail());
        ps.setBoolean(7, p.isMaleGender());
        ps.setString(8, p.getName());
        ps.setString(9, p.getPassword());
        ps.setString(10, p.getSurname());
        ps.setString(11, p.getUsername());
        return ps;
    }

}
