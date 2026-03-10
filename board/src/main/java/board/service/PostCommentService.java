package board.service;

import org.springframework.stereotype.Service;

import board.dto.PostCommentDto;
import board.entity.Post;
import board.entity.PostComment;
import board.entity.User;
import board.repository.PostCommentRepository;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostCommentService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;
	
	@Transactional
	public void registerPostComment(PostCommentDto postCommentDto) {
		
		// User
		User user = userRepository.getReferenceById(postCommentDto.getUserDto().getUid());
		
		// Post
		Post post = postRepository.getReferenceById(postCommentDto.getPid());
		
		// PostCommentDto -> PostComment
		PostComment postComment = postCommentDto.toEntity(post, user);
		
		// 댓글 저장
		postCommentRepository.save(postComment);
		
	}
	
	@Transactional
	public void deletePostComment(Long pcid,Long pid, String uid) {

//		postCommentRepository.deleteByIdAndUser_Uid(pcid,uid);
		postCommentRepository.deleteByIdAndPost_IdAndUser_Uid(pcid,pid,uid);
		

	}
	
}
