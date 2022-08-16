package com.cdweb.service;

import com.cdweb.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    public UserDTO findByEmail(String userName);

    public UserDTO sendMail(UserDTO user, HttpServletRequest request);

    public UserDTO confirmEmail(String confirmationToken);

    public UserDTO sendMailForgetPassword(String email, HttpServletRequest request);

    public UserDTO confirmEmailForgetPassword(String token);

    UserDTO changePassword(UserDTO user);

    boolean checkPass(String password, String email);

    List<UserDTO> findAll();

    UserDTO save(UserDTO user);

    void delete(String email);

    UserDTO findEmail(String email);
}
