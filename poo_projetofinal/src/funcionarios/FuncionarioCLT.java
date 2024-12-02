package funcionarios;

public class FuncionarioCLT extends Funcionario implements Estagiario {
    private double salarioBase;

    public FuncionarioCLT(String nome, double salarioBase) {
        super(nome);
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
    }

    @Override
    public void trabalharMenos() {
    }
}
