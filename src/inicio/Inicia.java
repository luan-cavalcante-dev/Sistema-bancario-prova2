package inicio;

import java.util.Scanner;

import contas.ContaPoupanca;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        Scanner scan = new Scanner(System.in);
        
        // CLInterface clInterface = new CLInterface(scan);
        // clInterface.roda();
        
        ContaPoupanca cp = new ContaPoupanca(1, 1000, "123.456.789-00");
        cp.calculaJuros();
        System.out.println(cp.getSaldo());

    }

    
}
