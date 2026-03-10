package board.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import board.dto.PostCommentDto;
import board.dto.PostWithCommentsDto;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
public class PostWithCommentsResponse {
	private Long id;
	private String title;
    private String content;
    private CategoryType categoryType;
    private String uid;
    private LocalDateTime createdDate;
    private String createdBy;
    private Set<PostCommentReponse> postCommentReponse;
    
    public static PostWithCommentsResponse of(Long id, String title, String content, CategoryType categoryType, String uid, LocalDateTime createdDate, String createdBy, Set<PostCommentReponse> postCommentReponse) {
    	return new PostWithCommentsResponse(id, title, content, categoryType, uid, createdDate, createdBy, postCommentReponse);
    }
    
    public static PostWithCommentsResponse from(PostWithCommentsDto postWithCommentsDto) {
    	
    	return new PostWithCommentsResponse(postWithCommentsDto.getId(),
    										postWithCommentsDto.getTitle(),
    										postWithCommentsDto.getContent(),
    										postWithCommentsDto.getCategoryType(),
    										postWithCommentsDto.getUserDto().getUid(),
    										postWithCommentsDto.getCreatedDate(),
    										postWithCommentsDto.getCreatedBy(),
    										getPostCommentReponses(postWithCommentsDto.getPostCommentDtos())
    										
		);
    }

   private static Set<PostCommentReponse> getPostCommentReponses(Set<PostCommentDto> postCommentDtos) {
	   // PostCommentDto 반복 -> Response 변경 -> 중복 제거??
			   									
	   // id값으로 중복을 제거 -> return
	   return null;
   }
    
}

