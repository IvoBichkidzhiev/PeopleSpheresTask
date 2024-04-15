package people.spheres.demo2.services;

import people.spheres.demo2.dto.EmailDTO;

public interface EmailGeneratorService {
    EmailDTO generateEmail(String[] allParams, String expression);
}
