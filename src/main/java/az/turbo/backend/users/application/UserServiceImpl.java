package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserPasswordDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.application.exception.UserDuplicatedException;
import az.turbo.backend.users.application.exception.UserNotFoundException;
import az.turbo.backend.users.domain.UserRepository;
import az.turbo.backend.users.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> retrieveAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto retrieveById(long id) {
        return null;
    }

    @Override
    public UserPasswordDto retrieveByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found by email=" + email));

        return modelMapper.map(user, UserPasswordDto.class);
    }

    @Override
    public long create(UserCreateDto userCreateDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userCreateDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserDuplicatedException("User already exists!");
        }

        User user = modelMapper.map(userCreateDto, User.class);
        return userRepository.create(user);
    }

    //Jalal
    @Override
    public Set<Long> createAll(List<UserCreateDto> userCreateDtoList) {
        return null;
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) {
        User user = modelMapper.map(userUpdateDto, User.class);
        userRepository.update(user);
    }

    @Override
    public void updatePassword(long id, String password) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id=" + id));

        userRepository.updatePassword(id, password);
    }

    @Override
    public void updateRefreshToken(long id, String refreshToken) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        userRepository.updateRefreshToken(id, refreshToken);
    }

    @Override
    public void deleteById(long id, long deletedBy, LocalDateTime deletedDate) {
        userRepository.deleteById(id, deletedBy, deletedDate);
    }

    @Override
    public void deleteAll(Set<Long> ids, long deletedBy, LocalDateTime deletedDate) {
        userRepository.deleteAll(ids, deletedBy, deletedDate);
    }
}
