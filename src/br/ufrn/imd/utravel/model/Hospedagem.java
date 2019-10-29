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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufrn.imd.utravel.enums.EnumTipoHospedagem;

@Entity
@Table(name = "hospedagem")
public class Hospedagem extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HOSPEDAGEM")
	@SequenceGenerator(name = "SEQ_HOSPEDAGEM", sequenceName = "seq_id_hospedagem", allocationSize = 1)
	private long id;
	
	@NotBlank
	@Column(unique = true)
	private String codigo;
	
	@Column(name = "quantidade_quartos")
	private int quantidadeQuartos;
	
	@Column(name = "tipo_hospedagem")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoHospedagem tipoHospedagem;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	public Hospedagem() {
	}

	public Hospedagem(long id, @NotBlank String codigo, int quantidadeQuartos, EnumTipoHospedagem tipoHospedagem,
			Endereco endereco, List<Avaliacao> avaliacoes, @NotNull Empresa empresa) {
		this.id = id;
		this.codigo = codigo;
		this.quantidadeQuartos = quantidadeQuartos;
		this.tipoHospedagem = tipoHospedagem;
		this.endereco = endereco;
		this.empresa = empresa;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(int quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}

	public EnumTipoHospedagem getTipoHospedagem() {
		return tipoHospedagem;
	}

	public void setTipoHospedagem(EnumTipoHospedagem tipoHospedagem) {
		this.tipoHospedagem = tipoHospedagem;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		Hospedagem other = (Hospedagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
