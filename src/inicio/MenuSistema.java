package inicio;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import pessoas.enums.TipoUsuario;

public enum MenuSistema {
    CADASTRAR_CORRENTISTA("CADASTRAR_CORRENTISTA", 
        EnumSet.of(TipoUsuario.GERENTE)), 
    CONFIGURAR_LIMITE_CONTA("CONFIGURAR_LIMITE_CONTA",
        EnumSet.of(TipoUsuario.GERENTE)),
    REALIZAR_SAQUE("REALIZAR_SAQUE",
        EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE)),
    REALIZAR_DEPOSITO("REALIZAR_DEPOSITO",
        EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE)),
    REALIZAR_TRANSFERENCIA("REALIZAR_TRANSFERENCIA",
        EnumSet.of(TipoUsuario.BANCARIO, TipoUsuario.GERENTE));

    private Set<TipoUsuario> permissoes;
    private String nome;

    MenuSistema(String nome, Set<TipoUsuario> tipos){
        this.nome = nome;
        this.permissoes = tipos;
    }
    public String getNome() {
        return nome;
    }
    public Set<TipoUsuario> getPermissoes() {
        return permissoes;
    }

    public boolean temAcesso(TipoUsuario tipo){
        return permissoes.contains(tipo);
    }
}
