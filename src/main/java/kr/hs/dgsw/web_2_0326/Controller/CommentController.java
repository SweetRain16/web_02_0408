package kr.hs.dgsw.web_2_0326.Controller;

import kr.hs.dgsw.web_2_0326.Domain.Comment;
import kr.hs.dgsw.web_2_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_2_0326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public List<CommentUsernameProtocol> listComments(){
        return this.commentService.listAllComments();
    }

    @GetMapping("/comment/{id}")
    public CommentUsernameProtocol viewOneComment(@PathVariable Long id){
        return this.commentService.viewOneComment(id);
    }

    @PostMapping("/comment")
    public CommentUsernameProtocol addComment(@RequestBody Comment comment){
        return this.commentService.addComment(comment);
    }

    @PutMapping("/comment/{id}")
    public CommentUsernameProtocol updateComment(@PathVariable Long id, @RequestBody Comment comment){
        return this.commentService.updateComment(id, comment);
    }

    @DeleteMapping("/comment/{id}")
    public boolean deleteComment(@PathVariable Long id){
        return this.commentService.deleteComment(id);
    }

}
