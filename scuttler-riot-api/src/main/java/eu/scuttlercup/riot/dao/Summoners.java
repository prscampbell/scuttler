package eu.scuttlercup.riot.dao;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;

import eu.scuttlercup.riot.Scuttler;
import eu.scuttlercup.riot.dto.Summoner;
import eu.scuttlercup.riot.util.ResponseCode;
import okhttp3.Response;

public class Summoners 
{

    private static final BiFunction<String, String, Optional<Summoner>> getSummoner 
        = (format, key) -> convert(Scuttler.execute(String.format(format, key)));
    
    private static final String BASE_URL = "/lol/summoner/v4/summoners";
    private static final String ACCOUNT_ID_URL = BASE_URL + "/by-account/%s";
    private static final String NAME_URL = BASE_URL + "/by-name/%s";
    private static final String PUUID_URL = BASE_URL + "/by-puuid/%s";
    private static final String ID_URL = BASE_URL + "/summoners/%s";
    
    public static Optional<Summoner> byAccountId(String accountId) {
        System.out.println("Sending request to: " + String.format(ACCOUNT_ID_URL, accountId));
        return getSummoner.apply(ACCOUNT_ID_URL, accountId);
    }

    public static Optional<Summoner> byName(String name) {
        System.out.println("Sending request to: " + String.format(NAME_URL, name));
        return getSummoner.apply(NAME_URL, name);
    }

    public static Optional<Summoner> byPUUID(String puuid) {
        System.out.println("Sending request to: " + String.format(PUUID_URL, puuid));
        return getSummoner.apply(PUUID_URL, puuid);
    }

    public static Optional<Summoner> byId(String id) {
        System.out.println("Sending request to: " + String.format(ID_URL, id));
        return getSummoner.apply(ID_URL, id);
    }
    
    private static Optional<Summoner> convert(Optional<Response> optional)
    {
        if(!optional.isPresent()) {
            return Optional.empty();
        }
        
        Response response = optional.get();
        
        if(!ResponseCode.verify(response.code())) {
            return Optional.empty();
        }
        
        try 
        {
            String json = response.body().string();
            return Optional.of(Scuttler.gson().fromJson(json, Summoner.class));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return Optional.empty();
        }
        finally
        {
            if(response != null) {
                response.close();
            }
        }
    }
    
}
