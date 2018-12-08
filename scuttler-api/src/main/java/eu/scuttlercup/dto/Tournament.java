package eu.scuttlercup.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "Tournament")
@Table(name = "TOURNAMENT")
public class Tournament 
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIMESTAMP")
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DEADLINE")
    private Date registration;
    
    public Tournament() { }

    public long getId() {
        return id;
    }

    @JsonProperty("start_timestamp")
    public Date getStart() {
        return start;
    }

    @JsonProperty("registration_deadline")
    public Date getRegistration() {
        return registration;
    }
    
    @JsonIgnore
    public boolean hasRegistrationDeadlinePassed() {
        return registration.before(new Date());
    }
    
}
