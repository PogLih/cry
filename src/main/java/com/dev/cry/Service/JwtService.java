package com.dev.cry.Service;

import com.dev.cry.Entity.User;
import com.dev.cry.Utils.DateUtils;
import com.dev.cry.model.UserPrinciple;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "11111111111111111111111111111111";

    private static final long EXPIRE_TIME = 86400000000L;

    private static final Logger logger = Logger.getLogger(JwtService.class.getName());

    public String generateTokenLogin(UserPrinciple userPrinciple) {
        Date issueAt = DateUtils.LocalDateTimeToDate(LocalDateTime.now());
        Date expiredAt = DateUtils.LocalDateTimeToDate(LocalDateTime.now().plus(EXPIRE_TIME, ChronoUnit.MICROS));


        return Jwts.builder()

                .setSubject((userPrinciple.getUsername()))

                .setIssuedAt(issueAt)

                .setExpiration(expiredAt)

                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)

                .compact();

    }



    public boolean validateJwtToken(String authToken) {

        try {

            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);

            return true;

        } catch (SignatureException e) {

            logger.error("Invalid JWT signature -> Message: {} ", e);

        } catch (MalformedJwtException e) {

            logger.error("Invalid JWT token -> Message: {}", e);

        } catch (ExpiredJwtException e) {

            logger.error("Expired JWT token -> Message: {}", e);

        } catch (UnsupportedJwtException e) {

            logger.error("Unsupported JWT token -> Message: {}", e);

        } catch (IllegalArgumentException e) {

            logger.error("JWT claims string is empty -> Message: {}", e);

        }



        return false;

    }



    public String getUserNameFromJwtToken(String token) {



        String userName = Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody().getSubject();

        return userName;

    }

}
