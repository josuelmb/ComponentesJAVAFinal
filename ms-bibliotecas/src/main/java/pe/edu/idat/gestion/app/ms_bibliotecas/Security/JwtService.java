package pe.edu.idat.gestion.app.ms_bibliotecas.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//Para definir las variables que me permiten, generar validar y obtener los datos del token
@Slf4j
@Service
public class JwtService
{
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiracion;

    //1.- Generar token
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +expiracion))
                .signWith(getSigninKey())
                .compact();
    }

    //Convierte la secretKey en clave valida para JWT
    public SecretKey getSigninKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    //- Extraer datos
    //Saca el usuario del token
    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public <T>  T extractClaim(String token, Function<Claims, T> resolver) //Metodo que puede devolver cualquier tipo de dato
    {
        return resolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    public long getExpirationms()
    {
        return expiracion;
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    //- Validar token si esta activo por su tiempo de expiracion
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        try {
            final String username =  extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }
        catch (Exception e)
        {
            log.warn("Token invalido: {} ", e.getMessage());
            return false;
        }
    }

}