package br.ufrn.imd.utravel.dto;

import java.util.List;

import br.ufrn.imd.utravel.enums.EnumTipoTransporte;

public class TransporteDTO {
	private boolean transporteProprio;
	private EnumTipoTransporte tipoTransporte;
	private VeiculoAlugadoDTO veiculoAlugadoDTO;
	private InformacoesVeiculoProprioDTO informacoesVeiculoProprioDTO;
	private List<PassagemDTO> passagensDTO;
	
	public boolean isTransporteProprio() {
		return transporteProprio;
	}
	
	public void setTransporteProprio(boolean transporteProprio) {
		this.transporteProprio = transporteProprio;
	}
	
	public EnumTipoTransporte getTipoTransporte() {
		return tipoTransporte;
	}
	
	public void setTipoTransporte(EnumTipoTransporte tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	
	public VeiculoAlugadoDTO getVeiculoAlugadoDTO() {
		return veiculoAlugadoDTO;
	}
	
	public void setVeiculoAlugadoDTO(VeiculoAlugadoDTO veiculoAlugadoDTO) {
		this.veiculoAlugadoDTO = veiculoAlugadoDTO;
	}
	
	public InformacoesVeiculoProprioDTO getInformacoesVeiculoProprioDTO() {
		return informacoesVeiculoProprioDTO;
	}
	
	public void setInformacoesVeiculoProprioDTO(InformacoesVeiculoProprioDTO informacoesVeiculoProprioDTO) {
		this.informacoesVeiculoProprioDTO = informacoesVeiculoProprioDTO;
	}
	
	public List<PassagemDTO> getPassagensDTO() {
		return passagensDTO;
	}
	
	public void setPassagensDTO(List<PassagemDTO> passagensDTO) {
		this.passagensDTO = passagensDTO;
	}
}
