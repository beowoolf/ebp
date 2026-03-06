package pl.engineerbookplus.www.service;

public interface Service<T extends Object> {

    T addRecord(T t);

    T editRecord(T t);

    T eraseRecord(T t);

}
