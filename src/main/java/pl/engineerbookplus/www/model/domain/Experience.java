package pl.engineerbookplus.www.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(catalog = "16120792_nebp", name = "experiences", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
        @NamedQuery(name = "Experience.findById", query = "SELECT e FROM Experience e WHERE e.id = :id"),
        @NamedQuery(name = "Experience.findByEmployedIn", query = "SELECT e FROM Experience e WHERE e.employedIn = :employedIn"),
        @NamedQuery(name = "Experience.findByPosition", query = "SELECT e FROM Experience e WHERE e.position = :position"),
        @NamedQuery(name = "Experience.findByStartDate", query = "SELECT e FROM Experience e WHERE e.startDate = :startDate"),
        @NamedQuery(name = "Experience.findByEndDate", query = "SELECT e FROM Experience e WHERE e.endDate = :endDate"),
        @NamedQuery(name = "Experience.findByHide", query = "SELECT e FROM Experience e WHERE e.hide = :hide")})
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String employedIn;
    @Column(length = 45)
    private String position;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hide;
    @Transient
    @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person idPerson;

    public Experience(Integer id) {
        this.id = id;
    }

    public Experience(Integer id, String employedIn, Date startDate, boolean hide) {
        this.id = id;
        this.employedIn = employedIn;
        this.startDate = startDate;
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
        if (!(object instanceof Experience)) {
            return false;
        }
        Experience other = (Experience) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
