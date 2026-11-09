package nagikh.dev;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @NotNullArgs
    public void send(String to, String text) {
        System.out.println("Send is done");
    }
}
