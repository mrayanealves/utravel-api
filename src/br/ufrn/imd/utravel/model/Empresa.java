package br.ufrn.imd.utravel.model;

import java.util.List;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "empresa",
uniqueConstraints = {
        @UniqueConstraint(
                name = "nome_documento_endereco_uniques",
                columnNames = {"nome", "documento", "id_endereco"}
        )
})
public class Empresa extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPRESA")
    @SequenceGenerator(name = "SEQ_EMPRESA", sequenceName = "seq_id_empresa", allocationSize = 1)
    private long id;

    @NotBlank
    private String nome;

    private String documento;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    
    @JsonIgnore
    @OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
    private List<Avaliacao> avaliacoes;

    public Empresa() {
    }

    public Empresa(long id, @NotBlank String nome, @NotBlank String documento, Endereco endereco,
                   List<Avaliacao> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @JsonProperty("avaliacaoMedia")
    public float notaMediaAvaliacoes() {
    	if (this.avaliacoes == null || 
    			this.avaliacoes.isEmpty() || 
    			this.avaliacoes.size() == 0) {
			return 0;
		}
    	
    	int somaNotas = 0;
    	
    	for(Avaliacao avaliacao : this.avaliacoes) {
    		somaNotas+=avaliacao.getNotaAtendimento();
    	}
    	
    	return somaNotas/this.avaliacoes.size();
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
        Empresa other = (Empresa) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
