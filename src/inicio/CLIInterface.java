package inicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contas.ContaCorrenteprincipal;
import contas.Contaprincipal;
import dados.repositorio.ContaRepositorio;
import dados.repositorio.UsuarioRepositorio;
import pessoas.Correntista;
import pessoas.Usuario;
import pessoas.enums.TipoUsuario;

public class CLIInterface {
    public CLIInterface(Scanner scan) {
        this.scan = scan;

        // começa a UI
    }

    private Scanner scan;

    private String perguntaString(String questao) {
        System.out.println(questao);
        return scan.next();
    }

    private int perguntaInt(String questao) {
        System.out.println(questao);
        return scan.nextInt();
    }

    private double perguntaDouble(String questao) {
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
        System.out
                .println("Numero de tentativas excedidas, suas informações estão sendo coletadas para averiguação !!");
        return null;
    }

    public MenuSistema menuUI(TipoUsuario tipo) {
        List<MenuSistema> menusAutorizados = new ArrayList<MenuSistema>();
        for (MenuSistema menuItem : MenuSistema.values()) {
            if (menuItem.temAcesso(tipo)) {
                menusAutorizados.add(menuItem);
            }
        }
        System.out.println("Digite o numero item");
        for (int i = 0; i < menusAutorizados.size(); i++) {
            System.out.printf("%s - %s \n", i + 1, menusAutorizados.get(i).getNome());
        }
        int opcao = scan.nextInt();

        if (opcao < 1 && opcao > menusAutorizados.size()) {
            System.out.println("Opção inválida");
            return null;
        }
        MenuSistema selecionado = menusAutorizados.get(opcao - 1);
        return selecionado;
    }

    private void mostraUI(MenuSistema selecionado) {
        switch (selecionado) {
            case MenuSistema.CADASTRAR_CORRENTISTA:
                cadastraCorrentistaUI();
                break;
            case MenuSistema.CONFIGURAR_LIMITE_CONTA:
                configuraLimiteContaUI();
                break;
            case MenuSistema.REALIZAR_SAQUE:
                realizaSaqueUI();
                break;
            case MenuSistema.REALIZAR_DEPOSITO:
                break;
            case MenuSistema.REALIZAR_TRANSFERENCIA:
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private void realizaSaqueUI() {
        
    }

    private void configuraLimiteContaUI() {
        int codigoConta = perguntaInt("Qual o numero da conta?");
        ContaRepositorio contarepositorio = new ContaRepositorio();
        Contaprincipal conta = contarepositorio.findOne(codigoConta);
        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }
        if (!(conta instanceof ContaCorrenteprincipal)) {
            System.out.println("Conta não é corrente");
            return;
        }

        double novoLimite = perguntaDouble("Qual o novo limite?");
        ((ContaCorrenteprincipal) conta).setLimitechequeEspecial(novoLimite);
    }

    private void cadastraCorrentistaUI() {
        String nome = perguntaString("Digite o nome do Correntista");
        String email = perguntaString("Digite o email do Correntista");
        int senha = perguntaInt("Digite o senha numerica do Correntista");
        String cpf = perguntaString("Digite o cpf do Correntista");
        Correntista novoCorrentista = new Correntista(nome, email, senha, cpf);
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        usuarioRepositorio.insert(novoCorrentista);
        System.out.println("Correntista Cadastrado com sucesso");
    }

    public void roda() {
        Usuario usuario = loginUI();

        if (usuario == null) {
            return;
        }
        do {
            MenuSistema itemMenuSelecionado = menuUI(usuario.getTipoUsuario());
            mostraUI(itemMenuSelecionado);
        } while (perguntaString("Deseja continuar? (s ou n)").equalsIgnoreCase("s"));
    }

}
