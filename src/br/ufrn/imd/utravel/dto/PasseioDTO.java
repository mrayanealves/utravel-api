package br.ufrn.imd.utravel.dto;

public class PasseioDTO {
	private long idPasseio;
	private String nome;
	private String tipo;
	private EnderecoDTO enderecoDTO;
	private EmpresaDTO empresaDTO;
	
	public long getIdPasseio() {
		return idPasseio;
	}
	
	public void setIdPasseio(long idPasseio) {
		this.idPasseio = idPasseio;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
