package dados.estrutura;

import java.util.Map;

public interface SuperDataInterface<T> extends DataInterface<T> {
    abstract T converteMapParaEntidade(Map<String,String> linha);
}
