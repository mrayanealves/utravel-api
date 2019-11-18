package br.ufrn.imd.utravel.dto;

import br.ufrn.imd.utravel.enums.EnumTipoHospedagem;

public class HospedagemDTO {
	private String codigo;
	private int quantidadeQuartos;
	private String dataHospedagem;
	private String dataSaida;
	private float valorGasto;
	private EnumTipoHospedagem tipoHospedagem;
	private EnderecoDTO enderecoDTO;
	private long empresa;
	
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

	public float getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(float valorGasto) {
		this.valorGasto = valorGasto;
	}

	public EnumTipoHospedagem getTipoHospedagem() {
		return tipoHospedagem;
	}
	
	public void setTipoHospedagem(EnumTipoHospedagem tipoHospedagem) {
		this.tipoHospedagem = tipoHospedagem;
	}
	
	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}
	
	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	public long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(long empresa) {
		this.empresa = empresa;
	}
}
