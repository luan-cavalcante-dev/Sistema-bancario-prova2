package inicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contas.ContaCorrenteAdcional;
import contas.ContaCorrenteprincipal;
import contas.ContaPoupanca;
import contas.Contaprincipal;
import contas.ErroBanco;
import contas.TipoConta;
import dados.repositorio.ContaRepositorio;
import dados.repositorio.UsuarioRepositorio;
import pessoas.Correntista;
import pessoas.Usuario;
import pessoas.enums.TipoUsuario;

public class CLInterface {
    public CLInterface(Scanner scan) {
        this.scan = scan;

        // começa a UI
    }

    private Scanner scan;
    private Usuario usuarioLogado;

    private String perguntaString(String questao) {
        System.out.println(questao);
        return scan.next();
    }

    private String perguntaStringLine(String questao) {
        System.out.println(questao);
        return scan.nextLine();
    }

    private int perguntaInt(String questao) {
        System.out.println(questao);
        int intDigitado = scan.nextInt();
        scan.nextLine(); // limpei a o buffer
        return intDigitado;
    }

    private double perguntaDouble(String questao) {
        System.out.println(questao);
        double doub = scan.nextDouble();
        scan.nextLine();
        return doub;
    }

    public Usuario loginUI() {
        int tentativas = 0;
        int MAX_TENTATIVAS = 3;
        while (tentativas < MAX_TENTATIVAS) {

            String cpfTentativa = perguntaString("Digite o CPF:");
            int senhaTentativa = perguntaInt("Digite a senha:");

            UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
            try {
                Usuario usuario = usuarioRepositorio.encontraPeloCPFeSenha(cpfTentativa, senhaTentativa);
                System.out.printf("\nBem Vindo %s\n\n", usuario.getNome());
                return usuario;
            } catch (ErroBanco e) {
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
        scan.nextLine();

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
                realizaDepositoUI();
                break;
            case MenuSistema.REALIZAR_TRANSFERENCIA:
                realizaTransferenciaUI();
                break;
            case MenuSistema.REALIZAR_SAQUE_CORRENTISTA:
                realizaSaqueCorrentistaUI();
                break;
            case MenuSistema.REALIZAR_DEPOSITO_CORRENTISTA:
                realizaDepositoCorrentistaUI();
                break;
            case MenuSistema.REALIZAR_TRANSFERENCIA_CORRENTISTA:
                realizaTransferenciaCorrentistaUI();
                break;
            case MenuSistema.CADASTRAR_CONTA:
                realizaCadastraContaUI();
                break;
            case MenuSistema.CONFIGURAR_LIMITE_ADCIONAL_CORRENTISTA:
                configuraLimiteAdcionalCorrentistaUI();
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private void configuraLimiteAdcionalCorrentistaUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null || !conta.getCpfTitular().equals(usuarioLogado.getCpf())) {
            System.out.println("Conta não encontrada");
            return;
        }
        if (!(conta instanceof ContaCorrenteAdcional)) {
            System.out.println("Conta não é Adcional");
            return;
        }

        double novoLimite = perguntaDouble("Qual o novo limite?");
        ContaCorrenteAdcional contaCorrenteAdcional = (ContaCorrenteAdcional) conta;
        contaCorrenteAdcional.setLimite(novoLimite);
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        contaRepositorio.update(contaCorrenteAdcional);

        System.out.println("Limite alterado com sucesso");
    }

    private void realizaCadastraContaUI() {
        int codigoConta = perguntaInt("Qual o CPF do titular?");
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        Usuario usuario = usuarioRepositorio.findOne(codigoConta);
        if (usuario == null
                || !usuario.getTipoUsuario().getTipo().equalsIgnoreCase(TipoUsuario.CORRENTISTA.getTipo())) {
            System.out.println("Usuário nao encontrado");
            return;
        }
        System.out.println("Qual o tipo da conta?");
        System.out.println("1 - Conta Poupanca");
        System.out.println("2 - Conta Corrente Adcional");
        int opcao = scan.nextInt();
        scan.nextLine();

        ContaRepositorio contaRepositorio = new ContaRepositorio();
        int numeroDeConta = contaRepositorio.buscaProximoNumeroDeConta();

        Contaprincipal novaConta;
        if (opcao == 1) {
            double saldo = perguntaDouble("Qual o valor do saldo inicial?");
            novaConta = new ContaPoupanca(numeroDeConta, saldo, usuario.getCpf());
        } else if (opcao == 2) {
            double limite = perguntaDouble("Qual o limite da conta adcional?");
            int numeroDeContaPrincipal = -1;
            for (Contaprincipal conta : contaRepositorio.buscaContaPorTitular(usuario.getCpf())) {
                if (conta.getTipoDeconta().equals(TipoConta.CORRENTE)) {
                    numeroDeContaPrincipal = conta.getNumerodaConta();
                    break;
                }
            }
            if (numeroDeContaPrincipal == -1) {
                System.out.println("Nao foi possivel encontrar uma conta corrente");
                return;
            }
            novaConta = new ContaCorrenteAdcional(numeroDeConta, usuario.getCpf(), limite, numeroDeContaPrincipal);
        } else {
            System.out.println("Opção inválida");
            return;
        }

        contaRepositorio.insert(novaConta);
    }

    private void realizaTransferenciaCorrentistaUI() {
        System.out.println("Conta Origem");
        Contaprincipal contaOrigem = buscaConta();
        if (contaOrigem == null || !contaOrigem.getCpfTitular().equals(usuarioLogado.getCpf())) {
            System.out.println("Conta nao encontrada");
            return;
        }
        if (contaOrigem instanceof ContaPoupanca || contaOrigem instanceof ContaCorrenteAdcional) {
            System.out.println("Conta Poupanca e Conta Adcional nao pode realizar transferencias");
            return;
        }
        System.out.println("Conta Destino");
        Contaprincipal contaDestino = buscaConta();
        if (contaDestino == null) {
            System.out.println("Conta nao encontrada");
            return;
        }
        if (contaDestino instanceof ContaCorrenteAdcional) {
            System.out.println("Conta Adcional nao pode receber transferencias");
            return;
        }
        double valor = perguntaDouble("Digite o valor a ser transferido");
        try {
            contaOrigem.transferencia(contaDestino, valor);
        } catch (ErroBanco e) {
            System.out.println("Nao foi possivel realizar a transferencia");
            System.out.println(e.getMessage());
            return;
        }
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        contaRepositorio.update(contaOrigem);
        contaRepositorio.update(contaDestino);
        System.out.println("Transferencia realizada com sucesso");
    }

    private void realizaDepositoCorrentistaUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null || !conta.getCpfTitular().equals(usuarioLogado.getCpf())) {
            System.out.println("Conta nao encontrada");
            return;
        }
        double valor = perguntaDouble("Digite o valor a ser depositado");
        conta.depositar(valor);
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        contaRepositorio.update(conta);
        System.out.println("Deposito realizado com sucesso");
    }

    private void realizaSaqueCorrentistaUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null || !conta.getCpfTitular().equals(usuarioLogado.getCpf())) {
            System.out.println("Conta nao encontrada");
            return;
        }

        double valor = perguntaDouble("Digite o valor a ser sacado");
        try {
            conta.sacar(valor);
            if (conta instanceof ContaCorrenteAdcional) {
                conta = ((ContaCorrenteAdcional) conta).getContaPrincipal();
            }
            ContaRepositorio contaRepositorio = new ContaRepositorio();
            contaRepositorio.update(conta);
        } catch (ErroBanco e) {
            System.out.println("Nao foi possivel realizar o saque");
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Saque realizado com sucesso");
    }

    private void realizaTransferenciaUI() {
        System.out.println("Conta Origem");
        Contaprincipal contaOrigem = buscaConta();
        if (contaOrigem == null) {
            System.out.println("Conta Origem nao encontrada");
            return;
        }
        System.out.println("Conta destino");
        Contaprincipal contaDestino = buscaConta();
        if (contaDestino == null) {
            System.out.println("Conta Destino nao encontrada");
            return;
        }
        double valor = perguntaDouble("Digite o valor a ser transferido");
        try {
            contaOrigem.transferencia(contaDestino, valor);
            ContaRepositorio contaRepositorio = new ContaRepositorio();
            contaRepositorio.update(contaOrigem);
            contaRepositorio.update(contaDestino);
        } catch (ErroBanco e) {
            System.out.println("Não foi possivel realizar a transferencia");
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Transferencia realizada com sucesso");

    }

    private void realizaDepositoUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }
        double valor = perguntaDouble("Digite o valor a ser depositado");
        conta.depositar(valor);
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        contaRepositorio.update(conta);
        System.out.println("Deposito realizado com sucesso");
    }

    private void realizaSaqueUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }
        double valor = perguntaDouble("Digite o valor a ser sacado");
        try {
            conta.sacar(valor);
        } catch (ErroBanco e) {
            System.out.println("Não foi possivel realizar o saque");
            System.out.println(e.getMessage());
            return;
        }
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        if (conta instanceof ContaCorrenteAdcional) {
            conta = ((ContaCorrenteAdcional) conta).getContaPrincipal();
        }
        contaRepositorio.update(conta);
        System.out.println("Saque realizado com sucesso");
    }

    private Contaprincipal buscaConta() {
        int codigoConta = perguntaInt("Qual o numero da conta?");
        ContaRepositorio contarepositorio = new ContaRepositorio();
        Contaprincipal conta = contarepositorio.findOne(codigoConta);
        return conta;
    }

    private void configuraLimiteContaUI() {
        Contaprincipal conta = buscaConta();
        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }
        if (!(conta instanceof ContaCorrenteprincipal)) {
            System.out.println("Conta não é corrente");
            return;
        }

        double novoLimite = perguntaDouble("Qual o novo limite?");
        ContaCorrenteprincipal contaCorrentePrincipal = (ContaCorrenteprincipal) conta;
        contaCorrentePrincipal.setLimitechequeEspecial(novoLimite);
        ContaRepositorio contaRepositorio = new ContaRepositorio();
        contaRepositorio.update(contaCorrentePrincipal);
    }

    private void cadastraCorrentistaUI() {
        String nome = perguntaStringLine("Digite o nome do Correntista");
        String email = perguntaString("Digite o email do Correntista");
        int senha = perguntaInt("Digite o senha numerica do Correntista");
        String cpf = perguntaString("Digite o cpf do Correntista");

        double saldoInicial = perguntaDouble("Qual o saldo inicial?");
        double limiteChequeEspecial = perguntaDouble("Qual o limite do cheque especial?");

        Correntista novoCorrentista = new Correntista(nome, email, senha, cpf);
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        usuarioRepositorio.insert(novoCorrentista);

        ContaRepositorio contaRepositorio = new ContaRepositorio();
        int numeroDeConta = contaRepositorio.buscaProximoNumeroDeConta();
        Contaprincipal novaConta = new ContaCorrenteprincipal(numeroDeConta, saldoInicial, cpf, limiteChequeEspecial);
        contaRepositorio.insert(novaConta);

        System.out.println("Correntista Cadastrado com sucesso");
        System.out.println("Criada a conta corrente com o numero " + numeroDeConta);
    }

    public void roda() {
        Usuario usuario = loginUI();

        if (usuario == null) {
            return;
        }
        this.usuarioLogado = usuario;
        do {
            MenuSistema itemMenuSelecionado = menuUI(usuario.getTipoUsuario());
            mostraUI(itemMenuSelecionado);
        } while (perguntaString("Deseja continuar? (s ou n)").equalsIgnoreCase("s"));
    }

}
