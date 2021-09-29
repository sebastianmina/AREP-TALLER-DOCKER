package edu.escuelaing.arep.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpClient {
	private String[] puertos = { ":34001", ":34002", ":34003" };
	private int cantidadServidores = 0;
	private String url = "http://190.147.3.51";

	public String getMensaje() throws UnirestException {
		HttpResponse<String> apiResponse = Unirest.get(url + puertos[cantidadServidores] + "/mensaje").asString();
		System.out.println("Petici�n GET de " + url + puertos[cantidadServidores]);
		return apiResponse.getBody();
	}

	public String postMensaje(String mensaje) throws UnirestException {
		HttpResponse<String> apiResponse = Unirest.post(url + puertos[cantidadServidores] + "/mensaje").body(mensaje)
				.asString();
		System.out.println("Petici�n POST de " + url + puertos[cantidadServidores]);
		return apiResponse.getBody();
	}

	public void cambiarNumeroServidor() {
		cantidadServidores = (cantidadServidores + 1) % puertos.length;
	}
}