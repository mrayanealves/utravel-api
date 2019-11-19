package br.ufrn.imd.utravel.dto;

public class ReservaDTO {
	private String codigo;
	private int quantidadeQuartos;
	private String dataHospedagem;
	private String dataSaida;
	private float valorGastoPrevisto;
	private HospedagemDTO hospedagemDTO;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public int getQuantidadeQuartos() {
		return quantidadeQuartos;
	}
	
	public void setQuantidadeQuartos(int quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}
	
	public String getDataHospedagem() {
		return dataHospedagem;
	}

	public void setDataHospedagem(String dataHospedagem) {
		this.dataHospedagem = dataHospedagem;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public float getValorGastoPrevisto() {
		return valorGastoPrevisto;
	}

	public void setValorGastoPrevisto(float valorGastoPrevisto) {
		this.valorGastoPrevisto = valorGastoPrevisto;
	}

	public HospedagemDTO getHospedagemDTO() {
		return hospedagemDTO;
	}

	public void setHospedagemDTO(HospedagemDTO hospedagemDTO) {
		this.hospedagemDTO = hospedagemDTO;
	}
}
