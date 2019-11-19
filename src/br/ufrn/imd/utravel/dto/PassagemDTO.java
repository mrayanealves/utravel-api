package br.ufrn.imd.utravel.dto;

import java.util.List;

public class PassagemDTO {
	private String dataPartida;
	private String dataChegada;
	private EnderecoDTO enderecoSaidaOrigem;
	private EnderecoDTO enderecoChegadaDestino;
	private List<EnderecoDTO> enderecosParadas;
	private EmpresaDTO empresaDTO;
	
	public String getDataPartida() {
		return dataPartida;
	}
	
	public void setDataPartida(String dataPartida) {
		this.dataPartida = dataPartida;
	}
	
	public String getDataChegada() {
		return dataChegada;
	}
	
	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public EnderecoDTO getEnderecoSaidaOrigem() {
		return enderecoSaidaOrigem;
	}
	
	public void setEnderecoSaidaOrigem(EnderecoDTO enderecoSaidaOrigem) {
		this.enderecoSaidaOrigem = enderecoSaidaOrigem;
	}
	
	public EnderecoDTO getEnderecoChegadaDestino() {
		return enderecoChegadaDestino;
	}
	
	public void setEnderecoChegadaDestino(EnderecoDTO enderecoChegadaDestino) {
		this.enderecoChegadaDestino = enderecoChegadaDestino;
	}
	
	public List<EnderecoDTO> getEnderecosParadas() {
		return enderecosParadas;
	}
	
	public void setEnderecosParadas(List<EnderecoDTO> enderecosParadas) {
		this.enderecosParadas = enderecosParadas;
	}
	
	public EmpresaDTO getEmpresaDTO() {
		return empresaDTO;
	}
	
	public void setEmpresaDTO(EmpresaDTO empresaDTO) {
		this.empresaDTO = empresaDTO;
	}
}
