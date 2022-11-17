package com.codeup.spring_blog.Controllers;

import com.codeup.spring_blog.Entities.Post;
import com.codeup.spring_blog.Entities.Users;
import com.codeup.spring_blog.Repositories.PostRepository;
import com.codeup.spring_blog.Repositories.UsersRepository;
import com.codeup.spring_blog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class PostController {



    private PostRepository postRepository;
    private UsersRepository usersRepository;
    private EmailService emailService;

    //automatically injecting need DAO dependencies into the
    //PostController class to access the database
    public PostController(PostRepository postRepository,
                          UsersRepository usersRepository,
                          EmailService emailService){
        this.emailService = emailService;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;

    }
    //mapping to the post pages get
    // request so it returns the post
    // creation page on get
    @GetMapping("/post")
    public String getPosts(Model model){
        model.addAttribute("all_post", postRepository.findAll());
        return "post/create";
    }

    //telling the controller that on post requests the user
    //data will be submitted to the controller and outputted through the html
    //model page
    @PostMapping("/post")
    public String createNewPost(
            @RequestParam("post_title") String post_title,
            @RequestParam("post_content") String post_content,
            @RequestParam("post_description") String post_description){

        Users user = (Users) usersRepository.getReferenceById(1L);
        //creating a Post
        // object to be saved
        // to the server
        new Post();
        Post post = Post.builder()
                    .title(post_title)
                    .description(post_description)
                    .content(post_content)
                    .user((Users)user)
                    .build();

            String email = post.getUser().getEmail();
            postRepository.saveAndFlush((Post) post);
            return "redirect:/post";
    }


    //path to show edit page for page with an id of ${id}
    @GetMapping("/post/{id}/edit")
    public String editPostShow(@PathVariable(name = "id") int id, Model model){
        Post post = postRepository.getReferenceById((long) id);

        model.addAttribute("id", id);
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/post/{id}/edit")
    public String editPost(@ModelAttribute(name = "post") Post post){
//        postRepository.save(post);

        emailService.prepareAndSend(post, "Alert Post Edit",
                "Someone just edited your post so ummmmmmm......could you figure it out or something");
        return "redirect:/post";
    }

}
