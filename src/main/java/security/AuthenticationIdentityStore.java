package security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.Arrays.asList;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class AuthenticationIdentityStore implements IdentityStore {

    private static final Map<String, String> unsecureStore = new HashMap<>();

    public AuthenticationIdentityStore() {
        // Don't do this at home, highly unsecure!
        unsecureStore.put("student", "student");
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        if (login.getCaller().equals("student") && login.getPasswordAsString().equals("student")) {
            return new CredentialValidationResult("admin", new HashSet<>(Arrays.asList("ADMIN")));
        }  else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
