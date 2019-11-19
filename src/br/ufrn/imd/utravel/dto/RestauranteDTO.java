package br.ufrn.imd.utravel.dto;

public class RestauranteDTO {
	private long idRestaurante;
	private String nomeRestaurante;
	private int numeroEstrela;
	private EnderecoDTO enderecoDTO;
	private EmpresaDTO empresaDTO;
	
	public long getIdRestaurante() {
		return idRestaurante;
	}
	
	public void setIdRestaurante(long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}
	
	public String getNomeRestaurante() {
		return nomeRestaurante;
	}
	
	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}
	
	public int getNumeroEstrela() {
		return numeroEstrela;
	}
	
	public void setNumeroEstrela(int numeroEstrela) {
		this.numeroEstrela = numeroEstrela;
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
