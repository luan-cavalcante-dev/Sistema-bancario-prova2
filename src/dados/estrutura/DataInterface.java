package dados.estrutura;

import java.util.List;

public interface DataInterface<? extends T>{
    public List<?>findAll();
    public T findOne(int codigo);
    public void insert(T entity);
    public void update(T entity);
}
