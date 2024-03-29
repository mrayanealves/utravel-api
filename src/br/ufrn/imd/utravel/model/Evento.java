package br.ufrn.imd.utravel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "evento")
public class Evento extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENTO")
    @SequenceGenerator(name = "SEQ_EVENTO", sequenceName = "seq_id_evento", allocationSize = 1)
    private long id;
    
    @NotBlank
    private String titulo;

    @Column(name = "valor_estimado")
    private float valorEstimado;

    @Column(name = "valor_total_gasto")
    private float valorTotalGasto;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio")
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fim")
    private Date dataFinal;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_viagem")
    private Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_passeio")
    private Passeio passeio;

    @ManyToOne
    @JoinColumn(name = "id_veiculo_alugado")
    private VeiculoAlugado veiculoAlugado;

    @ManyToOne
    @JoinColumn(name = "id_passagem")
    private Passagem passagem;

    public Evento() {
    }

    public Evento(long id, String titulo, float valorEstimado, float valorTotalGasto, @NotNull Date dataInicio, Date dataFim, Viagem viagem,
                  Restaurante restaurante, Reserva reserva, Passeio passeio, VeiculoAlugado veiculoAlugado,
                  Passagem passagem) {
        this.id = id;
        this.titulo = titulo;
        this.valorEstimado = valorEstimado;
        this.valorTotalGasto = valorTotalGasto;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFim;
        this.viagem = viagem;
        this.restaurante = restaurante;
        this.reserva = reserva;
        this.passeio = passeio;
        this.veiculoAlugado = veiculoAlugado;
        this.passagem = passagem;
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

	public float getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(float valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public float getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(float valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public VeiculoAlugado getVeiculoAlugado() {
        return veiculoAlugado;
    }

    public void setVeiculoAlugado(VeiculoAlugado veiculoAlugado) {
        this.veiculoAlugado = veiculoAlugado;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
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
        Evento other = (Evento) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
