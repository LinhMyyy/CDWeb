package com.cdweb.service.impl;

import com.cdweb.converter.RoleConverter;
import com.cdweb.converter.UserConverter;
import com.cdweb.dto.RoleDTO;
import com.cdweb.dto.UserDTO;
import com.cdweb.entity.ConfirmationToken;
import com.cdweb.entity.PasswordResetToken;
import com.cdweb.entity.RoleEntity;
import com.cdweb.entity.UserEntity;
import com.cdweb.repository.ConfirmationTokenRepository;
import com.cdweb.repository.PasswordResetTokenRepository;
import com.cdweb.repository.RoleRepository;
import com.cdweb.repository.UserRepository;
import com.cdweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;


    @Override
    public UserDTO findByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmailIgnoreCaseAndIsEnabled(email, true);
        if (userEntity != null) {
            return this.userConverter.toDTO(userEntity);
        }
        return null;
    }

    @Override
    public UserDTO findEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmailIgnoreCase(email);
        if (userEntity != null) {
            return this.userConverter.toDTO(userEntity);
        }
        return null;
    }

    public UserDTO sendMail(UserDTO user, HttpServletRequest request) {
        UserEntity userEntity = new UserEntity();
        UserEntity existingUser = userRepository.findByEmailIgnoreCaseAndIsEnabled(user.getEmail(), true);
        if (existingUser != null) {
            return null;
        } else {
            UserEntity temp = userRepository.findByEmailIgnoreCaseAndIsEnabled(user.getEmail(), false);
            if (temp != null) {
                userRepository.delete(temp);
            }
            user.setPassword(encoder.encode(user.getPassword()));
            List<RoleDTO> list = new ArrayList<>();
            RoleEntity role = roleRepository.findByName("ROLE_USER");
            list.add(roleConverter.toDTO(role));
            user.setRoleList(list);
            userEntity = userRepository.save(this.userConverter.toEntity(user));

            ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);

            confirmationTokenRepository.save(confirmationToken);


            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("bookstorenlu2021@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + baseUrl + "/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
            return userConverter.toDTO(userEntity);
        }
    }

    @Override
    public UserDTO confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            UserEntity user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            UserEntity userEntity = userRepository.save(user);
            List<ConfirmationToken> confirmationTokens = confirmationTokenRepository.findByUserId(token.getUser().getId());
            for (ConfirmationToken confirmToken : confirmationTokens) {
                confirmationTokenRepository.delete(confirmToken);
            }

            return userConverter.toDTO(userEntity);
        } else {
            return null;
        }
    }

    @Override
    public UserDTO sendMailForgetPassword(String email, HttpServletRequest request) {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        UserEntity user = this.userRepository.findByEmail(email);
        if (user == null) return null;
        PasswordResetToken passwordResetToken = new PasswordResetToken(user);
        this.passwordTokenRepository.save(passwordResetToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Forget Password!");
        mailMessage.setFrom("bookstorenlu2021@gmail.com");
        mailMessage.setText("To change your password, please click here : "
                + baseUrl + "/forget-password?token=" + passwordResetToken.getToken());
//                + "http://localhost:8080/forget-password?token=" + passwordResetToken.getToken());

        emailSenderService.sendEmail(mailMessage);
        return userConverter.toDTO(user);
    }

    @Override
    public UserDTO confirmEmailForgetPassword(String token) {
        PasswordResetToken passwordResetToken = this.passwordTokenRepository.findByToken(token);
        if (passwordResetToken != null) {
            return userConverter.toDTO(passwordResetToken.getUser());
        } else {
            return null;
        }
    }

    @Override
    public UserDTO changePassword(UserDTO user) {
        UserEntity userEntity = this.userRepository.findByEmailIgnoreCase(user.getEmail());


        if (user.getFullName() != null) {
            userEntity.setFullName(user.getFullName());
        }
        if (user.getPhone() != null) {
            userEntity.setPhone(user.getPhone());
        }
        if (user.getAddress() != null) {
            userEntity.setAddress(user.getAddress());
        }
        if (user.isEnabled()) {
            userEntity.setEnabled(true);
        }
        if (user.getPassword() != "" && user.getPassword() != null) {
            userEntity.setPassword(encoder.encode(user.getPassword()));
        }
        if (user.getRoleList() != null && user.getRoleList().size() != 0) {
            userEntity.setRoleList(userEntity.getRoleList());
        }
        userEntity = this.userRepository.save(userEntity);
        if (user.getPassword() != "" && user.getPassword() != null) {
            if (userEntity != null) {
                List<PasswordResetToken> passwordResetTokens = this.passwordTokenRepository.findByUser(userEntity);
                if (passwordResetTokens.size() > 0) {
                    for (PasswordResetToken passwordResetToken : passwordResetTokens) {
                        this.passwordTokenRepository.delete(passwordResetToken);
                    }

                }
            }
        }
        return this.userConverter.toDTO(userEntity);
    }

    @Override
    public boolean checkPass(String password, String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        return encoder.matches(password, userEntity.getPassword());
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> listUser = new ArrayList<>();
        List<UserEntity> users = this.userRepository.findAll();
        for (UserEntity user : users) {
            listUser.add(this.userConverter.toDTO(user));
        }
        return listUser;
    }

    @Override
    public UserDTO save(UserDTO user) {
        return this.userConverter.toDTO(this.userRepository.save(this.userConverter.toEntity(user)));
    }

    @Override
    public void delete(String email) {
        this.userRepository.delete(this.userRepository.findByEmail(email));
    }


}
