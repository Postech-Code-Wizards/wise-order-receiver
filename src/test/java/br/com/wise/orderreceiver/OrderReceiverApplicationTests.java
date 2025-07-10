package br.com.wise.orderreceiver;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;

class OrderReceiverApplicationTest {

	@Test
	@DisplayName("Should run Spring Boot application without exceptions")
	void shouldRunSpringBootApplication() {
		String[] args = Instancio.create(String[].class);
		try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class)) {
			springAppMock.when(() -> SpringApplication.run(OrderReceiverApplication.class, args)).thenReturn(null);
			OrderReceiverApplication.main(args);
		}
	}
}