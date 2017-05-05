package no.iktsenteret.pifu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;

import no.iktsenteret.pifu.security.oauth.OneRosterConsumerDetails;
import no.iktsenteret.pifu.security.oauth.OneRosterConsumerDetailsService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(final OneRosterConsumerDetailsService oneRosterConsumerDetailsService) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				OneRosterConsumerDetails readerAndWriter = new OneRosterConsumerDetails("ennio",
						new SharedConsumerSecretImpl("morricone"));
				readerAndWriter.getAuthorities()
						.addAll(AuthorityUtils.createAuthorityList("READ", "WRITE", "ACTUATOR"));
				oneRosterConsumerDetailsService.createConsumer(readerAndWriter);

				OneRosterConsumerDetails reader = new OneRosterConsumerDetails("user",
						new SharedConsumerSecretImpl("password"));
				reader.getAuthorities().addAll(AuthorityUtils.createAuthorityList("READ"));
				oneRosterConsumerDetailsService.createConsumer(reader);
			}
		};
	}
}
