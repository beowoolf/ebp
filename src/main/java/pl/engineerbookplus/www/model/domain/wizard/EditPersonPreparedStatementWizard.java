package pl.engineerbookplus.www.model.domain.wizard;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditPersonPreparedStatementWizard implements PreparedStatementWizard<Person> {

    @Override
    public PreparedStatement createPreparedStatement(Connection c, Person p) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "update 16120792_nebp.persons set birthday=?, enabled=?, familyName=?, hideBirthday=?, idAbout=?, mail=?, maleGender=?, name=?, password=?, surrname=?, username=? where id=?"
        );
        ps.setDate(1, (Date) p.getBirthday());
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
        ps.setInt(12, p.getId());
        return ps;
    }

}
