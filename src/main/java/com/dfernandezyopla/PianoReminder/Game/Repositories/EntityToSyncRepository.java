package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.EntityToSync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EntityToSyncRepository extends JpaRepository<EntityToSync, Long> {
    @Query("select a from EntityToSync a where a.modifiedAt >= :lastSynced")
    List<EntityToSync> findAllWithModifiedDateAfter(
            @Param("lastSynced") Instant lastSynced);
}
