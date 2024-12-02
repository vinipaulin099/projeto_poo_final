package funcionarios;
import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    private String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularSalario();

    @Override
    public String toString() {
        return nome + " - Salario: R$" + calcularSalario();
    }
}
