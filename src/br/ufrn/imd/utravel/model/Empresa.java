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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
public class Empresa extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPRESA")
    @SequenceGenerator(name = "SEQ_EMPRESA", sequenceName = "seq_id_empresa", allocationSize = 1)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

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
