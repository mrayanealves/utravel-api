package br.ufrn.imd.utravel.dto;

import br.ufrn.imd.utravel.enums.EnumTipoOrcamento;

public class OrcamentoDTO {
	private float valorEstimado;
	private EnumTipoOrcamento tipoOrcamento;
	private long viagem;
	
	public float getValorEstimado() {
		return valorEstimado;
	}
	
	public void setValorEstimado(float valorEstimado) {
		this.valorEstimado = valorEstimado;
	}
	
	public EnumTipoOrcamento getTipoOrcamento() {
		return tipoOrcamento;
	}
	
	public void setTipoOrcamento(EnumTipoOrcamento tipoOrcamento) {
		this.tipoOrcamento = tipoOrcamento;
	}
	
	public long getViagem() {
		return viagem;
	}
	
	public void setViagem(long viagem) {
		this.viagem = viagem;
	}
}
