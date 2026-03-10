package board.dto.response;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import board.dto.PostCommentDto;
import board.dto.PostWithCommentsDto;
import board.entity.constant.CategoryType;
import board.dto.response.PostCommentResponse; 
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
    private Set<PostCommentResponse> postCommentResponse;
    
    public static PostWithCommentsResponse of(Long id, String title, String content, CategoryType categoryType, String uid, LocalDateTime createdDate, String createdBy, Set<PostCommentResponse> postCommentReponse) {
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

   private static Set<PostCommentResponse> getPostCommentReponses(Set<PostCommentDto> postCommentDtos) {
	   // PostCommentDto 반복 -> Response 변경 -> 중복 제거??
	   Map<Long, PostCommentResponse> map = postCommentDtos.stream()
	   													.map(PostCommentResponse::from)
	   													.collect(Collectors.toMap(PostCommentResponse::getId, Function.identity()));
	   
	   
	   
	   // (대댓글이 달리는 경우 중복을 제거하기 위해) id값으로 중복을 제거 -> (날짜)내림차순 정렬 [CreatedDate DESC 정렬] -> return
	   // 제일 최근에 작성된 댓글이 위에 올라오게 정렬됨
	   return map.values().stream()
			   				.collect(Collectors.toCollection(() -> new TreeSet<>(
			   							Comparator.comparing(PostCommentResponse::getCreatedDate)
			   										.reversed()
			   										.thenComparing(PostCommentResponse::getId)
			   						)));
	   
   }
    
}

