package br.ufrn.imd.utravel.dto;

public class AlimentacaoDTO {
	private String dataIdaPrevista;
	private float valorGastoPrevisto;
	private RestauranteDTO restauranteDTO;
	
	
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

	public RestauranteDTO getRestauranteDTO() {
		return restauranteDTO;
	}

	public void setRestauranteDTO(RestauranteDTO restauranteDTO) {
		this.restauranteDTO = restauranteDTO;
	}
}
