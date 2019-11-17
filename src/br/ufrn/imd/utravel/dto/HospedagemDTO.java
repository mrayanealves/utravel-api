package br.ufrn.imd.utravel.dto;

import br.ufrn.imd.utravel.enums.EnumTipoHospedagem;

public class HospedagemDTO {
	private String codigo;
	private int quantidadeQuartos;
	private EnumTipoHospedagem tipoHospedagem;
	private EnderecoDTO enderecoDTO;
	private EmpresaDTO empresaDTO;
	
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
	
	public EmpresaDTO getEmpresaDTO() {
		return empresaDTO;
	}
	
	public void setEmpresaDTO(EmpresaDTO empresaDTO) {
		this.empresaDTO = empresaDTO;
	}
}
