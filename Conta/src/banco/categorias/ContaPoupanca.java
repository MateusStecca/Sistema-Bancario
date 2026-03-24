package banco.categorias;

import banco.ContaBancaria;

public class ContaPoupanca extends ContaBancaria {
    private double taxaRendimento;

    public ContaPoupanca(String titular, double saldoInicial, double taxaRendimento) {
        super(titular, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        double rendimento = consultarSaldo() * (taxaRendimento / 100.0);
        alterarSaldo(rendimento);
        registrarMovimentacao("Rendimento aplicado de R$ " + String.format("%.2f", rendimento));
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("\n=== CONTA POUPANÇA ===");
        super.exibirInformacoes();
        System.out.printf("Taxa de rendimento: %.2f%%%n", taxaRendimento);
    }
}