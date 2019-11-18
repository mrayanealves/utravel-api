package br.ufrn.imd.utravel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "endereco",
		uniqueConstraints = {
		        @UniqueConstraint(
		                name = "endereco_localizacao_uniques",
		                columnNames = {"endereco", "id_localizacao"}
		        )
		}
)
public class Endereco extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "seq_id_endereco", allocationSize = 1)
    private long id;

    @NotBlank
    private String endereco;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    public Endereco() {
    }

    public Endereco(long id, @NotBlank String endereco, Localizacao localizacao) {
        this.id = id;
        this.endereco = endereco;
        this.localizacao = localizacao;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
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
        Endereco other = (Endereco) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
