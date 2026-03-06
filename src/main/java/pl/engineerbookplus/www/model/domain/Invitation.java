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
@Table(catalog = "16120792_nebp", name = "invitations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i"),
        @NamedQuery(name = "Invitation.findById", query = "SELECT i FROM Invitation i WHERE i.id = :id"),
        @NamedQuery(name = "Invitation.findBySend", query = "SELECT i FROM Invitation i WHERE i.send = :send"),
        @NamedQuery(name = "Invitation.findByStamp", query = "SELECT i FROM Invitation i WHERE i.stamp = :stamp"),
        @NamedQuery(name = "Invitation.findByConfirmation", query = "SELECT i FROM Invitation i WHERE i.confirmation = :confirmation")})
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date send;
    @Temporal(TemporalType.TIMESTAMP)
    private Date stamp;
    private Boolean confirmation;
    @Transient
    @JoinColumn(name = "sender", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person sender;
    @Transient
    @JoinColumn(name = "recipient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person recipient;

    public Invitation(Integer id) {
        this.id = id;
    }

    public Invitation(Integer id, Date send) {
        this.id = id;
        this.send = send;
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
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
