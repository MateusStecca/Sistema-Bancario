package banco;

import java.util.ArrayList;

public class Banco {
    private ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
        System.out.println("Conta cadastrada com sucesso.");
        System.out.println("Número da conta: " + conta.getNumConta());
    }

    public ContaBancaria buscarConta(int numeroConta, int agencia) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta && conta.getAgencia() == agencia) {
                return conta;
            }
        }
        return null;
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (ContaBancaria conta : contas) {
            conta.exibirInformacoes();
        }
    }

    public boolean transferir(int numOrigem, int agOrigem, int numDestino, int agDestino, double valor) {
        ContaBancaria origem = buscarConta(numOrigem, agOrigem);
        ContaBancaria destino = buscarConta(numDestino, agDestino);

        if (origem == null || destino == null) {
            System.out.println("Conta de origem ou destino não encontrada.");
            return false;
        }

        if (valor <= 0) {
            System.out.println("Valor inválido para transferência.");
            return false;
        }

        if (origem.debitar(valor)) {
            destino.creditar(valor);

            origem.registrarMovimentacao("Transferência enviada de R$ " + String.format("%.2f", valor)
                    + " para conta " + destino.getNumConta());

            destino.registrarMovimentacao("Transferência recebida de R$ " + String.format("%.2f", valor)
                    + " da conta " + origem.getNumConta());

            System.out.println("Transferência realizada com sucesso.");
            return true;
        }

        System.out.println("Não foi possível concluir a transferência.");
        return false;
    }
}