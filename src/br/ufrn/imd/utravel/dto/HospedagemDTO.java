package br.ufrn.imd.utravel.dto;

import br.ufrn.imd.utravel.enums.EnumTipoHospedagem;

public class HospedagemDTO {
	private long idHospedagem;
	private String titulo;
	private EnumTipoHospedagem tipoHospedagem;
	private EnderecoDTO enderecoDTO;
	private EmpresaDTO empresaDTO;
	
	public long getIdHospedagem() {
		return idHospedagem;
	}
	
	public void setIdHospedagem(long idHospedagem) {
		this.idHospedagem = idHospedagem;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
