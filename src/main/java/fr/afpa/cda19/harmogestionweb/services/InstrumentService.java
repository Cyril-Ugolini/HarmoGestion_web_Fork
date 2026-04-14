package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import fr.afpa.cda19.harmogestionweb.repositories.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class InstrumentService {

    private final InstrumentRepository repository;

    public InstrumentService(
            @Autowired
            final InstrumentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Instrument> getInstruments() {
        return repository.getInstruments();
    }

    public Instrument getInstrument(final Integer id) {
        return repository.getInstrument(id);
    }

    public void deleteInstrument(final Integer id) {
        repository.deleteInstrument(id);
    }

    public Instrument saveInstrument(Instrument instrument) {
        Instrument saved;
        instrument.setLibelleInstrument(
                StringUtils.capitalize(
                        instrument.getLibelleInstrument().toLowerCase()
                )
        );
        if (instrument.getIdInstrument() == null) {
            saved = repository.createInstrument(instrument);
        } else {
            saved = repository.updateInstrument(instrument);
        }
        return saved;
    }
}
