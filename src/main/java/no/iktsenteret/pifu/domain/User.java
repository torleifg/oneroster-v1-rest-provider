package no.iktsenteret.pifu.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.Role;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedCollectionSerializer;

@Entity
@Relation(value = "user", collectionRelation = "users")
public class User extends Base {

	private String username, userId, givenName, familyName, identifier, email, sms, phone;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany
	private Set<User> agents = new HashSet<>();

	@ManyToMany
	private Set<Org> orgs = new HashSet<>();

	protected User() {
	}

	public User(Status status, String username, String givenName, String familyName, Role role, Set<Org> orgs) {
		super(status);
		this.username = username;
		this.givenName = givenName;
		this.familyName = familyName;
		this.role = role;
		this.orgs.addAll(orgs);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonSerialize(using = NestedCollectionSerializer.class)
	public Set<Org> getOrgs() {
		return orgs;
	}

	@JsonSerialize(using = NestedCollectionSerializer.class)
	public Set<User> getAgents() {
		return agents;
	}
}