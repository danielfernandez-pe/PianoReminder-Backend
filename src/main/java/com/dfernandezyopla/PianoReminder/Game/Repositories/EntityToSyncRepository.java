package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.Sync.EntityToSync;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityToSyncRepository extends JpaRepository<EntityToSync, Long> {
}
