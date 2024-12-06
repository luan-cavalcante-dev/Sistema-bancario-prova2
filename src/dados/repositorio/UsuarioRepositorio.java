package dados.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contas.ErroBanco;
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
            retorno.add(converteMapParaEntidade(hashMap));
        }
        return retorno;
    }

    @Override
    public Usuario findOne(int codigo) {
        List<Usuario> todos = this.findAll();
        for (Usuario usuario : todos) {
            if(usuario.getCpf().equals(Integer.toString(codigo))){
                return usuario;
            }
        }
        return null;
    }

    public Usuario findByEmailAndPassword(String email, int password){
        List<Usuario> todos = findAll();
        for (Usuario usuario : todos) {
            if(usuario.getEmail().equals(email) && usuario.getSenha() == password){
                return usuario;
            }
        }
        throw new ErroBanco("Usuário não encontrado");
    }

    @Override
    public void insert(Usuario entity) {
        List<Usuario> tudo = findAll();
        tudo.add(entity);
        salvaEntidades(tudo);
    }

    public void salvaEntidades(List<Usuario> tudo){
        List<String> lista = new ArrayList<>();
        for (Usuario usuario : tudo) {
            lista.add(entidadeParaString(usuario));
        }
        salvaDados(lista);
    }

    @Override
    public void update(Usuario entity) {
        List<Usuario> tudo = findAll();
        for (Usuario usuario : tudo) {
            if(usuario.getCpf().equals(entity.getCpf())){
                usuario = entity;
            }
        }
        salvaEntidades(tudo);
    }

    @Override
    public Usuario converteMapParaEntidade(Map<String,String> linha) {
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
