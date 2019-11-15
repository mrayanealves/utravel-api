package br.ufrn.imd.utravel.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "empresa_passeio",
            joinColumns = @JoinColumn(name = "id_passeio", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    )
    private List<Empresa> empresasOfertantes;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Passeio() {
    }

    public Passeio(long id, @NotBlank String nome, String tipo, List<Empresa> empresasOfertantes, Endereco endereco,
                   List<Avaliacao> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.empresasOfertantes = empresasOfertantes;
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
