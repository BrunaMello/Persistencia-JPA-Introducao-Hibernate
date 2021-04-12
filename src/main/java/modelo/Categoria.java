package modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

    public Categoria() {

    }

    @EmbeddedId
    private CategoriaId id;


    public Categoria(String nomeCategoria) {
        this.id = new CategoriaId(nomeCategoria, "xpto");
    }

    public String getNomeCategoria(){
        return this.id.getNome();
    }





}
