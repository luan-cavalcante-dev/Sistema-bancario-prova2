package inicio;

import java.util.Scanner;

import contas.ContaPoupanca;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        Scanner scan = new Scanner(System.in);
        
        CLInterface clInterface = new CLInterface(scan);
        clInterface.roda();
    }

    
}
