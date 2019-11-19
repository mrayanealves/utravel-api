package br.ufrn.imd.utravel.dto;

public class VeiculoAlugadoDTO {
	private EmpresaDTO empresaDTO;
	private VeiculoDTO veiculoDTO;
	
	public EmpresaDTO getEmpresaDTO() {
		return empresaDTO;
	}
	
	public void setEmpresaDTO(EmpresaDTO empresaDTO) {
		this.empresaDTO = empresaDTO;
	}
	
	public VeiculoDTO getVeiculoDTO() {
		return veiculoDTO;
	}
	
	public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
		this.veiculoDTO = veiculoDTO;
	}
}
