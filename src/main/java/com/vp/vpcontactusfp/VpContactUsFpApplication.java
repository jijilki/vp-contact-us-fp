package com.vp.vpcontactusfp;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.vp.vpcontactusfp.model.ContactUsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.function.Function;

import static com.amazonaws.regions.Regions.*;

@SpringBootApplication
public class VpContactUsFpApplication {

    Logger logger = LoggerFactory.getLogger(VpContactUsFpApplication.class);

    // Replace sender@example.com with your "From" address.
    // This address must be verified with Amazon SES.
    static final String FROM = "jijilki@gmail.com";

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
    static final String TO = "neojilz@gmail.com";

    // The configuration set to use for this email. If you do not want to use a
    // configuration set, comment the following variable and the
    // .withConfigurationSetName(CONFIGSET); argument below.
    static final String CONFIGSET = "ConfigSet";

    // The subject line for the email.
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

    // The HTML body for the email.
    static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>";

    // The email body for recipients with non-HTML email clients.
    static final String TEXTBODY = "This email was sent through Amazon SES "
            + "using the AWS SDK for Java.";


    @Bean
    Function<ContactUsModel,String> contactUs(){
        //return (input) -> String.valueOf(new StringBuilder(input).append(" , thanks for reaching out"));
        //validate request
        //queue/send email/message
        //Send response

        return (input) -> sendEmail();

      // return  (input) -> String.valueOf(new StringBuilder(input.toString()).append(" , thanks for reaching out"));
    }

    private String sendEmail() {




        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM)
                    // Comment or remove the next line if you are not using a
                    // configuration set
             //       .withConfigurationSetName(CONFIGSET)
                   ;
            client.sendEmail(request);
            System.out.println("Email sent!");
            logger.info("Email sent");
            return  "email sent";
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
            logger.info("sending email failed");
            return "sending email failed";
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(VpContactUsFpApplication.class, args);
    }

}
