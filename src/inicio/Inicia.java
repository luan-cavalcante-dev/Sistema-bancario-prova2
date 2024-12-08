package inicio;

import java.util.Scanner;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        Scanner scan = new Scanner(System.in);
        
        CLInterface clInterface = new CLInterface(scan);
        clInterface.roda();
        
        




        // GerenciaArquivos gerencia = new GerenciaArquivos(ArquivoTipo.USUARIO);

        // ArrayList<String> linhas = new ArrayList<String>();
        // linhas.add("Bart;bart@bart.net.br;senha123;222222");

        // gerencia.salvaDados(linhas);

        // System.out.println("cabou");

    }

    
}
