package com.sipgate.sipvalidator.controller;

import com.sipgate.sipvalidator.model.ValidationRequest;
import com.sipgate.sipvalidator.model.ValidationResponse;
import com.sipgate.sipvalidator.service.SipValidatorService;
import com.sipgate.sipvalidator.service.SipValidatorService.ValidationResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sip")
public class SipValidatorRestController {

    private final SipValidatorService validatorService;

    public SipValidatorRestController(SipValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @PostMapping("/validate/message")
    public ResponseEntity<ValidationResponse> validateMessage(
            @RequestBody ValidationRequest request) {
        
        ValidationResponse response = new ValidationResponse();
        
        if (request.getMessageText() != null && !request.getMessageText().isEmpty()) {
            ValidationResult result = validatorService.validateMessage(request.getMessageText());

            response.setValid(result.isValid());
            response.setMessages(result.getMessages());
            response.setErrors(result.getErrors());
        } else {
            response.setValid(false);
            response.getErrors().add("Request must include messageText");
        }
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate/header")
    public ResponseEntity<ValidationResponse> validateHeader(
            @RequestBody ValidationRequest request) {
        
        ValidationResponse response = new ValidationResponse();
        
        if (request.getHeaderText() != null && !request.getHeaderText().isEmpty()) {
            ValidationResult result = validatorService.validateHeader(request.getHeaderText());

            response.setValid(result.isValid());
            response.setMessages(result.getMessages());
            response.setErrors(result.getErrors());
        } else {
            response.setValid(false);
            response.getErrors().add("Request must include either headerText");
        }
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("SIP Header Validator is running");
    }
}