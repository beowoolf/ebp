package pl.engineerbookplus.www.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(catalog = "16120792_nebp", name = "gg", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Gg.findAll", query = "SELECT g FROM Gg g"),
        @NamedQuery(name = "Gg.findById", query = "SELECT g FROM Gg g WHERE g.id = :id"),
        @NamedQuery(name = "Gg.findByValue", query = "SELECT g FROM Gg g WHERE g.value = :value"),
        @NamedQuery(name = "Gg.findByHide", query = "SELECT g FROM Gg g WHERE g.hide = :hide")})
public class Gg implements Serializable {

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

    public Gg(Integer id) {
        this.id = id;
    }

    public Gg(Integer id, String value, boolean hide) {
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
        if (!(object instanceof Gg)) {
            return false;
        }
        Gg other = (Gg) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
