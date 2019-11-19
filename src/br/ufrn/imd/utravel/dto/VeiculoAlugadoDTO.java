package br.ufrn.imd.utravel.dto;

public class VeiculoAlugadoDTO {
	private String dataAluguel;
	private String dataEntrega;
	private float valorGastoPrevisto;
	private EmpresaDTO empresaDTO;
	private VeiculoDTO veiculoDTO;
	
	public String getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(String dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public float getValorGastoPrevisto() {
		return valorGastoPrevisto;
	}

	public void setValorGastoPrevisto(float valorGastoPrevisto) {
		this.valorGastoPrevisto = valorGastoPrevisto;
	}

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
