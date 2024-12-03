package inicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.repositorio.UsuarioRepositorio;
import pessoas.Usuario;
import pessoas.enums.TipoUsuario;

public class CLIInterface {
    public CLIInterface(Scanner scan){
        this.scan = scan;
    }
    private Scanner scan;

    private String perguntaString(String questao){
        System.out.println(questao);
        return scan.next();
    } 
    private int perguntaInt(String questao){
        System.out.println(questao);
        return scan.nextInt();
    } 
    private double perguntaDouble(String questao){
        System.out.println(questao);
        return scan.nextDouble();
    } 

    public Usuario loginUI() {
        int tentativas = 0;
        int MAX_TENTATIVAS = 3;
        while (tentativas < MAX_TENTATIVAS) {
            
            String emailTentativa = perguntaString("Digite o email:");
            int senhaTentativa = perguntaInt("Digite a senha:");

            UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
            try {
                Usuario usuario = usuarioRepositorio.findByEmailAndPassword(emailTentativa, senhaTentativa);
                System.out.printf("Bem Vindo %s", usuario.getNome());
                return usuario;
            } catch (Exception e) {
                System.out.println("Opss usuário e senha errados, por favor tente novamente !!");
                tentativas++;
            }
        }
        System.out.println("Numero de tentativas excedidas, suas informações estão sendo coletadas para averiguação !!");
        return null;
    }

    public void menuUI(TipoUsuario tipo) {
        List<MenuSistema> menusAutorizados = new ArrayList<MenuSistema>();
        for ( MenuSistema menuItem : MenuSistema.values()) {
            if(menuItem.temAcesso(tipo)){
                menusAutorizados.add(menuItem);
            }
        }
        System.out.println("Digite o numero item");
        for (int i = 0; i < menusAutorizados.size(); i++) {
            System.out.printf("%s - %s \n", i+1, menusAutorizados.get(i).getNome());
        }
        int opcao = scan.nextInt();

        if(opcao < 1 && opcao > menusAutorizados.size()){
            System.out.println("Opção inválida");
            return;
        }
        menusAutorizados.get(opcao-1);
        

    }
}
