package br.ufrn.imd.utravel.exception;

public class FieldError {
    private String campo;
    private String mensagem;

    public FieldError(String field, String message) {
        this.campo = field;
        this.mensagem = message;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
