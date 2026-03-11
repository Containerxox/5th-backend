package board.dto.response;

import java.time.LocalDateTime;

import board.dto.PostDto;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private CategoryType categoryType;
    private LocalDateTime createdDate;
    private String createdBy;
    
	public static PostResponse of(Long id, String title, String content, CategoryType categoryType, LocalDateTime createdDate,
			String createdBy) {
		return new PostResponse(id, title, content, categoryType, createdDate, createdBy);
	}

	public static PostResponse from(PostDto postDto) {
		return new PostResponse(
							postDto.getId(),
							postDto.getTitle(),
							postDto.getContent(),
							postDto.getCategoryType(),
							postDto.getCreatedDate(),
							postDto.getCreatedBy()
		);
	}
    
}