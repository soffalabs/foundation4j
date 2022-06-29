package dev.soffa.foundation.data.spring.service;

import dev.soffa.foundation.commons.DateUtil;
import dev.soffa.foundation.commons.Logger;
import dev.soffa.foundation.commons.Mappers;
import dev.soffa.foundation.context.Context;
import dev.soffa.foundation.context.OperationSideEffects;
import dev.soffa.foundation.core.SideEffectsHandler;
import dev.soffa.foundation.core.action.ProcessSideEffect;
import dev.soffa.foundation.extra.jobs.PendingJob;
import dev.soffa.foundation.extra.jobs.PendingJobId;
import dev.soffa.foundation.extra.jobs.PendingJobRepo;
import dev.soffa.foundation.extra.jobs.ProcessSideEffectInput;
import dev.soffa.foundation.helper.ID;
import dev.soffa.foundation.scheduling.OperationScheduler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@AllArgsConstructor
public class PlatformSideEffectsHandler implements SideEffectsHandler {

    private final PendingJobRepo pendingJobs;
    private final OperationScheduler scheduler;


    @Override
    public void enqueue(String operationName, String uuid, OperationSideEffects sideEffects, Context context) {
        PendingJob job = PendingJob.builder()
            .id(new PendingJobId(ID.generate("side_effect_")))
            .operation(operationName)
            .subject(uuid)
            .data(Mappers.JSON.serialize(sideEffects))
            .created(DateUtil.now())
            .build();
        pendingJobs.insert(job);
        Logger.app.info("Queueing side effect: %s | %s", job.getId(), operationName);
        scheduler.enqueue(uuid, ProcessSideEffect.class, new ProcessSideEffectInput(job.getId()), context);
    }


}