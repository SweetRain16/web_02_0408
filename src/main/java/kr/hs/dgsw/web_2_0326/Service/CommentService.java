package kr.hs.dgsw.web_2_0326.Service;

import kr.hs.dgsw.web_2_0326.Domain.Comment;
import kr.hs.dgsw.web_2_0326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();
    CommentUsernameProtocol viewOneComment(Long id);
    CommentUsernameProtocol addComment(Comment comment);
    CommentUsernameProtocol updateComment(Long id, Comment comment);
    boolean deleteComment(Long id);
}
