package com.springboot.shiro.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author xutianhong
 * @Date 2020/9/23 16:12
 */
@Component
public class JwtUtil {
    private final int millisecondToSecond = 1000;
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkngXDsTy7Vuo1CcDu0mUP+3lN2JarCYJa6lDJhbBwUstEE/HCTtZwsgoWFJFb2T3WAxCsXlxYKo9DiWJbBeMV7TRTzKbAz0Wp4M6ygAWo3NLlzeOa+4cMOVQ9kq0doHormzxzA/tHXTx9BYBtZBJryuXL3+qxWsb0CLtMa/W6Gs1+zKxo3FGvW0t1QnoZfoJV1VF8kvSEsDpsmV3kqerE0cVPkn/BOZ22x32F8Q3k3g0+g9npWIUZ3zVn/pemuG7vYTtPjBKAJCKeFF86gW2Fqb0EjrGCZx7BCO4gV3Gb6vng2mLEb3DkIcGBelmE0rnYAmlYAtJnSxWVI8gNUhwlwIDAQAB";
    private static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSeBcOxPLtW6jUJwO7SZQ/7eU3YlqsJglrqUMmFsHBSy0QT8cJO1nCyChYUkVvZPdYDEKxeXFgqj0OJYlsF4xXtNFPMpsDPRangzrKABajc0uXN45r7hww5VD2SrR2geiubPHMD+0ddPH0FgG1kEmvK5cvf6rFaxvQIu0xr9boazX7MrGjcUa9bS3VCehl+glXVUXyS9ISwOmyZXeSp6sTRxU+Sf8E5nbbHfYXxDeTeDT6D2elYhRnfNWf+l6a4bu9hO0+MEoAkIp4UXzqBbYWpvQSOsYJnHsEI7iBXcZvq+eDaYsRvcOQhwYF6WYTSudgCaVgC0mdLFZUjyA1SHCXAgMBAAECggEATH1chUEDIEOa0lghkcAmJJCnAJpjgb1HdCrrv0tpGfmZaghPiBmvkZIXx7AHRNo0dY3Jkoc6V2HwCqA9uO4/Q1pRaIuj+jf/ybE1lWIgcHF4i1rfDKxHmp1W/5gZ0D2dduG8EHZMvPJinLaVY+0bHdik1pBUbU63MqXauvT6RVtUgZZP1hoD6O23ZYwMiCtib9gkisniaoLBn1CIZ4MDcWbn6dxne1yJLDeoVpP4ZFU3sVlmnekh5KcUhklNkdMvLOSeuchP0dFXDgQtwUDfJNd5JWFOVCo1f4yl3pAYZTvQp/+v4+6LDff4z4ft5HPjPrktGT85GRQDdJM6pX6u4QKBgQDWfyYj5UbiJxpVD+M4SChY/WIY8mSkYIEH5Tj/KfKddCOUdx4kBacMl47Q/131c1y/ztasVwVPA1CObNQthVcIftyuqhdp06VvO2GmiMlaBMAG0vB4mZ7Y47Tr5vW4atpV/G7iNpCQ5GriTLzg9VFAWzRT0/tNfOjmeUtBqukz5wKBgQCuz0WbdyMwjq6T9d83gFKbrNhupNMOVmjuSO9oDNQOyvnQrDYZsghOfEAs4VCoEwkI01bkE6eIxKn93xlEfG/ztqqcZ0y40LCbYZgbdB4s2966lj2n+IZp22wLIjZGuLlRxRb6uBuD+aGsLt9NO1fN6OwtsVcE3ie2X2pDFIBH0QKBgBf8tiYzgL7Pwqkakr17jE+PXoYNrWWYiTV50+rtJP2ovEXhIFTE8Q5+cHE87aQCV6/3kJhLTwOsCjkzQHMqwzXnAgTibqXeDlUnHd+C0omnmFXrBwtlmpcEt/ndpUMPaAVPqpLPqVEayj0uebzqkkCRMoPhY+wheOY1UE4yr8unAoGBAKawBlPEOs3mgBmebgbiWeMP4iE/EfGt+8iby42QDfU9HxEX5U6DMOCGIjupbduWqxq7SzOYejw0K1RlFBb2AVqHaBBTgBkLBhxj50Lmao+j6LJK6OfuV11BWbkUGmU9Z5jNMqhIPhSOSNeCqnNejs9Nt0eqvqtpmryQJydnuRrBAoGART1cwZj3Z3yLAqbWaxoOkCCoT0EGPTKi760R2eNaKwHn+lP9kO8y2DsvmtkwmANUoBOEW4tyqRjFxAAK+U5K6pkM9PHKq/q8+HbIUbCY1gg3KINHProsbFv3VCX+dcjuwMxtEPXna9GSTyn2ycMsiKLnVAuMBVG1antVQKrfGJ8=";


    public String generateToken(JSONObject claims){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 100000 * millisecondToSecond);
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        JWTCreator.Builder builder = JWT.create()
                .withExpiresAt(expiryDate);
        builder.withClaim("userInfo", claims.toJSONString());
        String token = builder.sign(algorithm);
        return token;
    }

    public static Map getClaimsFromToken(String token){
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            map = decodedJWT.getClaims();
            Claim infoClaim = map.get("userInfo");
            return JSON.parseObject(infoClaim.asString());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUsername(String token){
        return JwtUtil.getClaimsFromToken(token).get("username").toString();
    }

    public static String getUserRole(String token){
        return JwtUtil.getClaimsFromToken(token).get("role").toString();
    }

    public static String getUsername(ServletRequest request){
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return JwtUtil.getUsername(httpRequest.getHeader("Authorization"));
    }
}
