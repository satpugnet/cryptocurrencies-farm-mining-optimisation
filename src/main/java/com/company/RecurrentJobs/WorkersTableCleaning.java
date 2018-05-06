package com.company.RecurrentJobs;

import com.company.Database.DatabaseAccessor;
import org.apache.log4j.Logger;

import java.util.TimerTask;

public class WorkersTableCleaning extends TimerTask {

    final static Logger logger = Logger.getLogger(WorkersTableCleaning.class);

    @Override
    public void run() {
        logger.info("Starting workers table cleaning");
        DatabaseAccessor db = new DatabaseAccessor(null);
        db.reactualiseWorkerTable();
        logger.info("Finished cleaning workers table");
    }
}
