package com.alerthub.demo.information;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;

    }

    private PageRequest pageRequest(Integer page) {
        return PageRequest.of(page, 80, Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    public List<Information> getInformation(Integer page) {
        PageRequest pageable = pageRequest(page);
        return informationRepository.findAll(pageable).getContent();
    }

    public void createInformation(Information information) {
        if (information == null) {
            throw new IllegalArgumentException("Information cannot be null");
        }
        information.setId(null);
        informationRepository.save(information);
    }

    public void editInformation(String id, Information updatedInformation) {
        if (id == null || updatedInformation == null) {
            throw new IllegalArgumentException("Information ID, and Information must not be null");
        }

        Optional<Information> optionalInformation = informationRepository.findById(id);
        if (optionalInformation.isPresent()) {
            Information existingInformation = optionalInformation.get();

            updatedInformation.setId(existingInformation.getId());

            informationRepository.save(updatedInformation);

        } else {
            throw new IllegalStateException("Information not found");
        }
    }

    public Information getInformationDetails(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Information ID must not be null");
        }
        Optional<Information> optionalInformation = informationRepository.findById(id);
        if (optionalInformation.isPresent()) {
            Information information = optionalInformation.get();
            return information;
        } else {
            throw new IllegalStateException("Information not found");
        }
    }

    public void deleteInformation(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Information ID must not be null");
        }

        Optional<Information> optionalInformation = informationRepository.findById(id);

        if (optionalInformation.isPresent()) {
            informationRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Information not found");
        }
    }

}
