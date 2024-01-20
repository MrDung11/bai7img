package service;

import model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import repository.IReviewRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceIMPL implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }


    @Override
    public Page<Review> findDateOnDay(Pageable pageable) {

        List<Review> reviewList = (List<Review>) findAll(pageable);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String dates = dtf.format(now);

        List<Review> reviewOnDay = new ArrayList<>();

        for (int i = 0; i < reviewList.size(); i++) {

            System.out.println("Test2: " + reviewList.get(i).getDates().substring(0, 10));

            if (reviewList.get(i).getDates().substring(0, 10).equals(dates)) {
                System.out.println(reviewList.get(i).getDates().substring(0, 10).equals(dates));
                reviewOnDay.add(reviewList.get(i));
            }

        }
//        Page<Review> reviewPage = (Page<Review>) reviewOnDay;
        return (Page<Review>) reviewOnDay;
    }
}
