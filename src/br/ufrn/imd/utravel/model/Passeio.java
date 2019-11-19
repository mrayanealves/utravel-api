package br.ufrn.imd.utravel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "passeio")
public class Passeio extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PASSEIO")
    @SequenceGenerator(name = "SEQ_PASSEIO", sequenceName = "seq_id_passeio", allocationSize = 1)
    private long id;

    @NotBlank
    private String nome;

    private String tipo;

    @ManyToMany
    @JoinTable(name = "empresa_passeio",
            joinColumns = @JoinColumn(name = "id_passeio", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    )
    private List<Empresa> empresasOfertantes;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    
    @JsonIgnore
    @OneToMany(mappedBy = "passeio", cascade = CascadeType.ALL)
    private List<Evento> eventos;

    public Passeio() {
    	this.empresasOfertantes = new ArrayList<Empresa>();
    	this.eventos = new ArrayList<Evento>();
    }

    public Passeio(long id, @NotBlank String nome, String tipo, List<Empresa> empresasOfertantes, Endereco endereco,
                   List<Avaliacao> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
        this.eventos = new ArrayList<Evento>();
        
        if (empresasOfertantes == null || empresasOfertantes.isEmpty()) {
        	this.empresasOfertantes = new ArrayList<Empresa>();
		} else {
			this.empresasOfertantes = empresasOfertantes;
		}
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Empresa> getEmpresasOfertantes() {
        return empresasOfertantes;
    }

    public void setEmpresasOfertantes(List<Empresa> empresasOfertantes) {
        this.empresasOfertantes = empresasOfertantes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        Passeio other = (Passeio) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
