package kr.hs.dgsw.web_2_0326.Service;

import kr.hs.dgsw.web_2_0326.Domain.Comment;
import kr.hs.dgsw.web_2_0326.Domain.User;
import kr.hs.dgsw.web_2_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_2_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_2_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("aaa", "aaa@dgsw"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));
    }

    private String getUsernameByComment(Comment comment) {
        return this.userRepository.findById(comment.getUserId()).map(User::getUsername).orElse(null);
    }

    private CommentUsernameProtocol convertProtocol(Comment comment) {
        return new CommentUsernameProtocol(comment, this.getUsernameByComment(comment));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment ->
            cupList.add(this.convertProtocol(comment))
        );
        return cupList;
    }

    @Override
    public CommentUsernameProtocol viewOneComment(Long id) {
        return this.convertProtocol(this.commentRepository.findById(id).orElse(null));
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment) {
        return this.convertProtocol(this.commentRepository.save(comment));
    }

    @Override
    public CommentUsernameProtocol updateComment(Long id, Comment comment) {
        Optional<Comment> found = commentRepository.findById(id);
        if (!found.isPresent()){
            return null;
        }
        Comment target = found.get();
        target.setContent(comment.getContent());
        target.setUserId(comment.getUserId());
        commentRepository.save(target);
        return this.convertProtocol(target);
    }

    @Override
    public boolean deleteComment(Long id) {
        Optional<Comment> found = commentRepository.findById(id);
        if (!found.isPresent()){
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }
}
