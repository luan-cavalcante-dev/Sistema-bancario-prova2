package pessoas;

import pessoas.enums.TipoUsuario;

public class Gerente extends Usuario {
    public Gerente(String nome, String email, int senha, String cpf){
        super(nome, email, senha, cpf, TipoUsuario.GERENTE);
    }
}
