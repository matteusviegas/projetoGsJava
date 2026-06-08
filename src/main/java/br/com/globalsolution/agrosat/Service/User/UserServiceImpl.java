package br.com.globalsolution.agrosat.Service.User;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.domainmodel.User;
import br.com.globalsolution.agrosat.domainmodel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
    }

    @Override
    public User create(User o) {
        if (userRepository.existsByEmail(o.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email já cadastrado");
        }

        o.setPassword(passwordEncoder.encode(o.getPassword()));
        return userRepository.save(o);
    }

    @Override
    public User updateById(Long id, String actualPassword, User o) {
        User existing = findById(id);

        if (!existing.getEmail().equals(o.getEmail()) &&
                (userRepository.existsByEmail(o.getEmail()))) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email já cadastrado");
        }

        if (!passwordEncoder.matches(
                actualPassword, existing.getPassword())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Senha atual inválida");
        }

        existing.setName(o.getName());
        existing.setEmail(o.getEmail());
        existing.setPassword(o.getPassword());
        existing.setPhone(o.getPhone());

        return userRepository.save(existing);
    }

    @Override
    public void removeById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

}
