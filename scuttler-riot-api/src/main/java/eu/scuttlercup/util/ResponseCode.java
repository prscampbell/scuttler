package eu.scuttlercup.util;

public class ResponseCode
{

    public final static int HTTP_OK = 200;
    public final static int HTTP_BAD_REQUEST = 400;
    public final static int HTTP_UNAUTHORIZED = 401;
    public final static int HTTP_FORBIDDEN = 403;
    public final static int HTTP_NOT_FOUND = 404;
    public final static int HTTP_UNSUPPORTED_MEDIA_TYPE = 415;
    public final static int HTTP_RATE_LIMIT_EXCEEDED = 429;
    public final static int HTTP_INTERNAL_SERVER_ERROR = 500;
    public final static int HTTP_SERVICE_UNAVALIABLE = 503;
    
    public static boolean verify(int code) 
    {
        if(code == 200) {
            return true;
        }
        
        switch(code) 
        {
            case HTTP_BAD_REQUEST:
                badRequest(); break;
            case HTTP_UNAUTHORIZED:
                unauthorized(); break;
            case HTTP_FORBIDDEN:
                forbidden(); break;
            case HTTP_NOT_FOUND:
                notFound(); break;
            case HTTP_UNSUPPORTED_MEDIA_TYPE:
                unsupportedMediaType(); break;
            case HTTP_RATE_LIMIT_EXCEEDED:
                rateLimitExceeded(); break;
            case HTTP_INTERNAL_SERVER_ERROR:
                internalServerError(); break;
            case HTTP_SERVICE_UNAVALIABLE:
                serviceUnavailable(); break;
            default:
                unexpectedError(code); break;
        }
        
        return false;
    }
    
    private static void badRequest() 
    {
        System.out.println("Request returned code " + HTTP_BAD_REQUEST + " (Bad Request)");
        System.out.println("This error indicates that there is a syntax error in the request and the request has therefore been denied. You should not continue to make similar requests without modifying the syntax or the requests being made.");
        System.out.println("Common reasons:");
        System.out.println(" > A provided parameter is in the wrong format (e.g., a string instead of an integer).");
        System.out.println(" > A provided parameter is invalid (e.g., beginTime and startTime specify a time range that is too large).");
        System.out.println(" > A required parameter was not provided.");
    }
    
    private static void unauthorized() 
    {
        System.out.println("Request returned code " + HTTP_UNAUTHORIZED + " (Unauthorized)");
        System.out.println("This error indicates that the request being made did not contain the necessary authentication credentials (e.g., an API key) and therefore you were denied access. You should not continue to make similar requests without including an API key in the request.");
        System.out.println("Common reasons:");
        System.out.println(" > An API key has not been included in the request.");
    }
    
    private static void forbidden() 
    {
        System.out.println("Request returned code " + HTTP_FORBIDDEN + " (Forbidden)");
        System.out.println("This error indicates that the server understood the request but refuses to authorize it. There is no distinction made between an invalid path or invalid authorization credentials (e.g., an API key). You should not continue to make similar requests.");
        System.out.println("Common reasons:");
        System.out.println(" > An invalid API key was provided with the API request.");
        System.out.println(" > A blacklisted API key was provided with the API request.");
        System.out.println(" > The API request was for an incorrect or unsupported path.");
    }
    
    private static void notFound() 
    {
        System.out.println("Request returned code " + HTTP_NOT_FOUND + " (Not Found)");
        System.out.println("This error indicates that the server has not found a match for the API request being made. No indication is given whether the condition is temporary or permanent.");
        System.out.println("Common reasons:");
        System.out.println(" > The ID or name provided does not match any existing resource (e.g., there is no summoner matching the specified ID).");
        System.out.println(" > There are no resources that match the parameters specified.");
        
    }
    
    private static void unsupportedMediaType() 
    {
        System.out.println("Request returned code " + HTTP_UNSUPPORTED_MEDIA_TYPE + " (Unsupported Media Type)");
        System.out.println("This error indicates that the server is refusing to service the request because the body of the request is in a format that is not supported.");
        System.out.println("Common reasons:");
        System.out.println(" > The Content-Type header was not appropriately set.");
    }
    
    private static void rateLimitExceeded() 
    {
        System.out.println("Request returned code " + HTTP_RATE_LIMIT_EXCEEDED + " (Rate Limit Exceeded)");
        System.out.println("This error indicates that the application has exhausted its maximum number of allotted API calls allowed for a given duration. If the client receives a Rate Limit Exceeded response you should process this response and halt future API calls for the duration, in seconds, indicated by the Retry-After header. Applications that are in violation of this policy may have their access disabled to preserve the integrity of the API. Please refer to our Rate Limiting documentation for more information on determining if you have been rate limited and how to avoid it.");
        System.out.println("Common reasons:");
        System.out.println(" > Unregulated API calls.");
    }
    
    private static void internalServerError() 
    {
        System.out.println("Request returned code " + HTTP_INTERNAL_SERVER_ERROR + " (Internal Server Error)");
        System.out.println("This error indicates an unexpected condition or exception which prevented the server from fulfilling an API request.");
    }
    
    private static void serviceUnavailable() 
    {
        System.out.println("Request returned code " + HTTP_SERVICE_UNAVALIABLE + " (Service Unavailable)");
        System.out.println("This error indicates the server is currently unavailable to handle requests because of an unknown reason. The Service Unavailable response implies a temporary condition which will be alleviated after some delay.");
    }
    
    private static void unexpectedError(int code) 
    {
        System.out.println("Request returned unexpected code " + code);
    }
    
}
