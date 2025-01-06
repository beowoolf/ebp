package pl.engineerbookplus.www.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Document(collection = "messages")
@Table(catalog = "16120792_nebp", name = "messages", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
        @NamedQuery(name = "Message.findByFrom", query = "SELECT m FROM Message m WHERE m.senderId = :from"),
        @NamedQuery(name = "Message.findByTo", query = "SELECT m FROM Message m WHERE m.recipientId = :to"),
        @NamedQuery(name = "Message.findByInterlocutor", query = "SELECT m FROM Message m WHERE (m.senderId = :p1 AND m.recipientId = :p2) OR (m.senderId = :p2 AND m.recipientId = :p1)"),
        @NamedQuery(name = "Message.findBySend", query = "SELECT m FROM Message m WHERE m.send = :send"),
        @NamedQuery(name = "Message.findByRead", query = "SELECT m FROM Message m WHERE m.readTime = :read"),
        @NamedQuery(name = "Message.findByHasRead", query = "SELECT m FROM Message m WHERE m.hasRead = :hasRead"),
        @NamedQuery(name = "Message.findBySenderDelete", query = "SELECT m FROM Message m WHERE m.senderDelete = :senderDelete"),
        @NamedQuery(name = "Message.findByRecipientDelete", query = "SELECT m FROM Message m WHERE m.recipientDelete = :recipientDelete")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @org.springframework.data.annotation.Transient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Transient
    @org.springframework.data.annotation.Id
    private ObjectId idMessage;
    @Basic(optional = false)
    @Column(nullable = false)
    private int senderId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int recipientId;
    @Transient
    private ObjectId recipient;
    @Transient
    private ObjectId sender;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date send;
    @Temporal(TemporalType.TIMESTAMP)
    private Date readTime;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hasRead;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean senderDelete;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean recipientDelete;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String message;

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, int senderId, int recipientId, Date send, boolean hasRead, boolean senderDelete, boolean recipientDelete, String message) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.send = send;
        this.hasRead = hasRead;
        this.senderDelete = senderDelete;
        this.recipientDelete = recipientDelete;
        this.message = message;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}
