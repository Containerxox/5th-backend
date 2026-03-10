package board.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import board.entity.Post;
import board.entity.User;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class PostWithCommentsDto {
    private Long id;
    private String title;
    private String content;
    private CategoryType categoryType;
    private Set<PostCommentDto> postCommentDtos;
    private UserDto userDto;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;
    
	public static PostWithCommentsDto of(Long id, String title, String content, CategoryType categoryType,
			Set<PostCommentDto> postCommentDtos, UserDto userDto, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		return new PostWithCommentsDto(id, title, content, categoryType, postCommentDtos, userDto, createdDate, createdBy, modifiedDate, modifiedBy);
	}

	public static PostWithCommentsDto from(Post post) {
		return new PostWithCommentsDto(
							post.getId(),
							post.getTitle(),
							post.getContent(),
							post.getCategoryType(),
							post.getPostComments().stream()
													.map(PostCommentDto::from)
													.collect(Collectors.toCollection(LinkedHashSet::new)),
							UserDto.from(post.getUser()),
							post.getCreatedDate(),
							post.getCreatedBy(),
							post.getModifiedDate(),
							post.getModifiedBy()
		);
	}
    
    public Post toEntity(User user) {
    	return Post.of(
    					title, 
    					content, 
    					categoryType, 
    					user
		);
    }
    
}