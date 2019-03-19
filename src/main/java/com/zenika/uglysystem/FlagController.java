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
                "QUIZ_CONVERSATIONALAI_DEV1_SEC0_OPS0_h7s582j96p873h8c77mu2biy8sj62gkj8k8678y4yycc4k6f"
        );
    }

    @GetMapping
    public List<String> getFlags() {
        return FlagController.getAllFlags();
    }

}