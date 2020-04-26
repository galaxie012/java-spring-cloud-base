package hou.tidaa.core.security.token;

public interface TokenSignService<T> {
    Token<T> sign(IdentityDto auth);
}
