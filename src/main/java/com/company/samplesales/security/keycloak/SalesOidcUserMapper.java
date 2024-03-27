package com.company.samplesales.security.keycloak;

import com.google.common.base.Strings;
import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.usermapper.BaseOidcUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("sales_SalesOidcUserMapper")
public class SalesOidcUserMapper extends BaseOidcUserMapper<SalesJmixOidcUser> {

    private ClaimsRolesMapper claimsRolesMapper;

    public SalesOidcUserMapper(ClaimsRolesMapper claimsRolesMapper) {
        this.claimsRolesMapper = claimsRolesMapper;
    }

    @Override
    protected SalesJmixOidcUser initJmixUser(OidcUser oidcUser) {
        return new SalesJmixOidcUser();
    }

    @Override
    protected void populateUserAttributes(OidcUser oidcUser, SalesJmixOidcUser jmixUser) {
        String fullName = oidcUser.getUserInfo().getFullName();
        jmixUser.setFormattedName(Strings.isNullOrEmpty(fullName)
                ? oidcUser.getPreferredUsername()
                : fullName + " [" + oidcUser.getPreferredUsername() + "]");
    }

    @Override
    protected void populateUserAuthorities(OidcUser oidcUser, SalesJmixOidcUser jmixUser) {
        Collection<? extends GrantedAuthority> grantedAuthorities = claimsRolesMapper.toGrantedAuthorities(oidcUser.getClaims());
        jmixUser.setAuthorities(grantedAuthorities);
    }
}
