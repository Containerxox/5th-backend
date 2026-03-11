package board.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class PagingService {
	
	// 화면당 보여줄 페이지의 개수
	private final static int PAGE_LENGTH = 10;
	
	// 화면에서 start, end 페이지 정의
	public List<Integer> getPagingNumbers(int pageNumber, int totalPages) {
		
		// 현재 : 0 ~ 9, start : 0 , end*: start + page_length
		// 마지막 : ? ~ ?, start : ? , end*: ? + page_length
		
		// 현재 페이지 블록
		int currentBlock = (pageNumber-1) / PAGE_LENGTH; // 예시) 1~10 페이지는 0 페이지 블록에 해당
		int startPage = currentBlock * PAGE_LENGTH + 1;// 현재 페이지(pageNumber)는 12 -> currentBlock은 1블록 -> startPage는 1+10이니까 11으로 시작됨(previous옆에 11로 시작)
		int endPage = Math.min(startPage + PAGE_LENGTH, totalPages + 1); // startPage가 11이면, endPage=11+10 = 21 즉, 마지막 페이지값이 21이다.
		// totalPage와 endPage 값을 비교해서 작은 값을 선택해야함! // startPage가 21인데 totalPage가 21이야. 근데 endPage는 21+10이여서 31이되서 올바르지 않음.  
		// 즉.endPage(startPage+PAGE_LENTH)가  totalPage를 넘지 않도록 해 줘야 한다!!!
		
		return IntStream.range(startPage, endPage).boxed().toList();
	}
	
	public int getPageLength() {
		return PAGE_LENGTH;
	}
	
}