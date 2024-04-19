package co.istad.banking.features.mail;

import co.istad.banking.features.mail.dto.MailRequest;
import co.istad.banking.features.mail.dto.MailResponse;

public interface MailService {

    MailResponse send(MailRequest mailRequest);
}
