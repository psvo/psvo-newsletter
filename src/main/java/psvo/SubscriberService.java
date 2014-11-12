package psvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Subscriber Service
 */
@Service
public class SubscriberService {

    public class DuplicateSubscriberException extends Exception {
        protected DuplicateSubscriberException(Throwable cause) {
            super(cause);
        }
    }

    @Autowired
    private SubscriberRepository repository;

    /**
     * Register new subscriber.
     *
     * @param subscriber new subscriber to be saved
     * @return registered subscriber unique id
     * @throws DuplicateSubscriberException
     */
    @Transactional(rollbackFor = Exception.class)
    public Long registerSubscriber(Subscriber subscriber) throws DuplicateSubscriberException {
        try {
            repository.save(subscriber);
            return subscriber.getId();
        } catch (DataIntegrityViolationException e) { // should be probably DuplicateKeyException but it's not
            throw new DuplicateSubscriberException(e);
        }
    }

    /**
     * Return registered subscriber by it's unique id.
     *
     * @param id registered subscriber unique id
     * @return subscriber or null if no such subscriber is found
     */
    public Subscriber getSubscriber(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findOne(id);
    }

    /**
     * Return list of all registered subscribers.
     *
     * @return registered subscriber @see{}Iterable}
     */
    public Iterable<Subscriber> listSubscribers() {
        return repository.findAll();
    }

}