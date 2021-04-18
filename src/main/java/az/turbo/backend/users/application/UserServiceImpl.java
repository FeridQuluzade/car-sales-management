package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.application.exception.UserDuplicatedException;
import az.turbo.backend.users.application.exception.UserNotFoundException;
import az.turbo.backend.users.domain.UserRepository;
import az.turbo.backend.users.domain.model.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl() {
        userRepository = new UserRepository();
        modelMapper = new ModelMapper();
    }

    @Override
    public List<UserDto> retrieveAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    //Jalal
    @Override
    public UserDto retrieveById(long id) {
        return null;
    }

    @Override
    public UserDto retrieveByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found by email=" + email));

        return modelMapper.map(user, UserDto.class);
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
    public void deleteById(long id, long deletedBy, LocalDateTime deletedDate) {
        userRepository.deleteById(id, deletedBy, deletedDate);
    }

    @Override
    public void deleteAll(Set<Long> ids, long deletedBy, LocalDateTime deletedDate) {
        userRepository.deleteAll(ids, deletedBy, deletedDate);
    }
}
