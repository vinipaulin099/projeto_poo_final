package sistema;

import funcionarios.Funcionario;
import funcionarios.FuncionarioCLT;
import funcionarios.FuncionarioPJ;
import repository.FuncionarioRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaFuncionarios {

private static List<Funcionario> funcionarios; //lista de funcionários
private static Scanner scanner;

public SistemaFuncionarios() {
    this.funcionarios = new ArrayList<>();
    this.scanner = new Scanner(System.in);
}
//metodo para iniciar o sistema
public void iniciar() {
    int opcao;

    do {
        exibirMenu();
        opcao = lerInteiro();

        switch (opcao) {
            case 1 -> cadastrarFuncionario();
            case 2 -> listarFuncionarios();
            case 3 -> salvarFuncionarios();
            case 4 -> carregarFuncionarios();
            case 0 -> System.out.println("Encerrando o programa");
            default -> System.out.println("Opção inválida digite novamente");
        }
    } while (opcao != 0);
}

    private static void exibirMenu() {
        System.out.println("\n== Sistema de Gerenciamento de Funcionários ==");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Listar Funcionários");
        System.out.println("3. Salvar Funcionários em Arquivo");
        System.out.println("4. Carregar Funcionários do Arquivo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarFuncionario() {
        System.out.print("Digite o nome do funcionário ");
        String nome = scanner.nextLine();

        System.out.print("Tipo de funcionário (1 - CLT, 2 - PJ): ");
        int tipo = lerInteiro();

        if (tipo == 1) {
            System.out.println("O Funcionário é Estagiario? (1 - Sim, 2 - Não)");
            int estagi = lerInteiro();
            if (estagi == 1){
                System.out.print("Digite o salário base do Estagiário: ");
                double salarioBase = lerDouble();
                if(salarioBase <= 1500) {
                    funcionarios.add(new FuncionarioCLT(nome, salarioBase));
                    System.out.println("Estágiario cadastrado!");
                }
                else {
                    salarioBase = 1500;
                    funcionarios.add(new FuncionarioCLT(nome, salarioBase));
                    System.out.println("Estágiario não pode ganhar acima de 1500, Salário Cadastrado: 1500!");
                }
            }
            else {
                System.out.print("Digite o salário base: ");
                double salarioBase = lerDouble();
                funcionarios.add(new FuncionarioCLT(nome, salarioBase));
                System.out.println("Funcionário Cadastrado");
            }
        } else if (tipo == 2) {
            System.out.print("Digite as horas trabalhadas: ");
            double horasTrabalhadas = lerDouble();
            System.out.print("Digite o valor por hora: ");
            double valorHora = lerDouble();
            funcionarios.add(new FuncionarioPJ(nome, horasTrabalhadas, valorHora));
            System.out.println("Funcionário Cadastrado!");
        } else {

            System.out.println("Tipo inválido. Funcionário não cadastrado");
        }
    }

    private static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado");
        } else {
            System.out.println("\n=== Lista de Funcionários ===");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }
    }

    private static void salvarFuncionarios() {
        try {
            FuncionarioRepository.salvarFuncionarios(funcionarios);
            System.out.println("Funcionários salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    private static void carregarFuncionarios() {
        try {
            funcionarios.clear();
            funcionarios.addAll(FuncionarioRepository.carregarFuncionarios());
            System.out.println("Funcionários carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            return lerInteiro();
        }
    }

    private static double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            return lerDouble();
        }
    }
}