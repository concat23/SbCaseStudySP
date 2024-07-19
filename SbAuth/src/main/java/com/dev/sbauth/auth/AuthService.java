package com.dev.sbauth.auth;

import com.dev.sbauth.entity.Member;
import com.dev.sbauth.member.MemberRequestDto;
import com.dev.sbauth.member.MemberResponseDto;
import com.dev.sbauth.jwt.TokenProvider;
import com.dev.sbauth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if (memRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("This user is already registered.");
        }

        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

}

