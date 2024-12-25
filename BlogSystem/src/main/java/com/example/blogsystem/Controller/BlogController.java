package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-my-blog")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(blogService.getMyBlog(myUser.getId()));
    }

    @PostMapping("/add-my-blog")
    public ResponseEntity addMyBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog) {
        blogService.addMyBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added"));
    }

    @PutMapping("/update-my-blog/{index}")
    public ResponseEntity updateMyBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer index, @RequestBody @Valid Blog blog){
        blogService.updateMyBlog(myUser.getId(), index, blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated"));
    }

    @DeleteMapping("/delete-my-blog/{index}")
    public ResponseEntity deleteMyBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer index){
        blogService.deleteMyBlog(myUser.getId(), index);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted"));
    }

    @GetMapping("/get-blog-by-id/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

    @GetMapping("/get-blog-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal MyUser myUser, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

}
