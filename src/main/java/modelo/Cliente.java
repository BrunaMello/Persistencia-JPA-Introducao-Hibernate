package modelo;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DadosPessoais dadosPessoais;


    public Cliente(String nomeCliente, String cpf) {
        this.dadosPessoais = new DadosPessoais(nomeCliente, cpf);
    }

    public Cliente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public String getNomeCliente(){
        return  this.dadosPessoais.getNomeCliente();
    }

    public String getCpf(){
        return  this.dadosPessoais.getCpf();
    }
}
