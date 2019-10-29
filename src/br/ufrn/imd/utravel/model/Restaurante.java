package br.ufrn.imd.utravel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "restaurante")
public class Restaurante extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESTAURANTE")
	@SequenceGenerator(name = "SEQ_RESTAURANTE", sequenceName = "seq_id_restaurante", allocationSize = 1)
	private long id;
	
	@NotBlank
	private String nome;
	
	@Column(name = "numero_estrelas")
	private int numeroEstrelas;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	public Restaurante() {
	}
	
	public Restaurante(long id, @NotBlank String nome, int numeroEstrelas, Endereco endereco,
			List<Avaliacao> avaliacoes) {
		this.id = id;
		this.nome = nome;
		this.numeroEstrelas = numeroEstrelas;
		this.endereco = endereco;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroEstrelas() {
		return numeroEstrelas;
	}

	public void setNumeroEstrelas(int numeroEstrelas) {
		this.numeroEstrelas = numeroEstrelas;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Restaurante other = (Restaurante) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
