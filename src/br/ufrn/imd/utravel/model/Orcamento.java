package br.ufrn.imd.utravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufrn.imd.enums.EnumTipoOrcamento;

@Entity
@Table(name = "orcamento")
public class Orcamento extends AbstractModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORCAMENTO")
	@SequenceGenerator(name = "SEQ_ORCAMENTO", sequenceName = "seq_id_orcamento", allocationSize = 1)
	private long id;
	
	@NotNull
	@Column(name = "valor_estimado")
	private float valorEstimado;
	
	@NotNull
	@Column(name = "tipo_orcamento")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoOrcamento tipoOrcamento;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_viagem")
	private Viagem viagem;	
	
	public Orcamento() {
	}
	
	public Orcamento(long id, @NotNull float valorEstimado, @NotNull EnumTipoOrcamento tipoOrcamento, Viagem viagem) {
		this.id = id;
		this.valorEstimado = valorEstimado;
		this.tipoOrcamento = tipoOrcamento;
		this.viagem = viagem;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

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

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
