package br.ufrn.imd.utravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "avaliacao")
public class Avaliacao extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVALIACAO")
    @SequenceGenerator(name = "SEQ_AVALIACAO", sequenceName = "seq_id_avaliacao", allocationSize = 1)
    private long id;

    @NotNull
    @Column(name = "nota_atendimento")
    private int notaAtendimento;

    private String comentario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioAvaliador;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_hospedagem")
    private Hospedagem hospedagem;

    @ManyToOne
    @JoinColumn(name = "id_passeio")
    private Passeio passeio;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_veiculo_alugado")
    private VeiculoAlugado veiculoAlugado;

    public Avaliacao() {
    }

    public Avaliacao(long id, @NotNull int notaAtendimento, String comentario, Usuario usuarioAvaliador,
                     Empresa empresa, Hospedagem hospedagem, Passeio passeio, Restaurante restaurante,
                     VeiculoAlugado veiculoAlugado) {
        super();
        this.id = id;
        this.notaAtendimento = notaAtendimento;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.empresa = empresa;
        this.hospedagem = hospedagem;
        this.passeio = passeio;
        this.restaurante = restaurante;
        this.veiculoAlugado = veiculoAlugado;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public int getNotaAtendimento() {
        return notaAtendimento;
    }

    public void setNotaAtendimento(int notaAtendimento) {
        this.notaAtendimento = notaAtendimento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public VeiculoAlugado getVeiculoAlugado() {
        return veiculoAlugado;
    }

    public void setVeiculoAlugado(VeiculoAlugado veiculoAlugado) {
        this.veiculoAlugado = veiculoAlugado;
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
        Avaliacao other = (Avaliacao) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
