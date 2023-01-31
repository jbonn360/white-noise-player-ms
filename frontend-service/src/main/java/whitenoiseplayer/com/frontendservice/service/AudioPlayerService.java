package whitenoiseplayer.com.frontendservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AudioPlayerService {
	private WebClient.Builder webClientBuilder;

	public AudioPlayerService(@Autowired WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	public Object isActive() {
		boolean isActive = webClientBuilder.build().get().uri("http://localhost:8082/player/state").retrieve()
				.bodyToMono(Boolean.class).block();

		return isActive;
	}

	public double getPlayerMasterVolume() {
		double masterVolume = webClientBuilder.build().get().uri("http://localhost:8082/player/masterVolume").retrieve()
				.bodyToMono(Double.class).block();

		return masterVolume;
	}

	public boolean isPlaybackOverride() {
		boolean isOverride = webClientBuilder.build().get().uri("http://localhost:8082/player/override").retrieve()
				.bodyToMono(Boolean.class).block();

		return isOverride;
	}

	public void switchStateTo(boolean state) {
		webClientBuilder.build().patch().uri("http://localhost:8082/player/state").retrieve().bodyToMono(Boolean.class)
				.block(); // check how to send patch request properly
	}

	public void setPlayerMasterVolume(double volume) {
		webClientBuilder.build().patch().uri("http://localhost:8082/player/masterVolume").retrieve()
				.bodyToMono(Boolean.class).block(); // check how to send patch request properly
	}

	public boolean setPlaybackOverride(boolean value) {
		boolean buttonStatus = webClientBuilder.build().patch().uri("http://localhost:8082/player/override").retrieve()
				.bodyToMono(Boolean.class).block(); // check how to send patch request properly
		
		return buttonStatus;
	}

}
