package br.ufrn.imd.utravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "informacoes_transporte_proprio")
public class InformacoesTransporteProprio extends AbstractModel{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFORMACOES_TRANSPORTE_PROPRIO")
    @SequenceGenerator(name = "SEQ_INFORMACOES_TRANSPORTE_PROPRIO", sequenceName = "seq_id_informacoes_transporte_proprio", allocationSize = 1)
    private long id;
	
	@NotNull
	@Column(name = "valor_gasto_combustivel_previsto")
	private float valorGastoCombustivelPrevisto;
	
	@NotNull
	@Column(name = "valor_gasto_pedagio_previsto")
	private float valorGastoPedagioPrevisto;
	
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	
	public InformacoesTransporteProprio() {
	}
	
	public InformacoesTransporteProprio(long id, @NotNull float valorGastoCombustivelPrevisto,
			@NotNull float valorGastoPedagioPrevisto, Veiculo veiculo) {
		this.id = id;
		this.valorGastoCombustivelPrevisto = valorGastoCombustivelPrevisto;
		this.valorGastoPedagioPrevisto = valorGastoPedagioPrevisto;
		this.veiculo = veiculo;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public float getValorGastoCombustivelPrevisto() {
		return valorGastoCombustivelPrevisto;
	}

	public void setValorGastoCombustivelPrevisto(float valorGastoCombustivelPrevisto) {
		this.valorGastoCombustivelPrevisto = valorGastoCombustivelPrevisto;
	}

	public float getValorGastoPedagioPrevisto() {
		return valorGastoPedagioPrevisto;
	}

	public void setValorGastoPedagioPrevisto(float valorGastoPedagioPrevisto) {
		this.valorGastoPedagioPrevisto = valorGastoPedagioPrevisto;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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
		InformacoesTransporteProprio other = (InformacoesTransporteProprio) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
