package security;

import accounter.model.Accounter;
import accounter.model.AccounterMenager;
import accounter.model.ResourceUser;
import resource.model.ResourceMenager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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



   @Inject
    AccounterMenager accounterMenager;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        Accounter accounter =accounterMenager.getAll().stream().filter(p -> p.getLogin().equals(login.getCaller()) &&
                                                                            p.isActive)
                                            .findFirst().orElse(null);

        if(accounter != null) {
            return new CredentialValidationResult(accounter.login, new HashSet<>(Arrays.asList(accounter.getResourceType())));
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }


    }
}
