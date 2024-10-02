import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao conversor de moedas!");

        String opcao = "sim";

        while (!opcao.equalsIgnoreCase("não")) {

            if (opcao.equalsIgnoreCase("sim")) {
                System.out.println("Digite a moeda de origem (ex: USD): ");
                String base_code = sc.nextLine().toUpperCase();

                System.out.println("Digite a moeda de destino (ex: BRL): ");
                String target_code = sc.nextLine().toUpperCase();

                System.out.println("Digite o valor a ser convertido: ");
                double amount = sc.nextDouble();

                requestAPIMoeda request = new requestAPIMoeda();
                conversaoResult result = request.valorMoeda(base_code, target_code, amount);

                System.out.println("Cotação atual: " + result.getConversionRate());
                System.out.println("Valor convertido: " + result.getConvertedAmount() + " " + target_code);
            }

            System.out.println("Deseja realizar outra conversão? (sim/não)");
            sc.nextLine(); // Consumir a nova linha
            opcao = sc.nextLine().toLowerCase();
        }

        System.out.println("Obrigado por utilizar o conversor de moedas!");
        sc.close();
    }
}