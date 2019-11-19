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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "veiculo_alugado", 
		uniqueConstraints = {
	        @UniqueConstraint(
	                name = "empresa_veiculo_uniques",
	                columnNames = {"id_empresa", "id_veiculo"}
	        )
		}
)
public class VeiculoAlugado extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VEICULO_ALUGADO")
    @SequenceGenerator(name = "SEQ_VEICULO_ALUGADO", sequenceName = "seq_id_veiculo_alugado", allocationSize = 1)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa")
    private Empresa empresaLocadora;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    public VeiculoAlugado() {
    }

    public VeiculoAlugado(long id, Empresa empresaLocadora,
                          List<Avaliacao> avaliacoes) {
        this.id = id;
        this.empresaLocadora = empresaLocadora;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public Empresa getEmpresaLocadora() {
        return empresaLocadora;
    }

    public void setEmpresaLocadora(Empresa empresaLocadora) {
        this.empresaLocadora = empresaLocadora;
    }

    public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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
        VeiculoAlugado other = (VeiculoAlugado) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
