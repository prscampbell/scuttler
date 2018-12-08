package eu.scuttlercup.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Registration")
@Table(name = "REGISTRATION")
public class Registration 
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    
    @Column(name = "TOURNAMENT_ID")
    private long tournamentId;
    
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    
    public Registration() { }
    
    public Registration(long tournamentId, String accountId) {
        this.tournamentId = tournamentId;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public long getTournamentId() {
        return tournamentId;
    }

    public String getAccountId() {
        return accountId;
    }
    
}
