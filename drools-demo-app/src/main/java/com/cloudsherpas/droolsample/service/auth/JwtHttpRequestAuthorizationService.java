package com.cloudsherpas.droolsample.service.auth;

import com.cloudsherpas.droolsample.api.resource.UserResource;
import com.cloudsherpas.droolsample.service.UserService;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.joda.time.Instant;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JwtHttpRequestAuthorizationService implements HttpRequestAuthorizationService {

    private static final String APP_NAME = "g4s";
    private static final String SECRET_KEY = "secretkey";

    private static Checker checker;

    public JwtHttpRequestAuthorizationService() {
        checker = new CheckerImpl(APP_NAME);
    }

    @Autowired
    private UserService userService;

    @Override
    public String generateToken(final Date expiry, final UserResource user, final String origin) {
        try {
            HmacSHA256Signer signer = new HmacSHA256Signer(APP_NAME, null, SECRET_KEY.getBytes());
            Calendar now = Calendar.getInstance();

            JsonToken token = new JsonToken(signer);
            token.setAudience(APP_NAME);
            token.setIssuedAt(new Instant(now.getTimeInMillis()));
            //token.setExpiration(new Instant(expiry.getTime()));

            JsonObject payload = token.getPayloadAsJsonObject();
            payload.addProperty("agent", BCrypt.hashpw(origin, BCrypt.gensalt()));
            payload.addProperty("username", user.getUsername());
            return token.serializeAndSign();
        } catch (final InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUsernameFromToken(final String token, final String origin) {
        try {
            if (token != null) {
                Verifier hmacVerifier = new HmacSHA256Verifier(SECRET_KEY.getBytes());
                VerifierProvider hmacLocator = new VerifierProviderImpl(hmacVerifier);
                VerifierProviders locators = new VerifierProviders();
                locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);

                JsonTokenParser parser = new JsonTokenParser(locators, checker);
                JsonToken jt = parser.verifyAndDeserialize(token);
                JsonObject payload = jt.getPayloadAsJsonObject();
                String claimIp = payload.getAsJsonPrimitive("agent").getAsString();
                String username = payload.getAsJsonPrimitive("username").getAsString();
                System.out.println("========USERNAME: " + username);
                if (BCrypt.checkpw(origin, claimIp)) {
                    return username;
                } else {
                    return null;
                }
            }

            return null;
        } catch (final SignatureException e) {
            return null;
        } catch (final InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isTokenValid(String token, String origin, String username) {
        return (getUsernameFromToken(token, origin).equals(username));
    }

    public static class VerifierProviderImpl implements VerifierProvider {

        private final Verifier verifier;

        public VerifierProviderImpl(Verifier verifier) {
            this.verifier = verifier;
        }

        @Override
        public List<Verifier> findVerifier(String id, String key) {
            return Lists.newArrayList(verifier);
        }
    }

    public static class CheckerImpl implements Checker {

        private String audience;

        public CheckerImpl(final String audience) {
            this.audience = audience;
        }

        @Override
        public void check(final JsonObject payload) throws SignatureException {
            String audience = payload.getAsJsonPrimitive("aud").getAsString();

            if (!audience.equals(this.audience)) {
                throw new SignatureException("Audience does not match");
            }
        }

    }
}
