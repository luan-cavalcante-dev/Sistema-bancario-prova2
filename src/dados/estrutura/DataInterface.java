package dados.estrutura;

import java.util.List;

public interface DataInterface<T>{
    public List<T>findAll();
    public T findOne(int codigo);
    public void insert(T entity);
    public void update(T entity);
}
