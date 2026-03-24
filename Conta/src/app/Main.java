package app;

import banco.Banco;
import banco.ContaBancaria;
import banco.categorias.ContaCorrente;
import banco.categorias.ContaPoupanca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        int opcao = -1;

        do {
            try {
                System.out.println("\n=== SISTEMA BANCÁRIO ===");
                System.out.println("1 - Cadastrar Conta Corrente");
                System.out.println("2 - Cadastrar Conta Poupança");
                System.out.println("3 - Depositar");
                System.out.println("4 - Sacar");
                System.out.println("5 - Transferir");
                System.out.println("6 - Aplicar rendimento");
                System.out.println("7 - Exibir dados de uma conta");
                System.out.println("8 - Exibir extrato");
                System.out.println("9 - Listar todas as contas");
                System.out.println("10 - Exibir total de contas");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Titular: ");
                        String titular = sc.nextLine();
                        System.out.print("Saldo inicial: ");
                        double saldo = sc.nextDouble();
                        System.out.print("Limite do cheque especial: ");
                        double limite = sc.nextDouble();

                        banco.adicionarConta(new ContaCorrente(titular, saldo, limite));
                    }

                    case 2 -> {
                        System.out.print("Titular: ");
                        String titular = sc.nextLine();
                        System.out.print("Saldo inicial: ");
                        double saldo = sc.nextDouble();
                        System.out.print("Taxa de rendimento (%): ");
                        double taxa = sc.nextDouble();

                        banco.adicionarConta(new ContaPoupanca(titular, saldo, taxa));
                    }

                    case 3 -> {
                        ContaBancaria conta = localizarConta(sc, banco);
                        if (conta != null) {
                            System.out.print("Valor do depósito: ");
                            double valor = sc.nextDouble();
                            if (conta.creditar(valor)) {
                                System.out.println("Depósito realizado com sucesso.");
                            }
                        }
                    }

                    case 4 -> {
                        ContaBancaria conta = localizarConta(sc, banco);
                        if (conta != null) {
                            System.out.print("Valor do saque: ");
                            double valor = sc.nextDouble();
                            if (conta.debitar(valor)) {
                                System.out.println("Saque realizado com sucesso.");
                            }
                        }
                    }

                    case 5 -> {
                        System.out.print("Número da conta de origem: ");
                        int numOrigem = sc.nextInt();
                        System.out.print("Agência da conta de origem: ");
                        int agOrigem = sc.nextInt();

                        System.out.print("Número da conta de destino: ");
                        int numDestino = sc.nextInt();
                        System.out.print("Agência da conta de destino: ");
                        int agDestino = sc.nextInt();

                        System.out.print("Valor da transferência: ");
                        double valor = sc.nextDouble();

                        banco.transferir(numOrigem, agOrigem, numDestino, agDestino, valor);
                    }

                    case 6 -> {
                        ContaBancaria conta = localizarConta(sc, banco);
                        if (conta instanceof ContaPoupanca poupanca) {
                            poupanca.aplicarRendimento();
                            System.out.println("Rendimento aplicado com sucesso.");
                        } else if (conta != null) {
                            System.out.println("A conta informada não é poupança.");
                        }
                    }

                    case 7 -> {
                        ContaBancaria conta = localizarConta(sc, banco);
                        if (conta != null) {
                            conta.exibirInformacoes();
                        }
                    }

                    case 8 -> {
                        ContaBancaria conta = localizarConta(sc, banco);
                        if (conta != null) {
                            conta.exibirExtrato();
                        }
                    }

                    case 9 -> banco.listarContas();

                    case 10 -> {
                        System.out.println("Banco: " + ContaBancaria.getBanco());
                        System.out.println("Total de contas cadastradas: " + ContaBancaria.getTotalContas());
                    }

                    case 0 -> System.out.println("Encerrando o sistema...");

                    default -> System.out.println("Opção inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite o tipo correto de dado.");
                sc.nextLine();
            }

        } while (opcao != 0);

        sc.close();
    }

    private static ContaBancaria localizarConta(Scanner sc, Banco banco) {
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        System.out.print("Agência: ");
        int agencia = sc.nextInt();

        ContaBancaria conta = banco.buscarConta(numero, agencia);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
        }

        return conta;
    }
}