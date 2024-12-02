package repository;

import funcionarios.Funcionario;

import java.io.*; //classe para manipular arquivo
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private static final String ARQUIVO = "funcionarios.dat";

    public static void salvarFuncionarios(List<Funcionario> funcionarios) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(funcionarios);
        }
    }

    public static List<Funcionario> carregarFuncionarios() throws IOException, ClassNotFoundException {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Funcionario>) ois.readObject();
        }
    }
}
