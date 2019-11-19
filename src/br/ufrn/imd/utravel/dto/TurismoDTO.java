package br.ufrn.imd.utravel.dto;

public class TurismoDTO {
	private String dataPasseio;
	private float valorGastoPrevisto;
	private PasseioDTO passeioDTO;
	
	public String getDataPasseio() {
		return dataPasseio;
	}
	
	public void setDataPasseio(String dataPasseio) {
		this.dataPasseio = dataPasseio;
	}
	
	public float getValorGastoPrevisto() {
		return valorGastoPrevisto;
	}
	
	public void setValorGastoPrevisto(float valorGastoPrevisto) {
		this.valorGastoPrevisto = valorGastoPrevisto;
	}
	
	public PasseioDTO getPasseioDTO() {
		return passeioDTO;
	}
	
	public void setPasseioDTO(PasseioDTO passeioDTO) {
		this.passeioDTO = passeioDTO;
	}
}
