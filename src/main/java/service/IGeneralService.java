package service;

import model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {

    Page<T> findAll(Pageable pageable);


    Optional<T> findById(Long id);


    void save(T t);

    void deleteById(Long id);
}
