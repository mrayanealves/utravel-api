package br.ufrn.imd.utravel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "passagem")
public class Passagem extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PASSAGEM")
	@SequenceGenerator(name = "SEQ_PASSAGEM", sequenceName = "seq_id_passagem", allocationSize = 1)
	private long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_partida")
	private Date dataPartida;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_chegada")
	private Date dataChegada;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_endereco_saida_origem")
	private Endereco enderecoSaidaOrigem;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_endereco_chegada_destino")
	private Endereco enderecoChegadaDestino;
	
	@ManyToMany
	@JoinTable(name = "passagem_enderecos_paradas",
	           joinColumns = @JoinColumn(name = "id_passagem", referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name = "id_endereco_paradas", referencedColumnName = "id")
	)
	private List<Endereco> enderecosParadas;
	
	public Passagem() {
	}
	
	public Passagem(long id, @NotNull Date dataPartida, Date dataChegada, Endereco enderecoSaidaOrigem,
			Endereco enderecoChegadaDestino, List<Endereco> enderecosParadas) {
		this.id = id;
		this.dataPartida = dataPartida;
		this.dataChegada = dataChegada;
		this.enderecoSaidaOrigem = enderecoSaidaOrigem;
		this.enderecoChegadaDestino = enderecoChegadaDestino;
		this.enderecosParadas = enderecosParadas;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Endereco getEnderecoSaidaOrigem() {
		return enderecoSaidaOrigem;
	}

	public void setEnderecoSaidaOrigem(Endereco enderecoSaidaOrigem) {
		this.enderecoSaidaOrigem = enderecoSaidaOrigem;
	}

	public Endereco getEnderecoChegadaDestino() {
		return enderecoChegadaDestino;
	}

	public void setEnderecoChegadaDestino(Endereco enderecoChegadaDestino) {
		this.enderecoChegadaDestino = enderecoChegadaDestino;
	}

	public List<Endereco> getEnderecosParadas() {
		return enderecosParadas;
	}

	public void setEnderecosParadas(List<Endereco> enderecosParadas) {
		this.enderecosParadas = enderecosParadas;
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
		Passagem other = (Passagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
