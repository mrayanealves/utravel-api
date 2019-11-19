package br.ufrn.imd.utravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.ufrn.imd.utravel.enums.EnumTipoTransporte;

@Entity
@Table(name = "veiculo")
public class Veiculo extends AbstractModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VEICULO")
	@SequenceGenerator(name = "SEQ_VEICULO", sequenceName = "seq_id_veiculo", allocationSize = 1)
	private long id;
	
	@Column(unique = true)
	private String placa;

	private String cor;

	private String modelo;

	private String marca;
	
	private EnumTipoTransporte tipoTransporte;

	public Veiculo() {
	}

	public Veiculo(long id, String placa, String cor, String modelo, String marca, EnumTipoTransporte tipoTransporte) {
		this.id = id;
		this.placa = placa;
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.tipoTransporte = tipoTransporte;
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

	public EnumTipoTransporte getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(EnumTipoTransporte tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
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
		Veiculo other = (Veiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
