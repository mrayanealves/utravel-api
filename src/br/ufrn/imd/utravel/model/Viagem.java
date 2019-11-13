package br.ufrn.imd.utravel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "viagem")
public class Viagem extends AbstractModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VIAGEM")
	@SequenceGenerator(name = "SEQ_VIAGEM", sequenceName = "seq_id_viagem", allocationSize = 1)
	private long id;
	
	@NotBlank
	private String titulo;
	
	@Column(name = "objetivo")
	private String objetivo;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Date dataFim;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "viagem_destino",
	           joinColumns = @JoinColumn(name = "id_viagem", referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name = "id_localizacao", referencedColumnName = "id")
	)
	private List<Localizacao> destinos;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "viagem_usuarios",
	           joinColumns = @JoinColumn(name = "id_viagem", referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
	           uniqueConstraints = {
	        		   @UniqueConstraint(name = "viagem_usuario_uniques", 
	        				             columnNames = {"id_viagem", "id_usuario"})
	           }
	)
	@NotEmpty
	private List<Usuario> usuarios;

	public Viagem() {
		this.usuarios = new ArrayList<Usuario>();
		this.destinos = new ArrayList<Localizacao>();
	}
	
	public Viagem(long id, @NotBlank String titulo, String objetivo, @NotNull Date dataInicio, Date dataFim,
			@NotEmpty List<Localizacao> destinos, @NotEmpty List<Usuario> usuarios) {
		this.id = id;
		this.titulo = titulo;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.destinos = destinos;
		this.usuarios = usuarios;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Localizacao> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Localizacao> destinos) {
		this.destinos = destinos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		Viagem other = (Viagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
