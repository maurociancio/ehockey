package ar.noxit.ehockey.service.impl;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IPlanillaDao;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.exceptions.NoxitException;

public class PlanillaService implements IPlanillaService{

    IPlanillaDao planillaDao;

    @Override
    @Transactional(readOnly = true)
    public Planilla get(Integer id) throws NoxitException{
        return planillaDao.get(id);
    }

    public void setPlanillaDao(IPlanillaDao planillaDao) {
        this.planillaDao = planillaDao;
    }

}
