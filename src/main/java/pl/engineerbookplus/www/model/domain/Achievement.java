package pl.engineerbookplus.www.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(catalog = "16120792_nebp", name = "achievements", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Achievement.findAll", query = "SELECT a FROM Achievement a"),
        @NamedQuery(name = "Achievement.findById", query = "SELECT a FROM Achievement a WHERE a.id = :id"),
        @NamedQuery(name = "Achievement.findByDateAchievement", query = "SELECT a FROM Achievement a WHERE a.dateAchievement = :dateAchievement"),
        @NamedQuery(name = "Achievement.findByHide", query = "SELECT a FROM Achievement a WHERE a.hide = :hide")})
public class Achievement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAchievement;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String achievementDescription;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hide;
    @Transient
    @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person idPerson;

    public Achievement(Integer id) {
        this.id = id;
    }

    public Achievement(Integer id, Date dateAchievement, String achievementDescription, boolean hide) {
        this.id = id;
        this.dateAchievement = dateAchievement;
        this.achievementDescription = achievementDescription;
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
        if (!(object instanceof Achievement)) {
            return false;
        }
        Achievement other = (Achievement) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
