package Aplicacao;

import java.util.Locale;
import java.util.Scanner;

import Entidades.Cliente;
import Entidades.Empresa;
import Entities.exception.InsufficientFundsException;

public class Programa {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Cliente cliente = null;
		Empresa empresa = null;

		try {
			System.out.println("==========================");
			System.out.print("Digite seu nome: ");
			String nome = sc.nextLine();
			System.out.print("Digite seu CPF: ");
			String cpf = sc.nextLine();
			cliente = new Cliente(nome, cpf);

			System.out.print("Digite o nome da empresa: ");
			String nomeemp = sc.nextLine();
			System.out.print("Digite o CNPJ da empresa: ");
			String cnpj = sc.nextLine();
			System.out.print("Digite a taxa da empresa (entre 0 e 1): ");
			double taxa = Double.parseDouble(sc.nextLine());
			empresa = new Empresa(nomeemp, cnpj, taxa);

			boolean continuar = true;
			while (continuar) {
				System.out.println("=============================================");
				System.out.println("Digite o número da opção que deseja realizar");
				System.out.println("Opção 1 = Sacar");
				System.out.println("Opção 2 = Depositar");
				System.out.println("Opção 3 = Saldo Atual");
				System.out.println("Opção 4 = Sair");
				int operacoes;
				try {
					operacoes = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Opção inválida. Por favor, digite um número.");
					continue;
				}

				switch (operacoes) {
				case 1:
					System.out.print("Digite o valor para saque: R$ ");
					try {
						double valorSaque = Double.parseDouble(sc.nextLine());
						cliente.sacar(empresa, valorSaque);
						System.out.println("Saque realizado com sucesso!");
						System.out.println(
								"Valor atual da conta da empresa: " + String.format("%.2f", empresa.getSaldo()));
					} catch (NumberFormatException e) {
						System.out.println("Valor inválido para saque. Por favor, digite um número válido.");
					} catch (InsufficientFundsException e) {
						System.out.println("Erro: " + e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println("Erro: " + e.getMessage());
					} catch (RuntimeException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;
				case 2:
					System.out.print("Digite o valor para depósito: R$ ");
					try {
						double valorDeposito = Double.parseDouble(sc.nextLine());
						cliente.depositar(empresa, valorDeposito);
						System.out.println("Depósito realizado com sucesso!");
						System.out.println(
								"Valor atual da conta da empresa: " + String.format("%.2f", empresa.getSaldo()));
					} catch (NumberFormatException e) {
						System.out.println("Valor inválido para depósito. Por favor, digite um número válido.");
					} catch (IllegalArgumentException e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;
				case 3:
					System.out.println("O saldo atual da sua conta: R$ " + String.format("%.2f", cliente.getSaldo()));
					break;
				case 4:
					continuar = false;
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}