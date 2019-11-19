package br.ufrn.imd.utravel.dto;

public class EmpresaDTO {
	private long idEmpresa;
	private String nome;
	private String documento;
	private EnderecoDTO enderecoSede;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public EnderecoDTO getEnderecoSede() {
		return enderecoSede;
	}
	
	public void setEnderecoSede(EnderecoDTO enderecoSede) {
		this.enderecoSede = enderecoSede;
	}
}
