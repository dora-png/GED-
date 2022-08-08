package com.microservice.ged.serviceImple;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.microservice.ged.service.AppUserService;

@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
    private LdapContextSource contextSource;

    private LdapTemplate ldapTemplate;
	
	@Override
	public List<String> findAllUser() {
		return this.getAllUserLDAPList("*", "*", "*");
	}

	@Override
	public String findUserByName(String name) {
		return this.getAllUserLDAPList(name, "*", "*").isEmpty() ? null : this.getAllUserLDAPList(name, "*", "*").get(0) ;
	}

	
	private void initUser() {
		contextSource.setUrl("ldap://localhost:8389");
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("ou=people,ou=groups,");
        contextSource.afterPropertiesSet();
        ldapTemplate = new LdapTemplate(contextSource);
	}
	
	private List<String> getAllUserLDAPList(String cn, String sn, String uid) {
		this.initUser();
        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(3000)
                .attributes("cn")
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("cn").like(cn)
                .and("sn").like(sn)
                .and("uid").like(uid);
        return ldapTemplate.search(query, new PersonAttributesMapper());
    }
	
	private List<String> getAllUserLDAPListNotLike(String cn, String sn, String uid) {
		this.initUser();
        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(3000)
                .attributes("cn")
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("cn").not().like(cn)
                .and("sn").like(sn)
                .and("uid").like(uid);
        return ldapTemplate.search(query, new PersonAttributesMapper());
    }
	
	/*private void getAllGroupLDAPList() {
        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(3000)
                //.countLimit(10)
                .attributes("cn")
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("groupOfUniqueNames")
                .and("uniqueMember").like("*");
        ldapTemplate.search(query, new PersonAttributesMapper());
    }*/

	 private class PersonAttributesMapper implements AttributesMapper<String> {
	        public String mapFromAttributes(Attributes attrs) throws NamingException {
	            String name = (String) attrs.get("cn").get();
	            return name;
	        }
	    }

	@Override
	public List<String> findAllUserByNameNotLike(String name) {
		name="Bob*";
		return this.getAllUserLDAPListNotLike(name, "*", "*");
	}

}
