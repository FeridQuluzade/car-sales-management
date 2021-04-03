package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.UserRepository;
import az.turbo.backend.users.domain.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
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
//        return userRepository
//                .findAll()
//                .stream()
//                .map(user -> {
//                    UserDto userDto = new UserDto();
//                    userDto.setId(user.getId());
//                    userDto.setFirstName(user.getFirstName());
//                    userDto.setLastName(user.getLastName());
//                    userDto.setGender(user.getGender());
//                    userDto.setEmail(user.getEmail());
//                    return userDto;
//                })
//                .collect(Collectors.toList());
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long create(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        return userRepository.create(user);
    }

    @Override
    public long update(UserUpdateDto userUpdateDto) {
        User userNEW = modelMapper.map(userUpdateDto, User.class);
        return userRepository.update(userNEW);
    }

    @Override
    public List<UserDto> retrieveByEmail(String email) {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .filter(x->x.getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
