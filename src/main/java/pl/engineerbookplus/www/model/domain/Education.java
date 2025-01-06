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
@Table(catalog = "16120792_nebp", name = "educations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e"),
        @NamedQuery(name = "Education.findById", query = "SELECT e FROM Education e WHERE e.id = :id"),
        @NamedQuery(name = "Education.findByType", query = "SELECT e FROM Education e WHERE e.type = :type"),
        @NamedQuery(name = "Education.findByStartDate", query = "SELECT e FROM Education e WHERE e.startDate = :startDate"),
        @NamedQuery(name = "Education.findByEndDate", query = "SELECT e FROM Education e WHERE e.endDate = :endDate"),
        @NamedQuery(name = "Education.findByCompletion", query = "SELECT e FROM Education e WHERE e.completion = :completion"),
        @NamedQuery(name = "Education.findByHide", query = "SELECT e FROM Education e WHERE e.hide = :hide")})
public class Education implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int type;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String schoolDescription;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean completion;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hide;
    @Transient
    @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person idPerson;

    public Education(Integer id) {
        this.id = id;
    }

    public Education(Integer id, int type, String schoolDescription, Date startDate, boolean completion, boolean hide) {
        this.id = id;
        this.type = type;
        this.schoolDescription = schoolDescription;
        this.startDate = startDate;
        this.completion = completion;
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
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
