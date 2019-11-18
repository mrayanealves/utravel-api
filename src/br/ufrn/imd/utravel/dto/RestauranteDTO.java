package br.ufrn.imd.utravel.dto;

public class RestauranteDTO {
	private String dataIdaPrevista;
	private float valorGastoPrevisto;
	private long restaurante;
	
	public String getDataIdaPrevista() {
		return dataIdaPrevista;
	}
	
	public void setDataIdaPrevista(String dataIdaPrevista) {
		this.dataIdaPrevista = dataIdaPrevista;
	}
	
	public float getValorGastoPrevisto() {
		return valorGastoPrevisto;
	}

	public void setValorGastoPrevisto(float valorGastoPrevisto) {
		this.valorGastoPrevisto = valorGastoPrevisto;
	}
	
	public long getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(long restaurante) {
		this.restaurante = restaurante;
	}
}
