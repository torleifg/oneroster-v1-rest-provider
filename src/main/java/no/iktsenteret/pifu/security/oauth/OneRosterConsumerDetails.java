package no.iktsenteret.pifu.security.oauth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.provider.ExtraTrustConsumerDetails;

@Entity
public class OneRosterConsumerDetails implements ExtraTrustConsumerDetails {

	private static final long serialVersionUID = 1L;

	@Id
	private String consumerKey;

	private String consumerName;

	private SignatureSecret signatureSecret;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> authorities = new ArrayList<>();

	/*
	 * Two Legged OAuth 1.0a
	 */
	private static final boolean requiredToObtainAuthenticatedToken = false;

	protected OneRosterConsumerDetails() {
	}

	public OneRosterConsumerDetails(String consumerKey, SignatureSecret signatureSecret) {
		this.consumerKey = consumerKey;
		this.signatureSecret = signatureSecret;
	}

	@Override
	public String getConsumerKey() {
		return consumerKey;
	}

	@Override
	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	@Override
	public SignatureSecret getSignatureSecret() {
		return signatureSecret;
	}

	public void setSignatureSecret(SignatureSecret signatureSecret) {
		this.signatureSecret = signatureSecret;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isRequiredToObtainAuthenticatedToken() {
		return requiredToObtainAuthenticatedToken;
	}
}