package com.shoshore.churchback.config;

import com.shoshore.churchback.repository.CellGroupRepository;
import com.shoshore.churchback.repository.ChurchMemberRepository;
import com.shoshore.churchback.repository.UserRepository;
import com.shoshore.churchback.services.cellGroup.CellGroupService;
import com.shoshore.churchback.services.cellGroup.CellGroupServiceImpl;
import com.shoshore.churchback.services.churchMember.ChurchMemberService;
import com.shoshore.churchback.services.churchMember.ChurchMemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Configuration
@EnableAsync
public class BeanConfig {
    @Bean
    public CellGroupService cellGroupService(CellGroupRepository cellGroupRepository) {
        return new CellGroupServiceImpl(cellGroupRepository);
    }

    @Bean
    ChurchMemberService churchMemberService(ChurchMemberRepository churchMemberRepository,
                                            CellGroupRepository cellGroupRepository,
                                            PasswordEncoder passwordEncoder,
                                            UserRepository userRepository) {
        return new ChurchMemberServiceImpl(churchMemberRepository, cellGroupRepository, passwordEncoder, userRepository);
    }
}
