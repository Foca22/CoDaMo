package com.codamo.codamo.config;

import com.codamo.codamo.dto.auth.response.AuthorizationResponse;
import com.codamo.codamo.model.auth.Account;
import com.codamo.codamo.model.auth.RefreshToken;
import com.codamo.codamo.model.Course;
import com.codamo.codamo.repo.account.AccountRepo;
import com.codamo.codamo.repo.account.RefreshTokenRepo;
import com.codamo.codamo.repo.CourseRepo;
import com.codamo.codamo.security.AuthoritiesConstants;
import com.codamo.codamo.service.internal.AccountInternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to generate default content like admin and user accounts on local environment.
 * The access and refresh tokens are logged in console and can be used during testing.
 */
@Configuration
public class TablePopulator {

    private static final Logger logger
            = LoggerFactory.getLogger(TablePopulator.class);

    @Autowired
    Environment env;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountInternalService accountInternalService;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    CourseRepo courseRepo;

    @PostConstruct
    public void populateTable() {
        if (Arrays.asList(env.getActiveProfiles()).contains("local")) {
            logger.info("************************************************");
            logger.info("************************************************");
            logger.info("Populating Table");
            logger.info("************************************************");
            logger.info("************************************************");
            buildAdmin();
            logger.info("************************************************");
            logger.info("************************************************");
            buildUser();
            logger.info("************************************************");
            addCourses();
            logger.info("Added projects");
            logger.info("************************************************");
        }

    }

    @Transactional
    private void buildAdmin() {
        Account account = Account.builder()
                .authorities(List.of(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN))
                .email("admin@admin.com")
                .firstName("admin")
                .lastName("admin")
                .fullName("admin admin")
                .password(accountInternalService.getPasswordEncoder().encode("admin"))
                .build();

        accountRepo.save(account);
        addRefreshTokenToAccount(account);
        accountRepo.save(account);

        AuthorizationResponse authorizationResponse = accountInternalService.buildTokens(account);

        logger.info("Admin Credentials");
        logger.info("************************************************");
        logger.info("Email: admin@admin.com");
        logger.info("Password: admin");
        logger.info("Access Token: ");
        logger.info(authorizationResponse.getAccessToken());
        logger.info("Refresh Token: ");
        logger.info(authorizationResponse.getRefreshToken());
    }

    @Transactional
    private void buildUser() {
        Account account = Account.builder()
                .authorities(List.of(AuthoritiesConstants.USER))
                .email("user@user.com")
                .firstName("user")
                .lastName("user")
                .fullName("user user")
                .password(accountInternalService.getPasswordEncoder().encode("user"))
                .build();

        accountRepo.save(account);
        addRefreshTokenToAccount(account);
        accountRepo.save(account);

        AuthorizationResponse authorizationResponse = accountInternalService.buildTokens(account);

        logger.info("User Credentials");
        logger.info("************************************************");
        logger.info("Email: user@user.com");
        logger.info("Password: user");
        logger.info("Access Token: ");
        logger.info(authorizationResponse.getAccessToken());
        logger.info("Refresh Token: ");
        logger.info(authorizationResponse.getRefreshToken());
    }

    private void addRefreshTokenToAccount(Account account) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(account);
        refreshToken = refreshTokenRepo.save(refreshToken);
        account.setRefreshToken(refreshToken);
    }

    private void addCourses() {
        Course grenadeCourse = new Course();
        grenadeCourse.setTitle("Grenade");
        grenadeCourse.setDescription("Curs despre grenade");

        Course savedCourse = courseRepo.save(grenadeCourse);
        logger.info("Created course " + savedCourse.getTitle() + " with id " + savedCourse.getId());
    }
}
