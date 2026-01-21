package com.sipgate.sipvalidator.service;

import org.springframework.stereotype.Service;

import com.sipgate.sipvalidator.model.HeaderInfo;
import com.sipgate.sipvalidator.service.SipHeaderRfcMapping.RfcReference;

import gov.nist.javax.sip.message.MessageFactoryImpl;
import javax.sip.message.MessageFactory;
import javax.sip.message.Message;
import javax.sip.message.Request;
import javax.sip.message.Response;

import gov.nist.javax.sip.header.HeaderFactoryImpl;
import javax.sip.header.HeaderFactory;
import javax.sip.header.Header;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Service
public class SipValidatorService {

    private final MessageFactory messageFactory;
    private final HeaderFactory headerFactory;

    public SipValidatorService() {
        this.messageFactory = new MessageFactoryImpl();
        this.headerFactory = new HeaderFactoryImpl();
    }

    /** 
     * Validates a complete SIP message
     * @param messageText The complete message text (e.g., "INVITE sip:...")
     * @return ValidationResult with success/failure status and messages
     */
    public ValidationResult validateMessage(String messageText) {
        ValidationResult result = new ValidationResult();
        
        try {
             // Make sure CRLF is used as line separator and the message is terminated with CRLF as per SIP spec
            messageText = messageText.replaceAll("\\r?\\n", "\r\n");
            if (!messageText.endsWith("\r\n")) {
                messageText = messageText + "\r\n";
            }          

            // If the first line starts with "SIP/2.0", it's a response. Otherwise, it's a request.
            String firstLine = messageText.split("\\r\\n", 2)[0].trim();  

            Object sipMessage;
            if ( !firstLine.startsWith("SIP/2.0") ) {
                sipMessage = ((MessageFactoryImpl)messageFactory).createRequest(messageText);
            } else {
                sipMessage = ((MessageFactoryImpl)messageFactory).createResponse(messageText);
            }

            // If we get here, message passed basic parsing
            result.setValid(true);
            result.addMessage("Message successfully parsed");
            
            // Add message type information
            result.addMessage("Message type: " + sipMessage.getClass().getSimpleName());
            
            // Extract headers from the SIP message and add RFC references
            if (sipMessage instanceof Request) {
                Request request = (Request) sipMessage;
                extractHeadersFromMessage(request, result);
            } else if (sipMessage instanceof Response) {
                Response response = (Response) sipMessage;
                extractHeadersFromMessage(response, result);
            }
            
            return result;
        } catch (ParseException e) {
            return result.addError("Parse error: " + e.getMessage());
        } catch (Exception e) {
            return result.addError("Validation error: " + e.getMessage());
        }
    }
    
    /**
     * Extract headers from a SIP message and add RFC information
     */
    @SuppressWarnings("unchecked")
    private void extractHeadersFromMessage(Object sipMessage, ValidationResult result) {
        try {
            // Get all header names from the message
            Iterator<String> headerNames = null;
            if (sipMessage instanceof Request) {
                headerNames = ((Request) sipMessage).getHeaderNames();
            } else if (sipMessage instanceof Response) {
                headerNames = ((Response) sipMessage).getHeaderNames();
            }
            
            if (headerNames != null) {
                while (headerNames.hasNext()) {
                    String headerName = headerNames.next();
                    RfcReference rfcRef = SipHeaderRfcMapping.getRfcReference(headerName);
                    
                    HeaderInfo headerInfo = new HeaderInfo(
                        headerName,
                        headerName,
                        "SIP Header",
                        rfcRef
                    );
                    result.addHeader(headerInfo);
                }
                
                result.addMessage("Found " + result.getHeaders().size() + " headers in the message");
            }
        } catch (Exception e) {
            result.addMessage("Could not extract headers: " + e.getMessage());
        }
    }

    /**
     * Validates a SIP header string
     * @param headerText The complete header text (e.g., "Call-ID: a84b4c76e66710")
     * @return ValidationResult with success/failure status and messages
     */
    public ValidationResult validateHeader(String headerText) {
        ValidationResult result = new ValidationResult();
        
        try {
            Header header = ((HeaderFactoryImpl)headerFactory).createHeader(headerText);
            
            // If we get here, header passed basic parsing
            result.setValid(true);
            result.addMessage("Header successfully parsed");
            
            // Add header type information
            result.addMessage("Header type: " + header.getClass().getSimpleName());
            result.addMessage("Header canonical name: " + header.getName());
            
            // Create HeaderInfo with RFC reference
            String headerName = header.getName();
            RfcReference rfcRef = SipHeaderRfcMapping.getRfcReference(headerName);
            HeaderInfo headerInfo = new HeaderInfo(
                headerName,
                headerName,
                header.getClass().getSimpleName(),
                rfcRef
            );
            result.addHeader(headerInfo);
            
            if (rfcRef != null) {
                result.addMessage("RFC Reference: " + rfcRef.getDisplayText());
            } else {
                result.addMessage("No RFC reference found for header: " + headerName);
            }
            
            return result;
        } catch (ParseException e) {
            return result.addError("Parse error: " + e.getMessage());
        } catch (Exception e) {
            return result.addError("Validation error: " + e.getMessage());
        }
    }

    /**
     * Result class to hold validation outcomes
     */
    public static class ValidationResult {
        private boolean valid = false;
        private List<String> messages = new ArrayList<>();
        private List<String> errors = new ArrayList<>();
        private List<HeaderInfo> headers = new ArrayList<>();

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public List<String> getMessages() {
            return messages;
        }

        public List<String> getErrors() {
            return errors;
        }

        public List<HeaderInfo> getHeaders() {
            return headers;
        }

        public ValidationResult addMessage(String message) {
            this.messages.add(message);
            return this;
        }

        public ValidationResult addError(String error) {
            this.errors.add(error);
            this.valid = false;
            return this;
        }

        public ValidationResult addHeader(HeaderInfo header) {
            this.headers.add(header);
            return this;
        }
    }
}