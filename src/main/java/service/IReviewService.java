package service;

import model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.IGeneralService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface IReviewService extends IGeneralService<Review> {


    Page<Review> findDateOnDay(Pageable pageable);

    List<Review> findAll();

}


