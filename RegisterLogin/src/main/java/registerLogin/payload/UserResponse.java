package registerLogin.payload;

import lombok.Data;

@Data
public class UserResponse {

    private String message;
    private String token;
}
