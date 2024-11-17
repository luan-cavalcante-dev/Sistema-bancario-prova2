package inicio;

import java.util.ArrayList;
import java.util.Scanner;

import dados.ArquivoTipo;
import dados.GerenciaArquivos;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        // Scanner scan = new Scanner(System.in);
        // loginUI(scan);

        GerenciaArquivos gerencia = new GerenciaArquivos(ArquivoTipo.USUARIO);

        ArrayList<String> linhas = new ArrayList<String>();
        linhas.add("Bart;bart@bart.net.br;senha123;11111111111");
        
        gerencia.salvaDados(linhas);

        System.out.println("cabou");

        
    }

    public static boolean loginUI(Scanner scan){
        System.out.println("Digite o cpf:");
        String cpfTentativa = scan.nextLine();
        System.out.println("Digite a senha:");
        String senhaTentativa = scan.nextLine();
        System.out.printf("Bem Vindo %s", "nomeDoUsuário");

        return true;
    }
}
