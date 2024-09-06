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
@Table(catalog = "16120792_nebp", name = "languages", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l"),
        @NamedQuery(name = "Language.findById", query = "SELECT l FROM Language l WHERE l.id = :id"),
        @NamedQuery(name = "Language.findByValue", query = "SELECT l FROM Language l WHERE l.value = :value"),
        @NamedQuery(name = "Language.findByHide", query = "SELECT l FROM Language l WHERE l.hide = :hide")})
public class Language implements Serializable {

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

    public Language(Integer id) {
        this.id = id;
    }

    public Language(Integer id, String value, boolean hide) {
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
