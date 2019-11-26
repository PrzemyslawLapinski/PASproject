import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class IdentityStoreTest implements IdentityStore {

    private static final Map<String, String> unsecureStore = new HashMap<>();

    public IdentityStoreTest() {
        // Don't do this at home, highly unsecure!
        unsecureStore.put("Przemek", "Przemek");
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        String caller = usernamePasswordCredential.getCaller();
        String pwd = usernamePasswordCredential.getPasswordAsString();

        // just for the exercise, assign the role based on the user name
        String role = "foo";
        if ( caller.contains("a") ) {
            role = "bar";
        } // else foo!

        if (usernamePasswordCredential.compareTo(caller,unsecureStore.get(caller))) {
            // return a VALID result with the caller and the set of groups s/he belongs to.
            return new CredentialValidationResult(caller, new HashSet<>(asList(role)));
        }
        return INVALID_RESULT;

    }
}
