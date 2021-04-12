package modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

    @Column(name = "nome_cliente")
    private String nomeCliente;

    private String cpf;

    public DadosPessoais(){

    }

    public DadosPessoais(String nomeCliente, String cpf) {
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
