package br.ufrn.imd.utravel.dto;

public class InformacoesVeiculoProprioDTO {
	private float valorGastoCombustivelPrevisto;
	private float valorGastoPedagioPrevisto;
	private VeiculoDTO veiculoDTO;
	
	public float getValorGastoCombustivelPrevisto() {
		return valorGastoCombustivelPrevisto;
	}
	
	public void setValorGastoCombustivelPrevisto(float valorGastoCombustivelPrevisto) {
		this.valorGastoCombustivelPrevisto = valorGastoCombustivelPrevisto;
	}
	
	public float getValorGastoPedagioPrevisto() {
		return valorGastoPedagioPrevisto;
	}
	
	public void setValorGastoPedagioPrevisto(float valorGastoPedagioPrevisto) {
		this.valorGastoPedagioPrevisto = valorGastoPedagioPrevisto;
	}
	
	public VeiculoDTO getVeiculoDTO() {
		return veiculoDTO;
	}
	
	public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
		this.veiculoDTO = veiculoDTO;
	}
}
