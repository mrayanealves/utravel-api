package br.ufrn.imd.utravel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "localizacao",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "pais_estado_cidade_uniques",
                        columnNames = {"pais", "estado", "cidade"}
                )
        }
)
public class Localizacao extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOCALIZACAO")
    @SequenceGenerator(name = "SEQ_LOCALIZACAO", sequenceName = "seq_id_localizacao", allocationSize = 1)
    private long id;

    @NotBlank
    private String pais;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    public Localizacao() {
    }

    public Localizacao(long id, @NotBlank String pais, @NotBlank String estado, @NotBlank String cidade) {
        this.id = id;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
        Localizacao other = (Localizacao) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
