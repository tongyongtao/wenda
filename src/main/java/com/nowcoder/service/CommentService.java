package com.nowcoder.service;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    SensitiveService sensitiveService;

    public List<Comment> getCommentsByEntity(int entityId,int entityType){
        return commentDAO.selectByEntity(entityId,entityType);
    }

    public int getCommentCount(int entityId,int entityType){
        return commentDAO.getCommentCount(entityId,entityType);
    }

    public int addComment(Comment comment){
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveService.filter(comment.getContent()));
        return commentDAO.addComment(comment);
    }

    public int deleteComment(int entityId,int entityType){
        return commentDAO.updateStatus(entityId,entityType,1);
    }

    public Comment selectById(int id){
        return commentDAO.selectById(id);
    }

    public int getUserCommentCount(int userId){
        return commentDAO.getUserCommentCount(userId);
    }
}
