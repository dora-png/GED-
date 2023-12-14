package com.microservice.ged.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.repository.ProfilesRepo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ProfilRepoText {
	@Autowired
	private ProfilesRepo profilesRepo;
	
	@BeforeEach
    void initUseCase() {
        List<Profiles> customers = Arrays.asList(
                new Profiles("default", "user",TypeUser.INTERN_ACTOR,true)
        );
        profilesRepo.saveAll(customers);
    }
	
	@Test
	void toto() {
		System.err.println("hjklkjhgvfdsfghjkl");
	}

}
