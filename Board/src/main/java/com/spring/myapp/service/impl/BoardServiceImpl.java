package com.spring.myapp.service.impl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;
 
import com.spring.myapp.dao.BoardDao;
import com.spring.myapp.domain.Board;
import com.spring.myapp.domain.BoardReply;
import com.spring.myapp.service.BoardService;
 
@Service("boardService")
public class BoardServiceImpl implements BoardService {
 
    @Resource(name="boardDao")
    private BoardDao boardDao;
 
    @Override
    public int regContent(Map<String, Object> paramMap) {
        //아이디가 없으면 입력
        if(paramMap.get("id")==null) {
            return boardDao.regContent(paramMap);
        }else {//아이디가 있으면 수정
            return boardDao.modifyContent(paramMap);
        }
    }
 
    @Override
    public int getContentCnt(Map<String, Object> paramMap) {
        return boardDao.getContentCnt(paramMap);
    }
 
    @Override
    public List<Board> getContentList(Map<String, Object> paramMap) {
        return boardDao.getContentList(paramMap);
    }
 
    @Override
    public Board getContentView(Map<String, Object> paramMap) {
        return boardDao.getContentView(paramMap);
    }
 
    @Override
    public int regReply(Map<String, Object> paramMap) {
        return boardDao.regReply(paramMap);
    }
 
    @Override
    public List<BoardReply> getReplyList(Map<String, Object> paramMap) {
 
        List<BoardReply> boardReplyList = boardDao.getReplyList(paramMap);
 
        //부모
        List<BoardReply> boardReplyListParent = new ArrayList<BoardReply>();
        //자식
        List<BoardReply> boardReplyListChild = new ArrayList<BoardReply>();
        //전체
        List<BoardReply> newBoardReplyList = new ArrayList<BoardReply>();
 
        //1.부모와 자식 분리
        for(BoardReply boardReply: boardReplyList){
            if(boardReply.getDepth().equals("0")){
                boardReplyListParent.add(boardReply);
            }else{
                boardReplyListChild.add(boardReply);
            }
        }
 
        //부모 for 문 돌리기
        for(BoardReply boardReplyParent: boardReplyListParent){
        	//부모는 무조건 넣기
            newBoardReplyList.add(boardReplyParent);
            //자식 for 문 돌리기
            for(BoardReply boardReplyChild: boardReplyListChild){
                //부모의 자식들만 넣기
                if(boardReplyParent.getReply_id().equals(boardReplyChild.getParent_id())){
                    newBoardReplyList.add(boardReplyChild);
                }
 
            }
 
        }
 
        //list return
        return newBoardReplyList;
    }
 
    @Override
    public int delReply(Map<String, Object> paramMap) {
        return boardDao.delReply(paramMap);
    }
 
    @Override
    public int getBoardCheck(Map<String, Object> paramMap) {
        return boardDao.getBoardCheck(paramMap);
    }
 
    @Override
    public int delBoard(Map<String, Object> paramMap) {
        return boardDao.delBoard(paramMap);
    }
 
    @Override
    public boolean checkReply(Map<String, Object> paramMap) {
        return boardDao.checkReply(paramMap);
    }
 
    @Override
    public boolean updateReply(Map<String, Object> paramMap) {
        return boardDao.updateReply(paramMap);
    }
 
}
 
