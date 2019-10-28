package br.ufrn.imd.utravel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufrn.imd.utravel.enums.EnumTipoTransporte;

@Entity
@Table(name = "transorte")
public class Transporte extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSPORTE")
	@SequenceGenerator(name = "SEQ_TRANSPORTE", sequenceName = "seq_id_transporte", allocationSize = 1)
	private long id;
	
	@NotNull
	private boolean proprio;
	
	@Column(name = "tipo_transporte")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoTransporte tipoTransporte;
	
	@ManyToOne
	@JoinColumn(name = "id_viagem")
	private Viagem viagem;
	
	@ManyToOne
	@JoinColumn(name = "id_veiculo_alugado")
	private VeiculoAlugado veiculoAlugado;
	
	@OneToMany
	@JoinColumn(name = "id_transporte")
	private List<Passagem> passagens;

	public Transporte() {
	}

	public Transporte(long id, @NotNull boolean proprio, EnumTipoTransporte tipoTransporte, Viagem viagem,
			VeiculoAlugado veiculoAlugado, List<Passagem> passagens) {
		this.id = id;
		this.proprio = proprio;
		this.tipoTransporte = tipoTransporte;
		this.viagem = viagem;
		this.veiculoAlugado = veiculoAlugado;
		this.passagens = passagens;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public boolean isProprio() {
		return proprio;
	}

	public void setProprio(boolean proprio) {
		this.proprio = proprio;
	}

	public EnumTipoTransporte getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(EnumTipoTransporte tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public VeiculoAlugado getVeiculoAlugado() {
		return veiculoAlugado;
	}

	public void setVeiculoAlugado(VeiculoAlugado veiculoAlugado) {
		this.veiculoAlugado = veiculoAlugado;
	}

	public List<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(List<Passagem> passagens) {
		this.passagens = passagens;
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
		Transporte other = (Transporte) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
