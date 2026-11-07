package pl.engineerbookplus.www.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(catalog = "16120792_nebp", name = "skypes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Skype.findAll", query = "SELECT s FROM Skype s"),
        @NamedQuery(name = "Skype.findById", query = "SELECT s FROM Skype s WHERE s.id = :id"),
        @NamedQuery(name = "Skype.findByValue", query = "SELECT s FROM Skype s WHERE s.value = :value"),
        @NamedQuery(name = "Skype.findByHide", query = "SELECT s FROM Skype s WHERE s.hide = :hide")})
public class Skype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String value;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hide;
    @Transient
    @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person idPerson;

    public Skype(Integer id) {
        this.id = id;
    }

    public Skype(Integer id, String value, boolean hide) {
        this.id = id;
        this.value = value;
        this.hide = hide;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skype)) {
            return false;
        }
        Skype other = (Skype) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
