package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllUserBlogs(){ //Only admin can access this endpoint
        return blogRepository.findAll();
    }
    public List<Blog> getMyBlog(Integer user_id){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user == null){throw new ApiException("Wrong user id");}

        return blogRepository.findAllByUser(user);
    }

    public void addMyBlog(Integer user_id,Blog blog) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateMyBlog(Integer user_id,Integer index,Blog blog) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Blog blog1 = blogRepository.findBlogById(index);
        if(blog1 == null) {throw new ApiException("Wrong blog id");}

        if (!blog1.getUser().getId().equals(user.getId())){throw new ApiException("The blog not updating because it doesn't belong to the same user");}

        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteMyBlog(Integer user_id,Integer index) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Blog blog = blogRepository.findBlogById(index);
        if(blog == null) {throw new ApiException("blog not found");}

        if (!blog.getUser().getId().equals(user.getId())){throw new ApiException("The blog not deleting because it doesn't belong to the same user");}

        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer id) {
        Blog blog = blogRepository.findBlogById(id);
        if (blog == null) {throw new ApiException("blog not found");}

        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {throw new ApiException("blog not found");}

        return blog;
    }

}
