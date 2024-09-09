package com.orionsolution.auth.utility;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
public final class JwtUtility extends DecodeUtility {

    /**
     * @param token JWT
     * @return subject from token
     */
    public static String getSubjectFromToken(String token) {
        String tokenWithoutSign = token.split("\\.")[1];
        String jsonString = DecodeUtility.getDecoded(tokenWithoutSign);
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString("sub");
    }


}
