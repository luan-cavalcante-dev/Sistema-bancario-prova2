package inicio;

import java.util.Scanner;

import pessoas.Usuario;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        Scanner scan = new Scanner(System.in);
        
        CLIInterface cliInterface = new CLIInterface(scan);

        cliInterface.roda();
        
        




        // GerenciaArquivos gerencia = new GerenciaArquivos(ArquivoTipo.USUARIO);

        // ArrayList<String> linhas = new ArrayList<String>();
        // linhas.add("Bart;bart@bart.net.br;senha123;222222");

        // gerencia.salvaDados(linhas);

        // System.out.println("cabou");

    }

    
}
