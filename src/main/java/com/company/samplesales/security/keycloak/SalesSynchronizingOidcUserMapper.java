package com.company.samplesales.security.keycloak;

import com.company.samplesales.entity.User;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.UserRepository;
import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.usermapper.SynchronizingOidcUserMapper;
import io.jmix.security.role.RoleGrantedAuthorityUtils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component("sales_SalesSynchronizingOidcUserMapper")
public class SalesSynchronizingOidcUserMapper extends SynchronizingOidcUserMapper<User> {

    public SalesSynchronizingOidcUserMapper(UnconstrainedDataManager dataManager,
                                            UserRepository userRepository,
                                            ClaimsRolesMapper claimsRolesMapper,
                                            RoleGrantedAuthorityUtils roleGrantedAuthorityUtils) {
        super(dataManager, userRepository, claimsRolesMapper, roleGrantedAuthorityUtils);

        setSynchronizeRoleAssignments(true);
    }

    @Override
    protected Class<User> getApplicationUserClass() {
        return User.class;
    }

    @Override
    protected void populateUserAttributes(OidcUser oidcUser, User jmixUser) {
        jmixUser.setUsername(oidcUser.getName());
        jmixUser.setFirstName(oidcUser.getGivenName());
        jmixUser.setLastName(oidcUser.getFamilyName());
        jmixUser.setEmail(oidcUser.getEmail());
    }
}
