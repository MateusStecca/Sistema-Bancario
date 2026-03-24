package banco.categorias;

import banco.ContaBancaria;

public class ContaCorrente extends ContaBancaria {
    private double limiteChequeEspecial;

    public ContaCorrente(String titular, double saldoInicial, double limiteChequeEspecial) {
        super(titular, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean debitar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para débito.");
            return false;
        }

        if (consultarSaldo() + limiteChequeEspecial >= valor) {
            alterarSaldo(-valor);
            registrarMovimentacao("Saque de R$ " + String.format("%.2f", valor) + " (conta corrente)");
            return true;
        }

        System.out.println("Saldo + limite insuficiente.");
        return false;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("\n=== CONTA CORRENTE ===");
        super.exibirInformacoes();
        System.out.printf("Limite cheque especial: R$ %.2f%n", limiteChequeEspecial);
    }
}