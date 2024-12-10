package inicio;

import java.util.EnumSet;
import java.util.Set;

import pessoas.enums.TipoUsuario;

public enum MenuSistema {
    CADASTRAR_CORRENTISTA("CADASTRAR CORRENTISTA",
            EnumSet.of(TipoUsuario.GERENTE)),
    CADASTRAR_CONTA("CADASTRAR CONTA",
            EnumSet.of(TipoUsuario.GERENTE)),
    CONFIGURAR_LIMITE_CONTA("CONFIGURAR LIMITE CONTA",
            EnumSet.of(TipoUsuario.GERENTE)),
    REALIZAR_SAQUE("REALIZAR SAQUE",
            EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE)),
    REALIZAR_DEPOSITO("REALIZAR DEPOSITO",
            EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE)),
    REALIZAR_TRANSFERENCIA("REALIZAR TRANSFERENCIA",
            EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE)),
    REALIZAR_SAQUE_CORRENTISTA("REALIZAR SAQUE",
            EnumSet.of(TipoUsuario.CORRENTISTA)),
    REALIZAR_DEPOSITO_CORRENTISTA("REALIZAR DEPOSITO",
            EnumSet.of(TipoUsuario.CORRENTISTA)),
    REALIZAR_TRANSFERENCIA_CORRENTISTA("REALIZAR TRANSFERENCIA",
            EnumSet.of(TipoUsuario.CORRENTISTA)),
    CONFIGURAR_LIMITE_ADCIONAL_CORRENTISTA("CONFIGURAR LIMITE CONTA DE CONTA ADCIONAL",
            EnumSet.of(TipoUsuario.CORRENTISTA)),
    PROCESSAR_RENDIMENTO("PROCESSAR O RENDIMENTO DAS CONTAS POUPANÇAS", 
            EnumSet.of(TipoUsuario.GERENTE));

    private Set<TipoUsuario> permissoes;
    private String nome;

    MenuSistema(String nome, Set<TipoUsuario> tipos) {
        this.nome = nome;
        this.permissoes = tipos;
    }

    public String getNome() {
        return nome;
    }

    public Set<TipoUsuario> getPermissoes() {
        return permissoes;
    }

    public boolean temAcesso(TipoUsuario tipo) {
        return permissoes.contains(tipo);
    }
}
