package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.UserRepository;
import az.turbo.backend.users.domain.model.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public UserDto retrieveByEmail(String email) {
        User user =  userRepository.findByEmail(email);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public long create(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        return userRepository.create(user);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) {
        User user = modelMapper.map(userUpdateDto, User.class);
        userRepository.update(user);
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
