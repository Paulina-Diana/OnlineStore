package edu.uoc.onlinestore.model;

import edu.uoc.onlinestore.dao.GenericDAO;
import edu.uoc.onlinestore.dao.factory.MysqlDAOFactory;

import java.util.ArrayList;
import java.util.List;

public class ListStore <T> extends MysqlDAOFactory {
    private T genericItem;
    private List<T> lists;
    public ListStore(){
        this.lists = new ArrayList<>();
    }

    public List<T> getLists() {
        return lists;
    }

    public List<T> getAll(List<T> list) throws Exception{
        for(T listItem : list) {
            this.lists.add(listItem);
        }
        return lists;
    }

    /*public void add(T t) throws Exception{
        lists.add(t);
    }*/

    public void add(T t) throws Exception{
        this.genericItem = t;
    }
    public T getGenericItem(){
        return this.genericItem;
    };

    public int getSize() {
        return this.lists.size();
    }

    public void delete(T t) throws Exception{
        this.lists.remove(t);
    }

    public T getAt(int position) {
        return this.lists.get(position);
    }

    public void clear() throws Exception {
        this.lists.clear();
    }

    public boolean isEmpty() {
        return lists.isEmpty();
    }

}