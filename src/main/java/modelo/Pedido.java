package modelo;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido")
    private LocalDate dataPedido = LocalDate.now();

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    //mapeamento manytomany simples exemplo se for complexo tem que fazer entidade
//    @ManyToMany
//    @JoinTable(colocar os id que referenciam)
//    private List<Produto.java> produtos;

    //relacionamento many to many complexo
    //indicar que é um mapeamento bidirecional quando houver
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) //passar nome do outro lado do relacionamento sempre passar do lado onetomany
    //sempre que houver lista ja inicializa a colecao para evitar ter que fazer if
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this); //settar dois lados do relacionamento nao esquecer
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Pedido() {

    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valor_total) {
        this.valorTotal = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente nome_cliente) {
        this.cliente = nome_cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", valorTotal=" + valorTotal +
                ", cliente=" + cliente +
                ", itens=" + itens +
                '}';
    }
}
