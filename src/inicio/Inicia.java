package inicio;

import java.util.ArrayList;
import java.util.Scanner;

import dados.ArquivoTipo;
import dados.GerenciaArquivos;
import dados.repositorio.UsuarioRepositorio;
import pessoas.Usuario;

public class Inicia {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema Bancário BML");

        Scanner scan = new Scanner(System.in);
        Usuario user = loginUI(scan);

        // GerenciaArquivos gerencia = new GerenciaArquivos(ArquivoTipo.USUARIO);

        // ArrayList<String> linhas = new ArrayList<String>();
        // linhas.add("Bart;bart@bart.net.br;senha123;11111111111");
        
        // gerencia.salvaDados(linhas);

        System.out.println("cabou");

        
    }

    public static Usuario loginUI(Scanner scan){

        int tentativas = 0;
        int MAX_TENTATIVAS = 3;
        while (tentativas < MAX_TENTATIVAS) {
            System.out.println("Digite o email:");
            String emailTentativa = scan.nextLine();
            System.out.println("Digite a senha:");
            int senhaTentativa = scan.nextInt();
            System.out.printf("Bem Vindo %s", "nomeDoUsuário");
    
            UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
            try {
                return usuarioRepositorio.findByEmailAndPassword(emailTentativa, senhaTentativa);
            } catch (Exception e) {
                System.out.println("Opss usuário e senha errados, por favor tente novamente !!");
                tentativas ++;
            }
        }

        throw new Error("Numero de tentativas excedidas, suas informações estão sendo coletadas para averiguação !!");
    }
}
