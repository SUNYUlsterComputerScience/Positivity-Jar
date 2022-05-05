/**
*@author Emmanuel Cruz
*/



package ricepudding.auth;

import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "${'java:global/jdbc/positivity_jar'}",
        callerQuery = "#{'select password from user_accounts where name = ?'}",
        groupsQuery = "select group_name from user_groups where name = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}"
)
@ApplicationScoped
@Named
public class ApplicationConfig {

    public String[] getDyna() {
        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }

}
