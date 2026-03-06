package pl.engineerbookplus.www.model.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Object> {

    T addRecord(T t);

    T searchRecordById(Serializable id);

    T loadRecordById(Serializable id);

    List<T> getAllRecords();

    T editRecord(T t);

    T eraseRecord(T t);

    T eraseRecordById(Serializable id);

    int eraseAllRecords();

    long numberOfRecords();

    boolean isExistRecordById(Serializable id);

}
