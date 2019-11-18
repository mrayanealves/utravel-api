package br.ufrn.imd.utravel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Evento> eventos;

    public Restaurante() {
    	this.eventos = new ArrayList<Evento>();
    }

    public Restaurante(long id, @NotBlank String nome, int numeroEstrelas, Endereco endereco,
                       List<Avaliacao> avaliacoes, @NotNull Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.numeroEstrelas = numeroEstrelas;
        this.endereco = endereco;
        this.empresa = empresa;
        this.eventos = new ArrayList<Evento>();
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
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
