package board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.dto.request.AiRequest;
import board.service.AiPostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/ai")
@RestController
public class AiPostController {

	private final AiPostService aiPostService;

	// POST /api/ai/category
	@PostMapping("/category")
	public ResponseEntity<String> recommendCategory(@RequestBody AiRequest request) {

		String category = aiPostService.recommendCategory(
											request.getTitle(), 
											request.getContent());
			
		return ResponseEntity.ok(category);
	}

	// POST /api/ai/summary
	@PostMapping("/summary")
	public ResponseEntity<String> summarize(@RequestBody AiRequest request) {
		return ResponseEntity.ok(null);
	}

	// POST /api/ai/correct
	@PostMapping("/correct")
	public ResponseEntity<String> correct(@RequestBody AiRequest request) {
		return ResponseEntity.ok(null);
	}
}