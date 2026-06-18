package com.example.e_commerce_website.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.LoginRequestDTO;
import com.example.e_commerce_website.dto.RegisterUserRequestDTO;
import com.example.e_commerce_website.dto.UpdateUserDTO;
import com.example.e_commerce_website.dto.UserResponseDTO;
import com.example.e_commerce_website.entity.User;
import com.example.e_commerce_website.repository.UserRepository;
import com.example.e_commerce_website.security.JwtUtil;
import com.example.e_commerce_website.serviceInterface.UserService;
import com.example.e_commerce_website.util.SHA256Util;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;  

    //USER REGISTER
    @Override
    public ApiResponse registerUser(RegisterUserRequestDTO requestDTO) {

        // Check email already exists
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            return new ApiResponse(400, "Email already registered", null);
        }

        // Check phone already exists
        if (userRepository.existsByPhone(requestDTO.getPhone())) {
            return new ApiResponse(400, "Phone already registered", null);
        }

        // Create new user
        User user = new User();
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(SHA256Util.encode(requestDTO.getPassword()));
        user.setPhone(requestDTO.getPhone());
        user.setGender(requestDTO.getGender());
        user.setDateOfBirth(requestDTO.getDateOfBirth());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new ApiResponse(200, "User Registered Successfully", true);
    }
    
    //LOGIN USERS
    @Override
    public ApiResponse loginUser(LoginRequestDTO requestDTO) {

        Optional<User> optionalUser = userRepository.findByEmail( requestDTO.getEmail());

        if(optionalUser.isEmpty()) { 
        	return new ApiResponse( 400, "Invalid Email", null); 
        	}

        User user = optionalUser.get();

        boolean passwordMatch = SHA256Util.matches( requestDTO.getPassword(), user.getPassword());

        if(!passwordMatch) {
        	return new ApiResponse( 400, "Invalid Password", null);
        	}

        String accessToken = jwtUtil.generateAccessToken(user.getEmail(),"USER" );

        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail(),"USER");
        
        Map<String,Object> response = new HashMap<>();
        response.put( "accessToken", accessToken);
        response.put( "refreshToken", refreshToken);
        response.put( "user", user);

        return new ApiResponse( 200, "Login Successfully", response);
    }
    
    //GET USERS
    @Override
    public ApiResponse getAllUsers() {
    	
    	List<User> users = userRepository.findAll();
    	
    	if(users.isEmpty()) {
    		return new ApiResponse(404, "No Users Found", null);
    	}
    	
    	List<UserResponseDTO> responseList = users.stream()
    			.map(user -> new UserResponseDTO(
    					user.getId(),
    					user.getName(),
    					user.getEmail(),
    					user.getPhone(),
    					user.getGender(),
    					user.getDateOfBirth(),
    					user.getCreatedAt()
    					))
    			.collect(Collectors.toList());
    	
    	return new ApiResponse(200, "All Users Fetched Successfully", responseList);
    	
    }
    
    //UPDATE USER
    @Override
    public ApiResponse updateUserProfile(String email, UpdateUserDTO requestDTO) {
    	
    	Optional<User> optionalUser = userRepository.findByEmail(email);
    	
    	if(optionalUser.isEmpty()) {
    		return new ApiResponse(404, "User Not Found", null);
    	}
    	
    	User user = optionalUser.get();
    	
    	user.setName(requestDTO.getName());
    	user.setPhone(requestDTO.getPhone());
    	user.setGender(requestDTO.getGender());
    	user.setDateOfBirth(requestDTO.getDateOfBirth());
        user.setUpdatedAt(LocalDateTime.now());
        
        userRepository.save(user);
        
        UserResponseDTO response = new UserResponseDTO(
        		user.getId(),
        		user.getName(),
        		user.getEmail(),
        		user.getPhone(),
        		user.getGender(),
        		user.getDateOfBirth(),
        		user.getCreatedAt()
        		);
        return new ApiResponse(200, "User Update Successfully", response);
    }
    
    //UPDATE PASSWORD
    @Override
    public ApiResponse changePassword(ChangePasswordDTO requestDTO) {

        Optional<User> optionalUser =
                userRepository.findByEmail(requestDTO.getEmail());

        if (optionalUser.isEmpty()) {
            return new ApiResponse(404, "User Not Found", null);
        }

        User user = optionalUser.get();

        boolean isMatch = SHA256Util.matches(
                requestDTO.getOldPassword(),
                user.getPassword()
        );

        if (!isMatch) {
            return new ApiResponse(400, "Old Password Incorrect", null);
        }

        user.setPassword(
                SHA256Util.encode(requestDTO.getNewPassword())
        );

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new ApiResponse(200, "Password Changed Successfully", true);
    }
    
    //DELETE USER
    @Override
    public ApiResponse deleteUser(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return new ApiResponse(404, "User Not Found", null);
        }

        User user = optionalUser.get();

        userRepository.delete(user);

        return new ApiResponse(200, "User Deleted Successfully", true);
    }
    
    //get user by id
    @Override
    public ApiResponse getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            return new ApiResponse(404, "User Not Found", null);
        }

        User user = optionalUser.get();

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getCreatedAt()
        );

        return new ApiResponse(200, "User Found", response);
    }
}