package chukhlantsev.oleg;

import chukhlantsev.oleg.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId)
    {
        log.info("fraud checking by id {}", customerId);
       Boolean isFraudster = fraudCheckService.isFraudster(customerId);

       return new FraudCheckResponse(isFraudster);

    }


}
