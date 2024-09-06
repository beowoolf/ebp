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
@Table(catalog = "16120792_nebp", name = "homepages", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Homepage.findAll", query = "SELECT h FROM Homepage h"),
        @NamedQuery(name = "Homepage.findById", query = "SELECT h FROM Homepage h WHERE h.id = :id"),
        @NamedQuery(name = "Homepage.findByValue", query = "SELECT h FROM Homepage h WHERE h.value = :value"),
        @NamedQuery(name = "Homepage.findByHide", query = "SELECT h FROM Homepage h WHERE h.hide = :hide")})
public class Homepage implements Serializable {

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

    public Homepage(Integer id) {
        this.id = id;
    }

    public Homepage(Integer id, String value, boolean hide) {
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
        if (!(object instanceof Homepage)) {
            return false;
        }
        Homepage other = (Homepage) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
