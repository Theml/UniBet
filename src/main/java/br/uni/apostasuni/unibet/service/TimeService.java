package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.dao.TimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {
    @Autowired
    TimeDAO tDao;

    public Time verifySave(Time time) throws Exception {
        if(time.getNome().isEmpty() || time.getNome().isBlank()){
            throw new Exception("Nome do time não pode ser vazio");
        }
        Time t = tDao.findByNome(time.getNome());
        if(t != null) {
            throw new Exception("O time" + t.getNome()+"já está cadastrado.");
        } else {
            return tDao.save(time);
        }
    }

    public List<Time> findAll() {
        return tDao.findAll();
    }

    public void removeTime(int id) throws Exception {
        Optional<Time> t = tDao.findById(id);
        if(t.isPresent()) {
            tDao.delete(t.get());
        } else {
            throw new Exception("Time " +id+ " não existe");
        }
    }

    public Time find(int id) {
        Optional<Time> t = tDao.findById(id);
        if(t.isPresent()){
            return t.get();
        } else {
            return null;
        }
    }

    public Time updateTime(int id, Time time) throws Exception {
        Optional<Time> t = tDao.findById(id);
        if(!t.isPresent()) {
            throw new Exception("time " + " não existe");
        }
        if (time.getNome().isEmpty() || time.getNome().isBlank()) {
            throw new Exception("Nome do time não pode ser vazio!");
        }
        Time timeAlreadyExist = tDao.findByNome((time.getNome()));
        if (timeAlreadyExist != null) {
            throw new Exception("Time " + timeAlreadyExist.getNome() + " já está cadastrado!");
        }
        Time timeChanged = t.get();
        timeChanged.setNome(time.getNome());
        return tDao.save(timeChanged);
    }
}
