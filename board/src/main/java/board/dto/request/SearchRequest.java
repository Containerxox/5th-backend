package board.dto.request;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
	private String searchType;
	private String searchValue;
	
	// 검색 조건의 유효성 검증
	public boolean hasSearch() {
		return StringUtils.hasText(searchType) && StringUtils.hasText(searchValue) ;
	}
	
	
}
