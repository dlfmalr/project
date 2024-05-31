package com.korea.project.company;

import com.korea.project.DataNotFoundException;
import com.korea.project.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ResourceLoader resourceLoader;

    public void save(String name,String url) {
        Company company = new Company();
        company.setName(name);
        company.setUrl(url);
        company.setCreateDate(LocalDateTime.now());

        companyRepository.save(company);
    }

    public Company getCompany(Long id) {
        Optional<Company> company = this.companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new DataNotFoundException("company not found");
        }

    }

    public List<Company> getCompanyList() {
        return companyRepository.findAll();
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

    public List<Company> getSearchList(String name) {
        return companyRepository.findByNameContaining(name);
    }
}
