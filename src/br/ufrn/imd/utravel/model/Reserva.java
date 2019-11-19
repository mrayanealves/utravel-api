package br.ufrn.imd.utravel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reserva")
public class Reserva extends AbstractModel{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVA")
    @SequenceGenerator(name = "SEQ_RESERVA", sequenceName = "seq_id_reserva", allocationSize = 1)
    private long id;
	
	@NotBlank
    @Column
    private String codigo;

    @Column(name = "quantidade_quartos")
    private int quantidadeQuartos;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_hospedagem")
    private Hospedagem hospedagem;
    
    @JsonIgnore
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Evento> eventos;

	public Reserva() {
		this.eventos = new ArrayList<Evento>();
	}

	public Reserva(long id, @NotBlank String codigo, int quantidadeQuartos, Hospedagem hospedagem,
			List<Evento> eventos) {
		this.id = id;
		this.codigo = codigo;
		this.quantidadeQuartos = quantidadeQuartos;
		this.hospedagem = hospedagem;
		this.eventos = eventos;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(int quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}

	public Hospedagem getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
}
