package controller;

import model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IReviewService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FeedBackController {

    @Autowired
    private IReviewService reviewServiceIMPL;

    @ModelAttribute("userVotes")
    public int[] setupUserVote() {
        return new int[]{1, 2, 3, 4, 5};
    }


    @GetMapping("/")
    public ModelAndView showInformationDates(Pageable pageable){

        Page<Review> reviewPage;
        reviewPage = reviewServiceIMPL.findDateOnDay(pageable);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("list_date", reviewPage);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showImage(@ModelAttribute("review") Review review){

        Review review1 = new Review();
        String author = "";

        ModelAndView modelAndView = new ModelAndView("/create");

        List<Review> reviewList = reviewServiceIMPL.findAll();

        if(reviewList.isEmpty()){
            modelAndView.addObject("no_author",  "no_author");
        }else {
            author = reviewList.get(0).getAuthor();
            modelAndView.addObject("author", author);
        }
        modelAndView.addObject("review", review1);
        return modelAndView;
    }


    @PostMapping("/create")
    public ModelAndView saveComment(@ModelAttribute("review") Review review){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView = new ModelAndView("/create");

        ModelAndView modelAndView = new ModelAndView("/create");

        if(review.getFeedBack().trim().equals("")){
            modelAndView.addObject("error", "Chua nhap comment");
        }else {

            // Set thoi gian
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String dates = dtf.format(now);

            review.setDates(dates);
//            System.out.println("test date **************" + dates);
            reviewServiceIMPL.save(review);
        }
        return modelAndView;
    }

    @GetMapping("/like/{id}")
    public String increaseLike(@PathVariable Long id){
//        System.out.println("test like " + id);
        Review review = reviewServiceIMPL.findById(id).get();
        review.setLikes(review.getLikes() + 1);
        reviewServiceIMPL.save(review);

        return "redirect:/";
    }

    // Đang bị hiện tượng nếu Author nhập vào cùng comment lần đầu mà xóa đi thì xóa cả
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Review review = reviewServiceIMPL.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("review", review);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteReview(@ModelAttribute("review") Review review) {
        reviewServiceIMPL.deleteById(review.getId());
        return "redirect:/";
    }

    // Đang bị lỗi Edit
    // Su khi edit là lỗi chương trình luôn
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Review review = reviewServiceIMPL.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("review", review);
        return modelAndView;

    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("review") Review review) {
        reviewServiceIMPL.save(review);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("review", review);
        modelAndView.addObject("message", "Review updated successfully");
        return modelAndView;
    }

}
