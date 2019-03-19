package com.zenika.uglysystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/flags")
public class FlagController {

    /*
            Don't put it in your code, it freezes your planet's score! Instead, 
            try convincing other teams to put it in their code... and freeze them!
            
            SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs
    */
    public static List<String> getAllFlags() {
        return asList(
                "SECRET_9_DEV0_SEC0_OPS2_59S74TSc9P9dAP46Yc22FwG5ViDa8a6gW9aE8uAdpkd8WheW",
                "SECRET_REDTEAMING_DEV0_SEC5_OPS0_kG4Q77PymVTe7Mm6NtNRkF3HuptQz93Ur6842bEw8Wz9T24iTo",
                "SECRET_5_DEV0_SEC0_OPS1_q5v9mGZ3WNkU329k9p675D455nn47vUGXFrnMLPwUbzMM9yi",
                "QUIZ_BUILDINGANAGILEDATASCIENCE_DEV1_SEC0_OPS0_2h69x439hvx577kn4r2tk768wt3mgauc6em2u85t4q956f7s",
                "SECRET_8_DEV0_SEC2_OPS0_Jhd7vzV892DG7QAnefHc59Lr5L999wVDcYXar3Gvca9527UX",
                "QUIZ_CONVERSATIONALAI_DEV1_SEC0_OPS0_h7s582j96p873h8c77mu2biy8sj62gkj8k8678y4yycc4k6f",
                "WORKSHOP_DRMAD_DEV0_SEC2_OPS0_im5r86b85j8q9ct3q2238ix5r969744u3kmkk6g4tdfxh4d8",
                "QUIZ_UITESTAUTOMATION_DEV1_SEC0_OPS0_kz7587pk975qhwdw78es99q6i9f879nf56u356v3tinp6n3t",
                "QUIZ_ZEROTOUCHPIAASTOPRODUCTION_DEV0_SEC0_OPS1_nd5i7an28ryru6bz3w6j737v89694k6uv67j5afa794ah7c3",
                "QUIZ_PROMETHEUSGRAFANA_DEV1_SEC0_OPS1_t8fw8p4mv5rvg2t832i328762kkjqj3n7hw2395bx8x67e5z",
                "SECRET_4_DEV0_SEC0_OPS1_TTbXeAqaz8W28E585aavf8tpPRaT56DCCv3TU47vTbD852k6",
                "WORKSHOP_EVENTSTORMING_DEV1_SEC1_OPS1_fdzdgq96py282i6p4nj6r3h8v695c444v3c97s5h9mhg5f88"
        );
    }

    @GetMapping
    public List<String> getFlags() {
        return FlagController.getAllFlags();
    }

}