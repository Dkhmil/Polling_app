package pollingapp.payload;

public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToten) {
        this.accessToken = accessToten;
    }

    public String getAccessToten() {
        return accessToken;
    }

    public void setAccessToten(String accessToten) {
        this.accessToken = accessToten;
    }

    public String getTokentype() {
        return tokenType;
    }

    public void setTokentype(String tokentype) {
        this.tokenType = tokentype;
    }
}
