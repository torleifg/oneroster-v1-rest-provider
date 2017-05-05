package no.iktsenteret.pifu.security.oauth;

import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InvalidOAuthParametersException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OneRosterConsumerDetailsService implements ConsumerDetailsService {

	private final OneRosterConsumerDetailsRepository oneRosterConsumerDetailsRepository;

	public OneRosterConsumerDetailsService(OneRosterConsumerDetailsRepository oneRosterConsumerDetailsRepository) {
		this.oneRosterConsumerDetailsRepository = oneRosterConsumerDetailsRepository;
	}

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
		ConsumerDetails details = oneRosterConsumerDetailsRepository.findOne(consumerKey);

		if (details == null)
			throw new InvalidOAuthParametersException("Consumer not found: " + consumerKey);

		return details;
	}

	@Transactional
	public void createConsumer(OneRosterConsumerDetails consumer) {
		ConsumerDetails details = oneRosterConsumerDetailsRepository.findOne(consumer.getConsumerKey());

		if (details != null)
			throw new InvalidOAuthParametersException("Consumer already exists: " + consumer.getConsumerKey());

		oneRosterConsumerDetailsRepository.save(consumer);
	}
}
