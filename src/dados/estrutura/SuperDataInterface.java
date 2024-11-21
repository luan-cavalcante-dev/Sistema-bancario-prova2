package dados.estrutura;

import java.util.Map;

public interface SuperDataInterface<T> extends DataInterface<T> {
    abstract T converte(Map<String,String> linha);
}
