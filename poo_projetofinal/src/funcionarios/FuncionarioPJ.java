package funcionarios;

public class FuncionarioPJ extends Funcionario {
    private double horasTrabalhadas;
    private double valorHora;

    public FuncionarioPJ(String nome, double horasTrabalhadas, double valorHora) {
        super(nome);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabalhadas * valorHora;
    }

}
