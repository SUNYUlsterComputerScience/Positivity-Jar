package ricepudding.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

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