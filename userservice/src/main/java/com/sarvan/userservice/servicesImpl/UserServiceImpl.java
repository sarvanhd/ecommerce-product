package com.sarvan.userservice.servicesImpl;

import com.sarvan.userservice.dao.UsersRepository;
import com.sarvan.userservice.entities.Address;
import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.exception.UserNotFoundException;
import com.sarvan.userservice.model.CreateUserRequest;
import com.sarvan.userservice.model.UpdateUserRequest;
import com.sarvan.userservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void createUser(CreateUserRequest userReq) {
        Users newUser = new Users();
        newUser.setEmail(userReq.getEmail());
        newUser.setFirstName(userReq.getFirstName());
        newUser.setLastName(userReq.getLastName());
        newUser.setPassword(userReq.getPassword());
        newUser.setDob(userReq.getDob());
        newUser.setAddress(new Address());
        if(userReq.getAddress() != null) {
            Address reqAddress = userReq.getAddress();
            Address userAddress = newUser.getAddress();
            userAddress.setCity(reqAddress.getCity());
            userAddress.setState(reqAddress.getState());
            userAddress.setCountry(reqAddress.getCountry());
            userAddress.setPostalCode(reqAddress.getPostalCode());
            userAddress.setStreet(reqAddress.getStreet());
            userAddress.setUser(newUser);
        }
        usersRepository.save(newUser);
    }

    @Override
    @Cacheable("users")
    public List<Users> getUsersList() {
        return usersRepository.findAll();
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public Users getUser(Long id) throws Exception{
        log.info("Get user...");
        Optional<Users> user = usersRepository.findById(id);
        return user.orElseThrow(() ->new UserNotFoundException("User not found."));
    }

    @Override
    @CachePut(value = "user", key = "#id")
    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(Long id) {
        log.info("Delete user...");
        usersRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    @CacheEvict(value = "users", allEntries = true)
    public Users updateUser(Long id, UpdateUserRequest userReq) throws Exception {
        Users user = usersRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User not found."));
        if(user != null) {
            user.setFirstName(userReq.getFirstName());
            user.setLastName(userReq.getLastName());
            user.setPassword(userReq.getPassword());
            user.setEmail(userReq.getEmail());
            if(user.getAddress() == null){
                Address newAddr = new Address();
                newAddr.setUser(user);
                user.setAddress(newAddr);
            }
            if(userReq.getAddress() != null) {
                Address reqAddress = userReq.getAddress();
                Address userAddress = user.getAddress();
                userAddress.setCity(reqAddress.getCity());
                userAddress.setState(reqAddress.getState());
                userAddress.setCountry(reqAddress.getCountry());
                userAddress.setPostalCode(reqAddress.getPostalCode());
                userAddress.setStreet(reqAddress.getStreet());
            }
        }
        usersRepository.save(user);
        return user;
    }
}
