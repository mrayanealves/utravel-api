package br.ufrn.imd.utravel.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo_alugado")
public class VeiculoAlugado extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VEICULO_ALUGADO")
	@SequenceGenerator(name = "SEQ_VEICULO_ALUGADO", sequenceName = "seq_id_veiculo_alugado", allocationSize = 1)
	private long id;
	
	private String placa;
	
	private String cor;
	
	private String modelo;
	
	private String marca;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_empresa")
	private Empresa empresaLocadora;

	public VeiculoAlugado() {
	}

	public VeiculoAlugado(long id, String placa, String cor, String modelo, String marca, Empresa empresaLocadora,
			List<Avaliacao> avaliacoes) {
		this.id = id;
		this.placa = placa;
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.empresaLocadora = empresaLocadora;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Empresa getEmpresaLocadora() {
		return empresaLocadora;
	}

	public void setEmpresaLocadora(Empresa empresaLocadora) {
		this.empresaLocadora = empresaLocadora;
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
		VeiculoAlugado other = (VeiculoAlugado) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
