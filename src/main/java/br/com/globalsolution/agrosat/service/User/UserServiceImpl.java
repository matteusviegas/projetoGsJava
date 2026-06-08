package br.com.globalsolution.agrosat.service.User;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
    }

    @Override
    @Cacheable(value = "users-by-email", key = "#email")
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "users-by-email", allEntries = true)
    })
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
    @Caching(put = {
            @CachePut(value = "users", key = "#id"),
            @CachePut(value = "users-by-email", key = "#result.email")
    }, evict = {
            @CacheEvict(value = "users-by-email", allEntries = true)
    })
    public User updateById(Long id, String actualPassword, User o) {
        User existing = findById(id);

        if (!existing.getEmail().equals(o.getEmail()) &&
                userRepository.existsByEmail(o.getEmail())) {

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
        existing.setPassword(passwordEncoder.encode(o.getPassword()));
        existing.setPhone(o.getPhone());

        return userRepository.save(existing);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "users-by-email", allEntries = true)
    })
    public void removeById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

}