package psvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Registration Controller
 */
@Controller
@RequestMapping("/")
public class NewsletterController {

    @Autowired
    protected SubscriberService subscriberService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Subscriber subscriber) {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Subscriber subscriber) {
        return "registration_form";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistrationForm(@Valid Subscriber subscriber, BindingResult bindingResult) {
        Long subscriber_id;
        if (bindingResult.hasErrors()) {
            return "registration_form";
        }
        try {
            subscriber_id = subscriberService.registerSubscriber(subscriber);
        } catch (SubscriberService.DuplicateSubscriberException e) {
            bindingResult.rejectValue("email", "duplicateEmail", "email address already registered");
            return "registration_form";
        }
        return String.format("redirect:/registration/status?id=%1$d", subscriber_id);
    }

    @RequestMapping(value = "/registration/status", method = RequestMethod.GET)
    public String showRegistrationStatus(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("registered", subscriberService.getSubscriber(id));
        model.addAttribute("subscribers", subscriberService.listSubscribers());
        return "registration_status";
    }

}
