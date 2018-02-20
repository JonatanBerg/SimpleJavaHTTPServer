package request;
/**
 * Extract the information needed from the HTTP Request
 */
public class RequestParser
{
    
    private RequestType type;
    private String path;
    private String httpVersion;
    
    /**
     * Constructor that takes the response as a String
     * @param inputRequest the HTTP Response as a String
     */
    public RequestParser(String inputRequest)
    {
    
        String[] request = inputRequest.split("\n");
    
        String[] firstLine = request[0].split(" ");
        
        try {
        	type = RequestType.valueOf(firstLine[0]);
        } catch (IllegalArgumentException e) {
        	type = RequestType.ILLEGAL;
        }
        
        
        if(firstLine.length == 3)
        {
            path = firstLine[1];
            httpVersion = firstLine[2];
        }
        else if(firstLine.length == 2)
        {
            path = "/";
            httpVersion = firstLine[1];
        }
        else
        {
            throw new IllegalArgumentException("The request was in illegal form");
        }
        
        
        if(isPathAFile(path) == false)
        {
          if(path.charAt(path.length()-1) == '/')
          {
             path+= "index.html";
          }
          else
          {
             path+="/index.html";
          }
       }
    }

    public boolean isPathAFile(String path)
    {
        int counter = 0;
        if(path.length() > 4)
        {
            counter = path.length() - 5;
        }
        while(counter < path.length()-1)
        {
            if(path.charAt(counter) == '.')
            {
                return true;
            }
            counter++;
        }
        // TODO debug
        System.out.println("Parse path");
        return false;
    }
    
    
    /* Getters & Setters */
    public RequestType getType(){return type;}
    public String getPath(){return path;}
    public String getHttpVersion(){return httpVersion;}
    
    public void setType(RequestType requestType)
    {
        type = requestType;
    }
}
