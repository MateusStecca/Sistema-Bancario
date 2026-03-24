package banco;

import java.util.ArrayList;

public class ContaBancaria {
    private String titular;
    private int numConta;
    private int agencia;
    private double saldo;

    public static final String BANCO = "Banco Central XYZ";
    private static int totalContas = 0;
    private static int proximoNumeroConta = 1000;

    private ArrayList<String> extrato = new ArrayList<>();

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.agencia = 1;
        this.numConta = proximoNumeroConta++;
        totalContas++;

        extrato.add("Conta criada com saldo inicial de R$ " + String.format("%.2f", saldoInicial));
    }

    protected void alterarSaldo(double valor) {
        this.saldo += valor;
    }

    public boolean creditar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para depósito.");
            return false;
        }

        alterarSaldo(valor);
        extrato.add("Depósito de R$ " + String.format("%.2f", valor));
        return true;
    }

    public boolean debitar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para débito.");
            return false;
        }

        if (saldo >= valor) {
            alterarSaldo(-valor);
            extrato.add("Saque de R$ " + String.format("%.2f", valor));
            return true;
        }

        System.out.println("Saldo insuficiente.");
        return false;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void registrarMovimentacao(String mensagem) {
        extrato.add(mensagem);
    }

    public void exibirExtrato() {
        System.out.println("\n=== EXTRATO DA CONTA ===");
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numConta);
        for (String mov : extrato) {
            System.out.println("- " + mov);
        }
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public void exibirInformacoes() {
        System.out.println("Titular: " + titular);
        System.out.println("Número da Conta: " + numConta);
        System.out.println("Agência: " + agencia);
        System.out.printf("Saldo: R$ %.2f%n", saldo);
        System.out.println("Banco: " + BANCO);
    }

    public static int getTotalContas() {
        return totalContas;
    }

    public static String getBanco() {
        return BANCO;
    }

    public int getNumConta() {
        return numConta;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getTitular() {
        return titular;
    }
}