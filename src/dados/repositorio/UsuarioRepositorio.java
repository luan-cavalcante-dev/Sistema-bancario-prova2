package dados.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dados.ArquivoTipo;
import dados.GerenciaArquivos;
import dados.estrutura.SuperDataInterface;
import pessoas.Bancario;
import pessoas.Correntista;
import pessoas.Gerente;
import pessoas.Usuario;
import pessoas.enums.TipoUsuario;

public class UsuarioRepositorio extends GerenciaArquivos implements SuperDataInterface<Usuario> {

    public UsuarioRepositorio() {
        super(ArquivoTipo.USUARIO);
    }

    @Override
    public List<Usuario> findAll() {
        List<HashMap<String, String>> dadosCrus = pegaDados();
        ArrayList<Usuario> retorno = new ArrayList<Usuario>();
        for (HashMap<String,String> hashMap : dadosCrus) {
            retorno.add(converte(hashMap));
        }
        return retorno;
    }

    @Override
    public Usuario findOne(int codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    public Usuario findByEmailAndPassword(String email, int password){
        List<Usuario> todos = findAll();
        for (Usuario usuario : todos) {
            if(usuario.getEmail().equals(email) && usuario.getSenha() == password){
                return usuario;
            }
        }
        throw new Error("Usuário não encontrado");
    }

    @Override
    public void insert(Usuario entity) {
        List<Usuario> tudo = findAll();
        tudo.add(entity);
        List<String> lista = new ArrayList<>();
        for (Usuario usuario : tudo) {
            lista.add(entidadeParaString(usuario));
        }
        salvaDados(lista);
    }

    @Override
    public void update(Usuario entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Usuario converte(Map<String,String> linha) {
        String tipo = linha.get("tipoUsuario");
        if(tipo.toLowerCase() == TipoUsuario.BANCARIO.getTipo().toLowerCase()){
            return new Bancario(
                linha.get("nome"),
                linha.get("email"),
                Integer.parseInt(linha.get("senha")),
                linha.get("cpf"));
        }
        if(tipo.toLowerCase() == TipoUsuario.GERENTE.getTipo().toLowerCase()){
            return new Gerente(
                linha.get("nome"),
                linha.get("email"),
                Integer.parseInt(linha.get("senha")),
                linha.get("cpf"));
        }
        if(tipo.toLowerCase() == TipoUsuario.CORRENTISTA.getTipo().toLowerCase()){
            return new Correntista(
                linha.get("nome"),
                linha.get("email"),
                Integer.parseInt(linha.get("senha")),
                linha.get("cpf"));
        }

        throw new Error("Tipo de Usuário desconhecido");
    }

    private String entidadeParaString(Usuario usuario) {
        String linha = String.format("%s;%s;%s;%s;%s",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getTipoUsuario());

        return linha;
    }
}
