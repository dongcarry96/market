package com.example.productservice.cofig;//package com.dong.good.domain.cofig;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class ScheduleConfig {
//
//    private final QuantityServiceImpl quantityService;
//
//    public ScheduleConfig(QuantityServiceImpl quantityService) {
//        this.quantityService = quantityService;
//    }
//
//    @Scheduled(fixedRate = 6 * 60 * 60 * 1000)//6시간
//    public void scheduled() {
//        quantityService.updateQuantity();
//        log.info("Quantity updated");
//    }
//
//    @Transactional
//    // 재고 DB 저장 - schedule
//    public void updateQuantity() {
//        List<Quantity> quantityList = quantityRepository.findAll();
//        for (Quantity quantity : quantityList) {
//            quantity.updateQuantity(redisService.getQuantity(quantity.getQuantityId()));
//        }
//    }
//
//}
