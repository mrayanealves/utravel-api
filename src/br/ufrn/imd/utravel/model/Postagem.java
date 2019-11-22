package br.ufrn.imd.utravel.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Postagem extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POSTAGEM")
    @SequenceGenerator(name = "SEQ_POSTAGEM", sequenceName = "seq_id_postagem", allocationSize = 1)
    private long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_viagem")
    private Viagem viagem;

    public Postagem() {}

    public Postagem(@NotBlank String titulo, @NotBlank String descricao, @NotNull Viagem viagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.viagem = viagem;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
