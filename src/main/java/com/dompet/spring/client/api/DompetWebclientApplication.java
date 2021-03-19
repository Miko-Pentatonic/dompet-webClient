package com.dompet.spring.client.api;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Controller
@RequestMapping("/dompet-client")
@ResponseBody
public class DompetWebclientApplication {

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8686/Dompet/Service")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@PostMapping("/dompetNow")
	public Mono<String> DompetNow(@RequestBody DompetRequest request) {
		return webClient.post().uri("/transaksiShow").bodyValue(request).retrieve().bodyToMono(String.class);
	}

	@GetMapping("/trackDompets")
	public Flux<DompetRequest> trackAllTransaksi() {
		return webClient.get().uri("/getAllTransaksi").retrieve().bodyToFlux(DompetRequest.class);
	}

	@GetMapping("/trackDompet/{id_dom}")
	public Mono<DompetRequest> getDompetById(@PathVariable long id_dom) {
		return webClient.get().uri("/getDompet/" + id_dom).retrieve().bodyToMono(DompetRequest.class);
	}

	@GetMapping("/trackSaldoDompet")
	public Mono<Double> getSaldo() {
		return webClient.get().uri("/getSaldo").retrieve().bodyToMono(Double.class);
	}

	@DeleteMapping("/removeDompet/{id_dom}")
	public Mono<String> cancelDompet(@PathVariable long id_dom) {
		return webClient.delete().uri("/cancelTransaski/" + id_dom).retrieve().bodyToMono(String.class);
	}

	@PutMapping("/changeDompet/{id_dom}")
	public Mono<DompetRequest> updateDompet(@PathVariable long id_dom, @RequestBody DompetRequest request) {
		return webClient.put().uri("/updateDompet/" + id_dom).bodyValue(request).retrieve()
				.bodyToMono(DompetRequest.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DompetWebclientApplication.class, args);
	}
}
