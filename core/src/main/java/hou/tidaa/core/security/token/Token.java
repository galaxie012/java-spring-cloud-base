package hou.tidaa.core.security.token;

import lombok.Data;

@Data
public class Token<T> {
    private final Type type;
    private final T token;

    public static<T> Token<T> jwt(T token){
        return new Token<T>(Type.JWT, token);
    }

    public enum Type{
        JWT
    }

}
;