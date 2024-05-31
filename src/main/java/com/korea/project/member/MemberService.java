package com.korea.project.member;

import com.korea.project.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

    public void save(String loginId, String password, String email) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        member.setCreateDate(LocalDateTime.now());
        memberRepository.save(member);
    }

    public Member getMember(String name) {
        Optional<Member> member = this.memberRepository.findByLoginId(name);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }


    public String temp_save(MultipartFile file) {
        if (!file.isEmpty())
            try {
                String path = resourceLoader.getResource("classpath:/static").getFile().getPath();
                File fileFolder = new File( path + "/image");
                if (!fileFolder.exists())
                    fileFolder.mkdirs();
                String filePath = "/image/" + UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
                file.transferTo(Paths.get(path + filePath));
                return filePath;
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        return null;
    }

    public void save(Member member, String url) {
        try {
            String path = resourceLoader.getResource("classpath:/static").getFile().getPath();
            if(member.getProfile_image()!=null) {
                File oldFile = new File(path+member.getProfile_image());
                if(oldFile.exists())
                    oldFile.delete();
            }
            member.setProfile_image(url);
            memberRepository.save(member);
        } catch (IOException ignored) {

        }

    }
}
