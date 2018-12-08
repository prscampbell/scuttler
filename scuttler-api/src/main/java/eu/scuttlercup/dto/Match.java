package eu.scuttlercup.dto;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.scuttlercup.riot.dao.Summoners;
import eu.scuttlercup.riot.dto.Summoner;

@Entity(name = "MatchEntry")
@Table(name = "MATCH_ENTRY")
public class Match 
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "MATCH_NUMBER")
    private long matchNumber;

    @Column(name = "TOURNAMENT_ID")
    private long tournamentId;
    
    @ManyToOne
    @JoinColumn(name = "RED_SIDE_ID")
    private Registration redSide;

    @ManyToOne
    @JoinColumn(name = "BLUE_SIDE_ID")
    private Registration blueSide;

    @Column(name = "WINNER")
    private long winner;

    public Match() { }
    
    public long getId() {
        return id;
    }

    @JsonProperty("match_number")
    public long getMatchNumber() {
        return matchNumber;
    }

    @JsonIgnore
    public long getTournamentId() {
        return tournamentId;
    }

    @JsonIgnore
    public Registration getRedSidePlayer() {
        return redSide;
    }
    
    @JsonProperty("red_side")
    public String getRedSide() 
    {
        Optional<Summoner> summoner = Summoners.byAccountId(redSide.getAccountId());
        
        if(!summoner.isPresent()) {
            return null;
        }
        
        return summoner.get().getName();
    }

    @JsonIgnore
    public Registration getBlueSidePlayer() {
        return blueSide;
    }
    
    @JsonProperty("blue_side")
    public String getBlueSide() 
    {
        Optional<Summoner> summoner = Summoners.byAccountId(blueSide.getAccountId());
        
        if(!summoner.isPresent()) {
            return null;
        }
        
        return summoner.get().getName();
    }

    public long getWinner() {
        return winner;
    }
    
}
