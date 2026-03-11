package board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import board.dto.UserDto;
import board.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public Optional<UserDto> getUser(String username) { 
		
		return userRepository.findById(username)
								.map(UserDto::from);
	}

}
