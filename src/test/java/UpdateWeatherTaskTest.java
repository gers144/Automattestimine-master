
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateWeatherTaskTest {

	private BufferedReader in;
	private CurrentWeatherRepository currentWeatherRepository;
	private ForecastRepository forecastRepository;

	@Before
	public void setup() {
		in = mock(BufferedReader.class);
		currentWeatherRepository = mock(CurrentWeatherRepository.class);
		forecastRepository = mock(ForecastRepository.class);
	}

	@Test
	public void testOneLineRead() throws IOException {
		when(in.readLine()).thenReturn("Tallinn");

		assertEquals("Tallinn", in.readLine());
	}

	@Test
	public void testMultipleLinesRead() throws IOException {
		when(in.readLine()).thenReturn("Tallinn", "Tartu", "London");

		assertEquals("Tallinn", in.readLine());
		assertEquals("Tartu", in.readLine());
		assertEquals("London", in.readLine());
	}
}
